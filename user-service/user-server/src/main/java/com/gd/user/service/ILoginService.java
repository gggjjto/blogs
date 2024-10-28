package com.gd.user.service;

import com.gd.user.pojo.UserLoginBO;

/**
 * Demo class
 *
 * @author jiang
 * @date 2024/9/28
 */
public interface ILoginService {
    /**
     * 用户登录服务
     *
     * @param username 用户名
     * @param password 密码
     * @return 匹配到的信息
     */
    UserLoginBO login(String username, String password);

}
