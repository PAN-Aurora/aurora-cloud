package com.aurora.admin.test;

import com.aurora.admin.api.TestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * todo..
 *
 * @author :PHQ
 * @date：2020/12/28
 **/
@RestController
@RequestMapping("/testController")
public class TestController {

    @Autowired
    TestFeign testFeign;

    @RequestMapping("/test")
    public  String  test(){
         return   "122222";
    }

    @RequestMapping("/testRibbon")
    public  String  testRibbon(){
         return   "testRibbon";
    }

    @RequestMapping("/testEs")
    public  String  testEs(){
         return   testFeign.test();
    }
}
