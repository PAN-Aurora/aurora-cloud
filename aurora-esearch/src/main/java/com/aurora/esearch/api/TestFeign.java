package com.aurora.esearch.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 远程服务调用 FeignClient
 * * Feign可以与Eureka和Ribbon组合使用以支持负载均衡。
 * hystrix fallback 回调
 * @author :PHQ
 * @date：2020/12/28
 **/
@Component
@FeignClient(name="aurora-admin",fallback = TestFeignHystrix.class)
public interface TestFeign {

    @RequestMapping(value = "/testController/test")
    public  String  test();
}
