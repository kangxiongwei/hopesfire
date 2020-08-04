package com.kxw.hopesfire.service;

import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.IUserService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.HopesfireWebApplication;
import org.assertj.core.util.Lists;
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
@SpringBootTest(classes = HopesfireWebApplication.class)
public class UserServiceTest {

    @Resource
    private IUserService userService;

    @Test
    public void test() {
        UserModel model = this.userService.get(1L);
        System.out.println(model);
    }

    @Test
    public void testFindUsers() {
        UserModel user = new UserModel();
        user.setPage(1);
        user.setPageSize(5);
        PagerModel pager = this.userService.find(user);
        System.out.println(pager);
    }

    @Test
    public void testAddUserRoles() {
        this.userService.addUserRoles(1L, Lists.newArrayList(1L,2L,3L));
    }

}
