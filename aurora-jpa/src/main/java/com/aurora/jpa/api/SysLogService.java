package com.aurora.jpa.api;

import com.aurora.jpa.model.SysLog;
import com.aurora.jpa.repository.SysLogRepository;
import org.springframework.data.domain.Page;

/**
 *
 * @author :PHQ
 * @date：2021/1/29
 **/
public interface SysLogService {

    public Page<SysLog> findRecordList(SysLog syslog);
}
