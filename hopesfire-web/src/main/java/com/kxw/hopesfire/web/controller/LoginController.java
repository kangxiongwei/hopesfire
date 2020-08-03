package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 登录注册控制器
 *
 * @author kangxiongwei
 * @date 2020-07-03 18:17
 */
@Controller
public class LoginController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping("/user/add")
    public String addUser() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String updateUser() {
        return "user/update";
    }

    @RequestMapping("/unauth")
    public String unauth() {
        return "unauth";
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public String login(String username, String password) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
            return "redirect:index";
        } catch (AccountException e) {
            return "redirect:unauth";
        }
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @PostMapping("/logout")
    @ResponseBody
    public HttpBaseModel logout(HttpSession session) {
        session.setAttribute("loginUser", null);
        return HttpBaseModel.buildSuccess(null);
    }


}
