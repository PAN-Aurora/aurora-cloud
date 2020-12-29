package com.aurora.esearch.test;

import com.aurora.esearch.api.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 *  测试远程服务调用相关
 * @author :PHQ
 * @date：2020/12/28
 **/
@RestController
@RequestMapping("/testController")
public class TestController {

    @Autowired
    TestFeign testFeign;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/test")
    public  String  test(){
        return   "调用成功！";
    }

    @RequestMapping("/testFeign")
    public  String  testFeign(){
        return   testFeign.test();
    }
    @RequestMapping("/testFeign2")
    public  String  testFeign2(){
        return  restTemplate.getForObject("http://aurora-admin/testController/testRibbon", String.class);
    }
}
