package com.aurora.jpa.repository;

import com.aurora.jpa.model.SysLog;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *  dao 层默认继承 JpaRepository  无需编写实现类
 *    <SysLog, Integer>   第一个表示 jpa操作表实体对象  第二个参数表示 主键类型
 *   JpaSpecificationExecutor  可以用来封装更加复杂的查询
 * @author :PHQ
 * @date：2021/1/27
 **/
public interface  SysLogRepository  extends JpaRepository<SysLog, Integer>, JpaSpecificationExecutor<SysLog> {
    /**
     * nativeQuery  本地查询
     * @param userName
     * @return
     */
    @Query(value="select log_id, log_user,log_desc,log_module ,log_role,log_method,log_url,log_ip,log_type ,log_create_time from sys_log where log_user =?",nativeQuery = true)
    List<SysLog> findLogByUser(String userName);
}
