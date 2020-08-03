package com.kxw.hopesfire.web.auth;

import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.ILoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 自定义权限数据源
 *
 * @author kangxiongwei
 * @date 2020-07-23 09:15
 */
public class HopesfireRealm extends AuthorizingRealm {

    @Resource
    private ILoginService loginService;

    /**
     * 进行用户认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (token == null || token.getPrincipal() == null) {
            throw new UnknownAccountException("签名不存在！");
        }
        String username = token.getPrincipal().toString();
        UserModel user = loginService.get(username);
        if (user == null) {
            throw new UnknownAccountException("用户名不存在！");
        }
        String credentials = new String((char[]) token.getCredentials());
        if (!user.getPassword().equals(credentials)) {
            throw new UnknownAccountException("密码不正确！");
        }
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }

    /**
     * 进行用户授权
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("====================执行授权=====================");



        return null;
    }
}
