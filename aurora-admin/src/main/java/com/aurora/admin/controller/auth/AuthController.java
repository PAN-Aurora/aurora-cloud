package com.aurora.admin.controller.auth;

import com.aurora.admin.api.auth.AuthService;
import com.aurora.admin.model.auth.User;
import com.aurora.common.annotation.GuavaRateLimiter;
import com.aurora.common.annotation.PassJwtToken;
import com.aurora.common.annotation.SystemLog;
import com.aurora.common.model.ResultCode;
import com.aurora.common.model.ResultModel;
import com.aurora.common.utils.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 *  用户权限登录及控制
 *  @author :PHQ
 * @date：2020/12/30
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录接口
     * @param user
     * @return
     */
    @PostMapping(value = "/login")
    @PassJwtToken
    @SystemLog(module="用户权限模块",methods="用户登录",url="/auth/login", desc="用户登录")
    @GuavaRateLimiter(permitsPerSecond = 1, timeout = 100, timeunit = TimeUnit.MILLISECONDS, msg = "现在访问人数过多,请稍后再试.")
    @ApiOperation(value="登录接口", notes="通过用户名密码登录 ",httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    public ResultModel login(@Valid @RequestBody User user){
        if(StringUtil.isBlank(user.getUsername()) || StringUtil.isBlank(user.getPassword())){
            return ResultModel.failure(ResultCode.LOGIN_ERROR);
        }
        ResultModel response = authService.login(user);
        return response;
    }

//    /**
//     * 退出
//     * @param request
//     * @return
//     */
//    @GetMapping(value = "/logout")
//    @PassJwtToken
//    @SystemLog(module="用户权限模块",methods="退出登录",url="/api/auth/logout", desc="退出登录")
//    @ApiOperation(value="退出登录", notes="通过接口注销登录，清除token ",httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "request", value = "HttpServletRequest对象需要携带token", required = true, dataType = "HttpServletRequest")
//    })
//    public ResultModel logout(HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        if (token == null) {
//            return ResultModel.failure(ResultCode.UNAUTHORIZED);
//        }
//        authService.logout(token);
//        return ResultModel.result(ResultCode.SUCCESS);
//    }

//    @GetMapping(value = "refresh")
//    @SystemLog(module="用户权限模块",methods="刷新token",url="/api/auth/refresh", desc="刷新token")
//    @ApiOperation(value="刷新token", notes="通过接口刷新token ",httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "request", value = "HttpServletRequest对象需要携带token", required = true, dataType = "HttpServletRequest")
//    })
//    public ResultModel refreshAndGetAuthenticationToken(HttpServletRequest request){
//        String token = request.getHeader(tokenHeader);
//        ResponseUserToken response = authService.refresh(token);
//        if(response == null) {
//            return ResultModel.failure(ResultCode.BAD_REQUEST, "token无效");
//        } else {
//            return ResultModel.success();
//        }
//    }

}
