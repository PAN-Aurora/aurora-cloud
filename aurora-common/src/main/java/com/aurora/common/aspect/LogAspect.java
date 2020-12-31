package com.aurora.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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
