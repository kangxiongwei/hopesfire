package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.UserModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 基本的控制类
 *
 * @author kangxiongwei
 * @date 2020/8/14 6:51 下午
 */
public class BaseController {

    /**
     * 获取当前登录用户ID
     *
     * @return
     */
    protected String getLoginUsername() {
        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel) subject.getPrincipal();
        return user.getUsername();
    }

}
