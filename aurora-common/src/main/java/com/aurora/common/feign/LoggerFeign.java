package com.aurora.common.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 日志远程服务调用
 * @author :PHQ
 * @date：2021/1/21
 **/
@Component
@FeignClient(name="aurora-mq")
public interface LoggerFeign {

    @RequestMapping(value = "/rabbit/getList", method = RequestMethod.POST, consumes = "application/json")
    public String getLogList(String msg);
}
