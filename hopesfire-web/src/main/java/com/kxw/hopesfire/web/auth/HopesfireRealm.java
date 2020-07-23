package com.kxw.hopesfire.web.auth;

import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.ILoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

import javax.annotation.Resource;

/**
 * 自定义权限数据源
 *
 * @author kangxiongwei
 * @date 2020-07-23 09:15
 */
public class HopesfireRealm extends AuthenticatingRealm {

    @Resource
    private ILoginService loginService;

    /**
     * 鉴权
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token.getPrincipal() == null) {
            return null;
        }
        String username = token.getPrincipal().toString();
        try {
            UserModel user = loginService.get(username);
            return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        } catch (Exception e) {
            return null;
        }
    }

}
