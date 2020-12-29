package com.aurora.esearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  搜索引擎 服务
 * @author :PHQ
 * @date：2020/12/28
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class EsearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsearchApplication.class, args);
    }

}
