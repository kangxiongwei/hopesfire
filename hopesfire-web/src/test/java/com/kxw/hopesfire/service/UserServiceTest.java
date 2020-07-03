package com.kxw.hopesfire.service;

import com.kxw.hopesfire.model.PagerModel;
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
    public void testSaveUser() {
        UserModel user = new UserModel(null, "user1", "用户1", 0);
        this.userService.save(user);
        System.out.println(user.getId());
    }

    @Test
    public void testFindUsers() {
        UserModel user = new UserModel();
        PagerModel pager = userService.find(user);
        System.out.println(pager);
    }

}
