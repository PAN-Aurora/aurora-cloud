package com.aurora.jpa.controller;

import com.aurora.common.model.ResultModel;
import com.aurora.jpa.api.SysLogService;
import com.aurora.jpa.model.SysLog;
import com.aurora.jpa.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author :PHQ
 * @date：2021/1/27
 **/
@RestController
@RequestMapping("/sysLog")
public class SysLogController  {

    @Autowired
    SysLogRepository sysLogRepository;
    @Autowired
    SysLogService sysLogService;

    @GetMapping("/list")
    public Page<SysLog> pageQuery(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return sysLogRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }
    @GetMapping("/list2")
    public Page<SysLog> pageQuery2(@RequestBody SysLog sysLog) {
        return sysLogService.findRecordList(sysLog);
    }
    @GetMapping("/list3")
    public ResultModel pageQuery3(@RequestBody SysLog sysLog) {
        Page<SysLog>  page =  sysLogService.findRecordList(sysLog);
        return ResultModel.successPage(page.getContent(),page.getTotalElements());
    }
    @GetMapping("/deleteById")
    public void deleteById(@RequestParam(value = "logId") Integer logId) {
        sysLogRepository.deleteById(logId);
    }
    @GetMapping("/getLogByUser")
    public List<SysLog> getLogByUser(@RequestParam(value = "userName") String userName){
       return  sysLogRepository.findLogByUser(userName);
    }
}
