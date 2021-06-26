package com.kxw.hopesfire.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kxw.hopesfire.biz.model.MenuModel;
import com.kxw.hopesfire.biz.service.IMenuService;
import com.kxw.hopesfire.web.HopesfireWebApplication;

/**
 * @author kangxiongwei
 * @date 2021-06-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HopesfireWebApplication.class)
public class MenuServiceTest {

    @Resource
    private IMenuService menuService;

    @Test
    public void testListMenus() {
        List<MenuModel> list = menuService.list(new MenuModel());
        System.out.println(list);
    }
}
