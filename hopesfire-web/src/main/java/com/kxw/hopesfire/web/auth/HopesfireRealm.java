package com.kxw.hopesfire.web.auth;

import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.ILoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
     * 鉴权
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("====================执行鉴权=====================");
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

    /**
     * 授权
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
