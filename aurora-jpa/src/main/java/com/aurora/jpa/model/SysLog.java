package com.aurora.jpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author :PHQ
 * @date：2021/1/27
 **/
@Entity
@Table(name = "sys_log")
@Data
@EqualsAndHashCode(callSuper=false)
public class SysLog extends PageModel{

    @Id
    //通过uuid 生成组件
    //@GenericGenerator(name = "idGenerator", strategy = "uuid")
    //@GeneratedValue(generator = "idGenerator")
    @Column(name="log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "log_user", unique = true, nullable = false, length = 255)
    private String logUser;

    @Column(name = "log_role")
    private String logRole;

    @Column(name = "log_module")
    private String logModule;

    @Column(name = "log_method")
    private String logMothod;

    @Column(name = "log_url")
    private String logUrl;

    @Column(name = "log_ip")
    private String logIp;

    @Column(name = "log_desc")
    private String logDesc;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
    @JsonFormat(timezone = "GMT+8",pattern ="yyyy-MM-dd HH:mm:ss")  //出参时间格式化
    @Column(name = "log_create_time")
    @Temporal(TemporalType.TIMESTAMP) //声明类型
    private Date logCreateTime;

    @Column(name = "log_type")
    private int logType = 0;
}
