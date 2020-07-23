package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.exception.ServiceException;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.ILoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 登录注册控制器
 *
 * @author kangxiongwei
 * @date 2020-07-03 18:17
 */
@RestController
public class LoginController {

    @Resource
    private ILoginService loginService;

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public HttpBaseModel login(@RequestBody UserModel user, HttpSession session) {
        try {
            UserModel u = loginService.login(user.getUsername(), user.getPassword());
            session.setAttribute("loginUser", u);
            return HttpBaseModel.buildSuccess(u);
        } catch (ServiceException e) {
            return HttpBaseModel.buildFailed(e);
        }
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @PostMapping("/logout")
    public HttpBaseModel logout(HttpSession session) {
        session.setAttribute("loginUser", null);
        return HttpBaseModel.buildSuccess(null);
    }


}
