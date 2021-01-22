package com.aurora.zuul.filter;

import com.aurora.common.model.ResponseModel;
import com.aurora.common.model.ResultCode;
import com.aurora.common.service.JwtService;
import com.aurora.common.service.RedisService;
import com.aurora.common.utils.FastJsonUtil;
import com.aurora.common.utils.StringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 *  路由过滤器
 *     目的为了对路由进行校验过滤 token
 * @author :PHQ
 * @date：2020/12/28
 **/
@Component
@ConfigurationProperties("jwt")
@Data
public class TokenFilter extends  ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class) ;

    private String[] skipAuthUrls;

    @Autowired
    JwtService  jwtService;

    @Autowired
    RedisService redisService;

    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext() ;
        HttpServletRequest request = requestContext.getRequest() ;
        String reqUri = request.getRequestURI() ;
        log.info("请求地址："+reqUri);
        //根据请求的地址进行过滤 与配置文件 配置路径比对
        if(getSkipAuthUrls().length>0){
            for(String url:getSkipAuthUrls()){
                log.info("路由地址："+url);
                if(reqUri.indexOf(url)!=-1){
                    return false;
                }
            }
        }
        return true;
    }

    //对路由进行校验操作
    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext() ;
        HttpServletRequest request = requestContext.getRequest() ;
        HttpServletResponse response = requestContext.getResponse() ;
        String reqUri = request.getRequestURI() ;
        String method = request.getMethod() ;
      //  String reqUrl = request.getRequestURL() ;

        log.info("请求路径："+reqUri);
       // log.info("请求路径："+reqUrl);
        log.info("请求路径："+method);

        String  token = request.getHeader("Authorization");

        //token为空直接校验不通过
        if(StringUtil.isBlank(token)){
            ResponseModel.responseResult(response, ResultCode.UNAUTHORIZED);
            return null;
        }
        Claims claims = jwtService.getClaimsFromToken(token);
        Long   userId = jwtService.getUserIdFromToken(token);
        //判断redis是否存在key 不存在说明失效了 或者登录不成功
        if(!redisService.exists(String.valueOf(claims.get("username")))){
            ResponseModel.responseResult(response, ResultCode.TOKEN_ERROR);
            return null;
        }
        Object  redisToken =  redisService.get(String.valueOf(claims.get("username")));

        log.info( String.valueOf(redisToken));
        log.info("用户id："+userId);
        log.info("用户名："+FastJsonUtil.toJSONNoFeatures(claims.get("username")));
        log.info("用户详情："+ FastJsonUtil.collectToString(claims));

         //刷新当前key过期时间 30分钟
         redisService.expire(String.valueOf(claims.get("username")),60L, TimeUnit.MINUTES);

        //存放到header
       // requestContext.addZuulRequestHeader("user", FastJsonUtil.collectToString(claims));
      //  String  user = request.getHeader("user");
        response.setHeader("user",FastJsonUtil.collectToString(claims));
       // log.info("用户详情："+user);
        return null;
    }
}
