package com.aurora.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 日志工程
 *    记录用户行为及提供日志相关查询
 * @author :PHQ
 * @date：2021/1/11
 **/
@SpringBootApplication
@EnableEurekaClient
public class LoggerApplication {

    private static final Logger logger = LoggerFactory.getLogger(LoggerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(LoggerApplication.class, args);
        logger.info("###################aurora-logger日志模块启动成功#####################");

    }
}
