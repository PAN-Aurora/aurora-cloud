package com.aurora.mq.controller;

import com.aurora.mq.service.RabbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  mq 调用
 * @author :PHQ
 * @date：2021/1/29
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    RabbitService rabbitService;

    @RequestMapping("/getList")
    public String  getList(@RequestParam("msg") String msg){
        rabbitService.sendMsg(msg);
        return "hhhhh";
    }
}
