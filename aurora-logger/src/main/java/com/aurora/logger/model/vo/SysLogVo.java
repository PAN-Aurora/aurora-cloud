package com.aurora.logger.model.vo;

import com.aurora.logger.model.SysLog;
import lombok.Data;

/**
 *  日志vo类 扩展日志实体类
 * @author :PHQ
 * @date：2020/5/19
 **/
@Data
public class SysLogVo extends SysLog {

    public String startTime;
    public String endTime;

}
