package com.kxw.hopesfire.bi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 数据统计的项目，独立部署
 *
 * @author kangxiongwei
 * @date 2020-07-14 15:07
 */
@EnableEurekaServer
@SpringBootApplication
public class HopesfireBiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopesfireBiApplication.class, args);
    }


}
