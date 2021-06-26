package com.kxw.hopesfire.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

/**
 * 系统的一些配置参数
 *
 * @author kangxiongwei
 * @date 2020/8/10 5:35 下午
 */
@Data
@Configuration
@PropertySource({"classpath:/configs/application-config-${spring.profiles.active}.properties"})
public class ApplicationConfiguration {

    @Value("${user.attach.path}")
    private String attachPath;

    @Value("${wx.app.id}")
    private String wxAppId;

    @Value("${wx.app.secret}")
    private String wxAppSecret;

    @Value("${wx.app.token}")
    private String wxAppToken;

}
