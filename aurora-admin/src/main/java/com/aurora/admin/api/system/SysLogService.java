package com.aurora.admin.api.system;

import com.aurora.admin.model.system.SysLog;
import com.aurora.admin.model.system.vo.SysLogVo;
import com.aurora.common.model.ResultModel;
/**
 * 日志记录业务接口
 * @author PHQ
 * @create 2020-05-01 23:46
 **/
public interface SysLogService {

    public int saveLog(SysLog sysLog);

    public ResultModel getLogList(SysLogVo sysLog);
}
