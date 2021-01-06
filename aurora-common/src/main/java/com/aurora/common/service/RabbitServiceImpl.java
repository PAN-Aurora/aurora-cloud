package com.aurora.common.service;

import com.aurora.common.config.MqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.UUID;

/**
 * todo..
 *
 * @author :PHQ
 * @date：2020/12/31
 **/
public class RabbitServiceImpl implements  RabbitService,RabbitTemplate.ConfirmCallback {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

   // Confirm模式  所有消息发送且返回确认
    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String cause) {
        logger.info(" 回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + cause);
        }

    }
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(MqConfig.EXCHANGE_A, MqConfig.ROUTINGKEY_A, content, correlationId);
    }

    public void sendMsg(String exchange, String routingKey,String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(exchange, routingKey, content, correlationId);

    }

}
