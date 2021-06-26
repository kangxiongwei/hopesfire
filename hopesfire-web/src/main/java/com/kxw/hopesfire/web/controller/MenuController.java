package com.kxw.hopesfire.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kxw.hopesfire.biz.model.MenuModel;
import com.kxw.hopesfire.biz.service.IMenuService;
import com.kxw.hopesfire.web.model.HttpBaseModel;

/**
 * 菜单管理
 *
 * @author kangxiongwei
 * @date 2021-06-26
 */
@RestController
@RequestMapping("/ctl/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @PostMapping("/tree")
    public HttpBaseModel listMenuTree(@RequestBody MenuModel menu) {
        List<MenuModel> list = this.menuService.list(menu);
        return HttpBaseModel.buildSuccess(list);
    }

}
