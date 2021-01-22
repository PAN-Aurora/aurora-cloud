package com.aurora.logger.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aurora.common.annotation.SystemLog;
import com.aurora.common.model.ResultModel;
import com.aurora.logger.api.SysLogService;
import com.aurora.logger.model.vo.SysLogVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 日志控制类
 * @author :PHQ
 * @date：2020/5/19
 **/
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    /**
     * 获取日志管理列表数据
     * @param sysLog
     * @return
     */
    @PostMapping(value = "/getLogList")
    @SystemLog(module="日志管理模块",methods="获取日志列表",url="/log/getLogList", desc="获取日志列表")
    public String getLogList(@RequestBody SysLogVo sysLog){
        return JSONObject.toJSONString(sysLogService.getLogList(sysLog));
    }
}
