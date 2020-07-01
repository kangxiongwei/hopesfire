package com.kxw.hopesfire.controller;

import com.kxw.hopesfire.model.HttpBaseModel;
import com.kxw.hopesfire.model.UserModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    @RequestMapping("/user/list")
    public HttpBaseModel listUsers() {
        List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(1L, "user1", "用户1", 0));
        users.add(new UserModel(2L, "user2", "用户2", 1));
        users.add(new UserModel(3L, "user3", "用户3", 0));
        users.add(new UserModel(4L, "user4", "用户4", 1));
        users.add(new UserModel(5L, "user5", "用户5", 0));
        return HttpBaseModel.buildSuccess(users);
    }

}
