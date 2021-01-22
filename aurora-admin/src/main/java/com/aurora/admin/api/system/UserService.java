package com.aurora.admin.api.system;

import com.aurora.admin.model.auth.User;
import com.aurora.common.model.ResultModel;

/**
 * 用户业务接口类
 * @author PHQ
 * @create 2020-05-01 22:43
 **/
public interface UserService {
    /**
     * 获取用户列表
     * @param user
     * @return
     */
    public ResultModel getUserList(User user);

    /**
     * 新增用户
     * @param user
     * @return
     */
    public ResultModel insertUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    public ResultModel updateUser(User user);

    /**
     * 删除用户
     * @param user
     * @return
     */
    public ResultModel deletetUser(User user);

}
