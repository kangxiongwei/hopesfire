package com.kxw.hopesfire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 *
 * @author kangxiongwei
 */
@SpringBootApplication
@MapperScan("com.kxw.hopesfire.dao.mapper")
public class HopesfireApplication {

    public static void main(String[] args) {
        SpringApplication.run(HopesfireApplication.class, args);
    }

}
