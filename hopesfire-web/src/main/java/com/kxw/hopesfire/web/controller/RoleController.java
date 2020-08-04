package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.service.IRoleService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色控制接口
 *
 * @author kangxiongwei
 * @date 2020/8/4 1:29 下午
 */
@RestController
@RequestMapping("/ctl/auth")
public class RoleController {

    @Resource
    private IRoleService roleService;

    /**
     * 保存角色信息
     *
     * @param role
     * @return
     */
    @PostMapping("/role/save")
    public HttpBaseModel saveRole(@RequestBody RoleModel role) {
        this.roleService.save(role);
        return HttpBaseModel.buildSuccess(role.getId());
    }

    /**
     * 删除角色信息
     *
     * @param id
     * @return
     */
    @GetMapping("/role/delete/{id}")
    public HttpBaseModel deleteUser(@PathVariable Integer id) {
        this.roleService.delete(id);
        return HttpBaseModel.buildSuccess(id);
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/role/get/{id}")
    public HttpBaseModel getUser(@PathVariable Integer id) {
        RoleModel role = roleService.get(id);
        return HttpBaseModel.buildSuccess(role);
    }

    /**
     * 分页查询用户列表
     *
     * @param role
     * @return
     */
    @PostMapping("/role/find")
    public HttpBaseModel findUsers(@RequestBody RoleModel role) {
        PagerModel pager = this.roleService.find(role);
        return HttpBaseModel.buildSuccess(pager);
    }

}
