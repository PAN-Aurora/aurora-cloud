package com.aurora.common.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
/**
 *  rabbitMq 配置文件
 * @author :PHQ
 * @date：2020/12/31
 **/
@Component
@ConfigurationProperties
@PropertySource("classpath:rabbit.properties")
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    public String host;
    @Value("${spring.rabbitmq.port}")
    public int port;
    @Value("${spring.rabbitmq.username}")
    public String username;
    @Value("${spring.rabbitmq.password}")
    public String password;




}

