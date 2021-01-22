package com.aurora.logger.listener;

import com.alibaba.fastjson.JSONObject;
import com.aurora.logger.model.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *  日志
 *    从mq获取日志消息并进行存储入库或者存入es
 * @author :PHQ
 * @date：2021/1/13
 **/
//@Component
//@RabbitListener(queues = "logger.queue")
public class LoggerListener {

    private static final Logger logger = LoggerFactory.getLogger(LoggerListener.class);

    @RabbitHandler
    public void process(SysLog message){

        logger.info("接收日志消息："+message);
        logger.info("接收日志消息："+JSONObject.toJSONString(message));
    }
}
