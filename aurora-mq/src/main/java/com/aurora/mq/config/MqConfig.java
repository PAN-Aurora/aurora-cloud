package com.aurora.mq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列config
 * @author :PHQ
 * @date：2020/12/31
 **/
@Configuration
public class MqConfig {

    private static final Logger logger = LoggerFactory.getLogger(MqConfig.class);


    @Autowired
    RabbitConfig rabbitConfig;

    /**
     * 构建连接工厂
     * @return
     */
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitConfig.host,rabbitConfig.port);
//        connectionFactory.setUsername(rabbitConfig.username);
//        connectionFactory.setPassword(rabbitConfig.password);
//        //虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离
//        connectionFactory.setVirtualHost("/");
//
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//
//    @Autowired
//    private CachingConnectionFactory connectionFactory;
//

    @Bean
    public RabbitTemplate rabbitTemplate() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitConfig.host,rabbitConfig.port);
        connectionFactory.setUsername(rabbitConfig.username);
        connectionFactory.setPassword(rabbitConfig.password);
        connectionFactory.setChannelCheckoutTimeout(3000);
        connectionFactory.setPublisherReturns(true);
        //虚拟主机，一个broker里可以开设多个vhost，用作不同用户的权限分离
        connectionFactory.setVirtualHost("/");

        connectionFactory.setPublisherConfirms(true);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
       // rabbitTemplate.setMessageConverter(converter());

        // 消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                logger.info("消息成功发送到Exchange");
                String msgId = correlationData.getId();
                logger.info("消息发送到Exchange:id: {}",msgId);

            } else {
                logger.info("消息发送到Exchange失败, {}", correlationData);
                logger.info("消息发送到Exchange失败, cause: {}", correlationData, cause);
            }
        });

        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            logger.info("消息从Exchange路由到Queue失败: exchange: {}", exchange);
            logger.info("消息从Exchange路由到Queue失败: route: {}", routingKey);
            logger.info("消息从Exchange路由到Queue失败: replyCode: {}",replyCode);
            logger.info("消息从Exchange路由到Queue失败: replyText: {}",replyText);
            logger.info("消息从Exchange路由到Queue失败: message: {}",message);;
        });

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    //日志存储消息队列属性信息
    public static final String LOGGER_QUEUE_NAME = "logger.queue";
    public static final String LOGGER_EXCHANGE_NAME = "logger.exchange";
    public static final String LOGGER_ROUTING_KEY_NAME = "logger.routing.key";

    /**
     * 创建一个日志队列 用于存放日志消息
     * @return
     */
    @Bean
    public Queue loggerQueue() {
        return new Queue(LOGGER_QUEUE_NAME, true);
    }

    /**
     * 创建一个日志交换机
     * @return
     */
    @Bean
    public DirectExchange loggerExchange() {
        return new DirectExchange(LOGGER_EXCHANGE_NAME, true, false);
    }

    /**
     * 将队列绑定到交换机 并使用 路由关键字创建绑定关系
     * @return
     */
    @Bean
    public Binding loggerBinding() {
        return BindingBuilder.bind(loggerQueue()).to(loggerExchange()).with(LOGGER_ROUTING_KEY_NAME);
    }
}
