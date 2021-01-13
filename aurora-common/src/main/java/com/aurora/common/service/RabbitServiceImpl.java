package com.aurora.common.service;

import com.aurora.common.config.MqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * todo..
 *
 * @author :PHQ
 * @date：2020/12/31
 **/
@Component
public class RabbitServiceImpl implements  RabbitService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(MqConfig.LOGGER_EXCHANGE_NAME, MqConfig.LOGGER_ROUTING_KEY_NAME, content, correlationId);
    }
    public void sendMsg(Object content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(MqConfig.LOGGER_EXCHANGE_NAME, MqConfig.LOGGER_ROUTING_KEY_NAME, content, correlationId);
    }

    public void sendMsg(String exchange, String routingKey,String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        rabbitTemplate.convertAndSend(exchange, routingKey, content, correlationId);

    }

}
