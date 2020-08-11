package com.kxw.hopesfire.web.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author kangxiongwei
 * @date 2020/8/10 5:35 下午
 */
@Data
@Configuration
@PropertySource({"classpath:/configs/application-config-${spring.profiles.active}.properties"})
public class ApplicationConfiguration {

    @Value("${user.attach.path}")
    private String attachPath;

}
