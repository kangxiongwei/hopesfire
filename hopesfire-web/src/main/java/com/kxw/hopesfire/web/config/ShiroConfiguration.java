package com.kxw.hopesfire.web.config;

import com.kxw.hopesfire.web.auth.HopesfireRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 权限框架的相关配置
 *
 * @author kangxiongwei
 * @date 2020-07-11 17:59
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    public Realm hopesfireRealm() {
        return new HopesfireRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(hopesfireRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/ctl/auth/**", "authc");
        chainDefinition.addPathDefinition("/user/**", "authc");
        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }

}
