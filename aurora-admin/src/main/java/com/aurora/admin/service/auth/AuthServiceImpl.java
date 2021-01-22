package com.aurora.admin.service.auth;

import com.alibaba.fastjson.JSONObject;
import com.aurora.admin.api.auth.AuthService;
import com.aurora.admin.mapper.auth.AuthMapper;
import com.aurora.admin.mapper.system.ResourceMapper;
import com.aurora.admin.model.auth.ResponseUserToken;
import com.aurora.admin.model.auth.User;
import com.aurora.admin.model.system.Resource;
import com.aurora.admin.model.system.Role;
import com.aurora.common.model.ResultCode;
import com.aurora.common.model.ResultModel;
import com.aurora.common.service.JwtService;
import com.aurora.common.service.RedisService;
import com.aurora.common.utils.Md5Util;
import com.aurora.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *  用户登录权限类
 * @author :PHQ
 * @date：2020/12/30
 **/
@Service
public class AuthServiceImpl  implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
   AuthMapper  authMapper;

    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    RedisService  redisService;

    @Autowired
    JwtService jwtService;

    @Override
    public User register(User user) {
        return null;
    }

    private static final String CLAIM_KEY_USER_ID = "user_id";
    private static final String CLAIM_KEY_AUTHORITIES = "scope";

    @Override
    public ResultModel login( User user) {
        //首先判断用户名密码是否为空
        User  loginUser =   authMapper.findByUsername(user);
        if(loginUser!=null){
             //密码校验
            logger.info("md5加密" +Md5Util.encrypt(user.getPassword()));
             if(!Md5Util.encrypt(user.getPassword()).equals(loginUser.getPassword())){
                 return ResultModel.failure(ResultCode.LOGIN_ERROR);
             }
             //进行加密和redis保存token
            JSONObject jsonObject = (JSONObject)JSONObject.toJSON(loginUser);
            Set<Map.Entry<String,Object>> entrySet = jsonObject.entrySet();
            Map<String, Object> claims=new HashMap<String,Object>();
            for (Map.Entry<String, Object> entry : entrySet) {
                claims.put(entry.getKey(), entry.getValue());
            }

            //获取用户对应角色权限信息
            Role role = authMapper.findRoleByUserId(loginUser);
            loginUser.setRole(role);

            if(role!= null){
                //根据角色查询角色资源
                List<Resource> menuList =   resourceMapper.getResourceListByRoleId(role.getId());
                loginUser.setMenuList(menuList);

                List<Resource>  resourceList =   resourceMapper.getResourceListByType(role.getId());
                loginUser.setResourceList(resourceList);
            }
            //增加claims userid 和权限
            claims.put(CLAIM_KEY_USER_ID,loginUser.getId());
            //获取菜单权限
            claims.put(CLAIM_KEY_AUTHORITIES,loginUser.getId());
           //生成token 并将token进行保存
           String  token  =   jwtService.generateAccessToken(loginUser.getUsername(),claims);
           //30分钟
           redisService.set(loginUser.getUsername(),token,60L, TimeUnit.MINUTES);

           return  ResultModel.successData(ResultCode.SUCCESS, new ResponseUserToken(token, loginUser));

        }else{
             return ResultModel.failure(ResultCode.LOGIN_ERROR,"暂无用户");
        }
    }

    /**
     *  redis 将token进行清除
     * @param token
     */
    @Override
    public void logout(String token) {
        String userNmae =  jwtService.getUsernameFromToken(token);
        redisService.remove(userNmae);
    }

    @Override
    public User getUserByToken(String token) {
        return null;
    }

    @Override
    public User getUserInfo(User userLogin) {
        return null;
    }
}
