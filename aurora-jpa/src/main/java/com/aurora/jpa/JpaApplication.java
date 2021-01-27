package com.aurora.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author :PHQ
 * @date：2021/1/27
 **/
@SpringBootApplication
@EnableEurekaClient
public class JpaApplication {

    private static final Logger logger = LoggerFactory.getLogger(JpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
        logger.info("###################JPA模块启动成功#####################");
    }
}