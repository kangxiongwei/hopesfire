package com.kxw.hopesfire.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.client.RestTemplate;

/**
 * 项目启动类
 *
 * @author kangxiongwei
 */
@SpringBootApplication(scanBasePackages = {"com.kxw.hopesfire"})
@MapperScan("com.kxw.hopesfire.dao.mapper")
//@EnableEurekaClient
//@EnableDiscoveryClient
//@EnableFeignClients
public class HopesfireWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopesfireWebApplication.class, args);
    }

}
