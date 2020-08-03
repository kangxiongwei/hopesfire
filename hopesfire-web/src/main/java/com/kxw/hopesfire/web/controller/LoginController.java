package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录注册控制器
 *
 * @author kangxiongwei
 * @date 2020-07-03 18:17
 */
@Controller
public class LoginController {

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public HttpBaseModel login(@RequestBody UserModel user) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            subject.login(token);
            return HttpBaseModel.buildSuccess(user);
        } catch (AccountException e) {
            return HttpBaseModel.buildFailed(e);
        }
    }

    /**
     * 退出登录
     *
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public HttpBaseModel logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return HttpBaseModel.buildSuccess(null);
    }

}
