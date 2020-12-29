package com.aurora.esearch.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *  使用RestTemplate 远程服务调用
 *    配置 RestTemplate 相关信息
 * @author :PHQ
 * @date：2020/12/29
 **/
@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
