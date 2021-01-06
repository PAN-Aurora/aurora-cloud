package com.aurora.common.service;

/**
 * todo..
 *
 * @author :PHQ
 * @date：2020/12/31
 **/
public interface RabbitService {
    /**
     *  使用默认的 交换机和 路由关键字
     * @param content
     */
    public void sendMsg(String content);

    /**
     * 指定交换机和路由路由关键字
     * @param exchange
     * @param routingKey
     * @param content
     */
    public void sendMsg(String exchange, String routingKey,String content);

}
