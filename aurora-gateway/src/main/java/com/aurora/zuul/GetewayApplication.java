package com.aurora.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
/**
 *  zuul 路由控制
 *     网关控制 拦截所有服务请求 进行重定向
 *     具备 ribbon 负载均衡好 hystrix 服务熔断功能
 * @author :PHQ
 * @date：2020/12/24
 **/
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableZuulProxy
@EnableDiscoveryClient
public class GetewayApplication {
    private static final Logger logger = LoggerFactory.getLogger(GetewayApplication.class);

    public static void main(String[] args) {
            SpringApplication.run(GetewayApplication.class, args);
            logger.info("###################aurora-gateway网关模块启动成功#####################");

    }
}
