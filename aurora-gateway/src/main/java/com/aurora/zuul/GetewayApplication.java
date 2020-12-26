package com.aurora.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 *  zuul 路由控制
 * @author :PHQ
 * @date：2020/12/24
 **/
@SpringBootApplication
@EnableZuulProxy
public class GetewayApplication {
        public static void main(String[] args) {
            SpringApplication.run(GetewayApplication.class, args);
        }
}
