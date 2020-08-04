package com.kxw.hopesfire.web.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kangxiongwei
 * @date 2020/8/4 4:22 下午
 */
@Configuration
public class MyBatisPlusConfiguration {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
