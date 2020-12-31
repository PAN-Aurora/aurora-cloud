package com.aurora.admin.service.system;

import com.alibaba.fastjson.JSON;
import com.aurora.admin.api.system.UserService;
import com.aurora.common.model.ResultCode;
import com.aurora.common.model.ResultModel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

/**
 * 用户业务实现类
 * @author :PHQ
 * @date：2020/5/7
 **/
@Service
public class UserServiceImpl implements UserService {
}
