package com.aurora.admin.api.system;

import com.aurora.admin.model.system.Role;
import com.aurora.common.model.ResultModel;

/**
 *
 * @author PHQ
 * @create 2020-05-01 22:44
 **/
public interface RoleService {

    //获取角色列表
    public ResultModel getRoleList(Role role);

    //增加角色
    public ResultModel  insertRole(Role role);
    //更新角色
    public ResultModel  updateRole(Role role);
    //刪除角色
    public ResultModel  deleteRole(Role role);
}
