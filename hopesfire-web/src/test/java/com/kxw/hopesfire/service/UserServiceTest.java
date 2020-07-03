package com.kxw.hopesfire.service;

import com.kxw.hopesfire.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020-07-01 17:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    public void test() {
        UserModel model = this.userService.get(1L);
        System.out.println(model);
    }

}
