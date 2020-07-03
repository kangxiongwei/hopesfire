package com.kxw.hopesfire.service;

import com.kxw.hopesfire.model.UserModel;

/**
 * 登录注册接口
 *
 * @author kangxiongwei
 * @date 2020-07-03 18:20
 */
public interface ILoginService {

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    UserModel login(String username, String password);

}
