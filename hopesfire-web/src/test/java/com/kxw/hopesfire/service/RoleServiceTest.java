package com.kxw.hopesfire.service;

import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.service.IRoleService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.HopesfireWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020/8/4 4:14 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopesfireWebApplication.class)
public class RoleServiceTest {

    @Resource
    private IRoleService roleService;

    @Test
    public void testFindRoles() {
        RoleModel role = new RoleModel();
        role.setPage(1);
        role.setPageSize(5);
        PagerModel pager = roleService.find(role);
        System.out.println(pager);
    }

}
