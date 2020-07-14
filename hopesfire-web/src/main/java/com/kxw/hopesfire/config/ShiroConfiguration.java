package com.kxw.hopesfire.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
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
    public Realm realm() {
        return null;
    }

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/admin/**", "authc, roles[admin]");
        chainDefinition.addPathDefinition("/**", "authc");
        chainDefinition.addPathDefinition("/**", "anon");
        return chainDefinition;
    }


}
