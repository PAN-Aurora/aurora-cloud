package com.aurora.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 *  admin 业务主控制模块
 * @author :PHQ
 * @date：2020/12/28
 **/
@SpringBootApplication(scanBasePackages = {"com.aurora.*"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.aurora.*.mapper")
public class AdminApplication {
    private static final Logger logger = LoggerFactory.getLogger(AdminApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
        logger.info("###################aurora-admin模块启动成功#####################");
    }

}
