package com.gd.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gd.clientResource.ResourceClient;
import com.gd.core.result.RestResult;
import com.gd.user.mapper.UserSafetyMapper;
import com.gd.user.mapper.UserViewMapper;
import com.gd.user.pojo.UserLoginBO;
import com.gd.user.pojo.UserSafety;
import com.gd.user.pojo.UserView;
import com.gd.user.service.ILoginService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/9/28
 */
@Service
@Slf4j
public class LoginServiceImpl implements ILoginService {

    @Resource
    private UserSafetyMapper userSafetyMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserViewMapper userViewMapper;

    @Resource
    private ResourceClient resourceClient;

    @Override
    public UserLoginBO login(String username, String password) {
        LambdaQueryWrapper<UserSafety> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserSafety::getUsername, username);
        UserSafety user = userSafetyMapper.selectOne(wrapper);
        if (Objects.nonNull(user) && passwordEncoder.matches(password, user.getPassword())) {
            // 查询用户信息
            UserView userView = userViewMapper.selectById(user.getUserId());
            // 查询院校名称
            UserLoginBO userLoginBO = new UserLoginBO();
            BeanUtils.copyProperties(userView, userLoginBO);
            // 查询
            RestResult<String> universityName = resourceClient.getUniversityName(userView.getSchoolCode());
            userLoginBO.setSchoolName(universityName.getData());
            return userLoginBO;
        }
        return null;
    }
}
