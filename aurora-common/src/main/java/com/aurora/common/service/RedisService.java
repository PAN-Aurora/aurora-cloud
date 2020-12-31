package com.aurora.common.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *  redis 针对RedisTemplate 二次封装的接口
 * @author :PHQ
 * @date：2020/12/29
 **/
@Component
@Service
public interface RedisService {
    /**
     * 存入String 类型 数据
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value);

    /**
     * 改变key 的过期时间
     * @param key
     * @param expireTime
     * @param timeUnit
     * @return
     */
    public boolean expire(final String key,Long expireTime, TimeUnit timeUnit);

    /**
     * 判断一个key是否存在
     * @param key
     * @return
     */
    public boolean exists(final String key);

    public long  increment(final String key);

    /**
     * 存入带过期时间的key
     * @param key
     * @param value
     * @param expireTime 时间长度
     * @param timeUnit  时间的单位 秒 分 时 天
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit);

    public void remove(final String... keys);

    public void removePattern(final String pattern);

    public Object get(final String key);

    public int getKeyCount(String key,String pattern);

    public void hmSet(String key, Object hashKey, Object value);

    public Object hmGet(String key, Object hashKey);

    public void lPush(String k, Object v);

    public List<Object> lRange(String k, long l, long l1);

    public void add(String key, Object value);

    public Set<Object> setMembers(String key);

    public void zSetAdd(String key, Object value, double scoure);

    public Set<Object> rangeByScore(String key, double scoure, double scoure1);
}
