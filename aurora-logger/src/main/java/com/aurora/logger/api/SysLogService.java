package com.aurora.logger.api;

import com.aurora.common.model.ResultModel;
import com.aurora.logger.model.SysLog;
import com.aurora.logger.model.vo.SysLogVo;

/**
 *
 * @author :PHQ
 * @date：2021/1/13
 **/
public interface SysLogService {


    public int saveLog(SysLog sysLog);

    public ResultModel getLogList(SysLogVo sysLog);
}
