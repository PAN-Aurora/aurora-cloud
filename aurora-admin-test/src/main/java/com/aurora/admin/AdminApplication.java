package com.aurora.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *  admin 业务主控制模块
 * @author :PHQ
 * @date：2020/12/28
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class AdminApplication {
    public static void main(String[] args) {
         SpringApplication.run(AdminApplication.class, args);
    }


}
