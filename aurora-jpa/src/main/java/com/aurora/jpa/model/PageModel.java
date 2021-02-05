package com.aurora.jpa.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 分页model
 * @author PHQ
 * @create 2020-05-01 11:09
 **/
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PageModel implements Serializable {
    @JSONField(serialize = false)
    @Getter
    private int current = 1;  //当前页

    @JSONField(serialize = false)
    private int start = 0;  //起始位置

    @JSONField(serialize = false)
    @Getter
    private int limit = 10; //每页大小

    @JSONField(serialize = false)
    @Getter
    private int[] ids; //ids集合

    public int getStart() {
        start = (this.current-1) * this.limit;
        return start;
    }
}
