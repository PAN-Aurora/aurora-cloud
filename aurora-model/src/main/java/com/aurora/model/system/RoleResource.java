package com.aurora.model.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * 角色资源表映射
 * @create 2020-05-14 22:25
 **/
@Data
@TableName("SYS_ROLE_RESOURCE")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleResource {


    @TableId(value = "ID", type = IdType.AUTO)
    private int id;

    @TableField("role_id")
    private int roleId;

    @TableField("resource_id")
    private int resourceId;


}
