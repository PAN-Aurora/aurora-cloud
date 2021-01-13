package com.aurora.common.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aurora.common.annotation.PassJwtToken;
import com.aurora.common.annotation.SystemLog;
import com.aurora.common.model.Global;
import com.aurora.common.model.log.SysLog;
import com.aurora.common.service.JwtService;
import com.aurora.common.service.RabbitService;
import com.aurora.common.utils.IpUtil;
import com.aurora.common.utils.StringUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.Timestamp;

/**
 * 日志AOP记录
 * @author PHQ
 * @create 2020-05-01 22:54
 **/
@Slf4j
@Configuration
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    RabbitService rabbitService;

    //定义切点 针对类上注解含有SystemLog
    @Pointcut(value = "@annotation(com.aurora.common.annotation.SystemLog)")
    public void logPointCut(){}

    /**
     * 后置通知
     * @param joinPoint
     * @param ret
     */
    @AfterReturning(returning = "ret",pointcut = "logPointCut()")
    public void saveSysLog(JoinPoint joinPoint, Object ret){
        logger.info("日志记录开始.........");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog log = new  SysLog();
        SystemLog systemLog  =  method.getAnnotation(SystemLog.class);
        if (systemLog != null) {
            HttpServletRequest  request =   this.getRequest();
            logger.info("日志记录操作方法:"+systemLog.methods());
            //登录相关接口不携带token
            PassJwtToken passJwtToken  =  method.getAnnotation(PassJwtToken.class);
            if(passJwtToken != null) {
                //获取切点参数
                Object[] args = joinPoint.getArgs();
                logger.info("参数："+args);

            }else{
                String  auth_token  = request.getHeader("Authorization");
                final String auth_token_start = Global.JWT_TOKENHEAD;

                if (StringUtil.isNotBlank(auth_token) && auth_token.startsWith(auth_token_start)) {
                    auth_token = auth_token.substring(auth_token_start.length());
                } else {
                    // 不按规范,不允许通过验证
                    auth_token = null;
                }
//                Claims claims = JwtService.getClaimsFromToken(auth_token);
//                log.setLogUser(claims.get("sub").toString());
//                log.setLogRole(claims.get("scope").toString());
            }
            log.setLogIp(IpUtil.getIpAddress(request));
            //log注解数据
            log.setLogModule(systemLog.module());
            log.setLogUrl(systemLog.url());
            log.setLogMothod(systemLog.methods());
            log.setLogCreateTime(new Timestamp(System.currentTimeMillis()));
            log.setLogType(1);

            //发送日志到mq
            rabbitService.sendMsg(log);
        }
    }

    /**
     * 异常通知
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint,Exception ex) {
        if (ex instanceof Exception) {
            logger.error("异常信息："+ex);
            String logDesc =ex.getMessage().substring(0,ex.getMessage().length()>900?900:ex.getMessage().length());
        }
    }




    /**
     * 获取request对象
     * @return
     */
    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }

}
