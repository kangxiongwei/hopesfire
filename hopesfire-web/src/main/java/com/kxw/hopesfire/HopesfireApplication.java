package com.kxw.hopesfire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 项目启动类
 *
 * @author kangxiongwei
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.kxw.hopesfire.dao.mapper")
public class HopesfireApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopesfireApplication.class, args);
    }

}
