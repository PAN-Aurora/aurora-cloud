package com.aurora.admin.test;

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

    @RequestMapping("/test")
    public  String  test(){
         return   "2";
    }



    @RequestMapping("/testRibbon")
    public  String  testRibbon(){
        return   "testRibbon222222";
    }
}
