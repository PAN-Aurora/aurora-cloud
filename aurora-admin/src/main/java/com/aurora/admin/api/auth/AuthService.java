package com.aurora.admin.api.auth;

import com.aurora.admin.model.auth.User;
import com.aurora.common.model.ResultModel;
import org.springframework.stereotype.Service;

/**
 * 权限登录接口定义
 * @author PHQ
 * @create 2020-05-03 12:20
 **/
public interface AuthService {

    /**
     * 注册用户
     * @param user
     * @return
     */
    User register(User user);

    /**
     * 登陆
     * @param user
     * @return
     */
    ResultModel login( User user);

    /**
     * 登出
     * @param token
     */
    void logout(String token);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    User getUserByToken(String token);

    /**
     * 获取用户详情信息
     * @param userLogin
     * @return
     */
    public User getUserInfo(User userLogin);
}
