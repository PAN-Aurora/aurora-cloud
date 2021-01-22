package com.aurora.admin.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.aurora.admin.api.feign.LoggerFeign;
import com.aurora.admin.model.system.SysLog;
import com.aurora.common.annotation.SystemLog;
import com.aurora.common.model.ResultModel;
import com.aurora.logger.model.vo.SysLogVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  日志模块调用 远程日志模块
 * @author :PHQ
 * @date：2021/1/21
 **/
@RestController
@RequestMapping("/logger")
public class LoggerController {
    private static final Logger log = LoggerFactory.getLogger(LoggerController.class);

    @Autowired
    private LoggerFeign loggerFeign;

    /**
     * 获取日志管理列表数据
     * @param sysLog
     * @return
     */
    @GetMapping(value = "/getLogList")
    @SystemLog(module="日志管理模块",methods="获取日志列表",url="/log/getLogList", desc="获取日志列表")
    @ApiOperation(value="获取日志列表", notes="获取日志列表 ",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysLog", value = "日志详情实体sysLog", required = true, dataType = "SysLog")
    })
    public String getLogList(SysLog sysLog){
        log.info("参数" +sysLog.toString());
        return loggerFeign.getLogList(sysLog);
    }
}
