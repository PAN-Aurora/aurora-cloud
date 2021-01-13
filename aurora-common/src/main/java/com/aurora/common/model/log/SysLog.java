package com.aurora.common.model.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 日志实体
 * @author PHQ
 * @create 2020-05-01 23:18
 **/
@Data
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysLog implements Serializable {

        private int id;

        private String logUser;

        private String logRole;

        private String logModule;

        private String logMothod;

        private String logUrl;

        private String logIp;

        private String logDesc;

        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")//页面写入数据库时格式化
        @JsonFormat(timezone = "GMT+8",pattern ="yyyy-MM-dd HH:mm:ss")
        private Timestamp logCreateTime;

        private int logType = 0;


}
