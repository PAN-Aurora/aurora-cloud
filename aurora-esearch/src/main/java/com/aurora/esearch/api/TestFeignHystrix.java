package com.aurora.esearch.api;

import org.springframework.stereotype.Component;

/**
 * todo..
 *
 * @author :PHQ
 * @date：2020/12/28
 **/
@Component
public class TestFeignHystrix implements TestFeign {
    @Override
    public String test() {
        return "调用失败";
    }
}
