package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.model.UserRoleModel;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理接口
 *
 * @author kangxiongwei
 * @date 2020-07-01 13:46
 */
@RestController
@RequestMapping("/ctl/auth")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("/user/save")
    public HttpBaseModel saveUser(@RequestBody UserModel user) {
        this.userService.save(user);
        return HttpBaseModel.buildSuccess(user.getId());
    }

    /**
     * 删除用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/user/delete/{id}")
    public HttpBaseModel deleteUser(@PathVariable Long id) {
        this.userService.delete(id);
        return HttpBaseModel.buildSuccess(id);
    }

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/user/get/{id}")
    public HttpBaseModel getUser(@PathVariable Long id) {
        UserModel user = userService.get(id);
        return HttpBaseModel.buildSuccess(user);
    }

    /**
     * 根据用户ID查询已有角色列表
     *
     * @param id
     * @return
     */
    @GetMapping("/user/role/list/{id}")
    public HttpBaseModel listUserRoles(@PathVariable Long id) {
        List<RoleModel> roles = this.userService.listUserRoles(id);
        return HttpBaseModel.buildSuccess(roles);
    }

    /**
     * 根据用户ID增加角色列表
     *
     * @param userRole
     * @return
     */
    @PostMapping("/user/role/save")
    public HttpBaseModel saveUserRoles(@RequestBody UserRoleModel userRole) {
        this.userService.addUserRoles(userRole.getUserId(), userRole.getRoleIds());
        return HttpBaseModel.buildSuccess(userRole.getUserId());
    }

    /**
     * 分页查询用户列表
     *
     * @param user
     * @return
     */
    @PostMapping("/user/find")
    public HttpBaseModel findUsers(@RequestBody UserModel user) {
        PagerModel pager = this.userService.find(user);
        return HttpBaseModel.buildSuccess(pager);
    }

}
