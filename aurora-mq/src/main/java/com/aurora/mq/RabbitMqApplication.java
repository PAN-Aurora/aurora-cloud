package com.aurora.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class RabbitMqApplication {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMqApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
        logger.info("###################RabbitMq模块启动成功#####################");
    }
}
