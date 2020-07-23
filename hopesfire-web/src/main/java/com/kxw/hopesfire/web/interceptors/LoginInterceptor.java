package com.kxw.hopesfire.web.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录的拦截器
 *
 * @author kangxiongwei
 * @date 2020-07-03 19:02
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    //未登录时的页面重定向状态码
    private static final int AUTH_ERROR_CODE = 304;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) {
            LOGGER.info("未登录，需要登录！");
            response.setStatus(AUTH_ERROR_CODE);
            return false;
        } else {
            LOGGER.info("已登录，当前用户：{}", loginUser);
            return true;
        }*/
        return true;
    }

}
