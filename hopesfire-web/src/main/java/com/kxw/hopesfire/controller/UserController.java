package com.kxw.hopesfire.controller;

import com.kxw.hopesfire.model.HttpBaseModel;
import com.kxw.hopesfire.model.PagerModel;
import com.kxw.hopesfire.model.UserModel;
import com.kxw.hopesfire.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @GetMapping("/user/{id}")
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
    @GetMapping("/user/{id}")
    public HttpBaseModel getUser(@PathVariable Long id) {
        UserModel user = userService.get(id);
        return HttpBaseModel.buildSuccess(user);
    }

    /**
     * 分页查询用户列表
     *
     * @param user
     * @return
     */
    @PostMapping("/user/find")
    public HttpBaseModel listUsers(@RequestBody UserModel user) {
        PagerModel pager = this.userService.find(user);
        return HttpBaseModel.buildSuccess(pager);
    }

}
