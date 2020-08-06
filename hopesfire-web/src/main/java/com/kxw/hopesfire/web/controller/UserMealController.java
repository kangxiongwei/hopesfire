package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.IUserMealService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020/8/5 10:49 下午
 */
@RestController
@RequestMapping("/ctl/user/meal")
public class UserMealController {

    @Resource
    private IUserMealService userMealService;

    private UserMealModel setLoginUser(UserMealModel meal) {
        Subject subject = SecurityUtils.getSubject();
        UserModel login = (UserModel)subject.getPrincipal();
        meal.setUsername(login.getUsername());
        return meal;
    }

    @PostMapping("/save")
    public HttpBaseModel save(@RequestBody UserMealModel meal) {
        this.userMealService.save(setLoginUser(meal));
        return HttpBaseModel.buildSuccess(meal.getId());
    }

    @GetMapping("/delete/{id}")
    public HttpBaseModel delete(@PathVariable Long id) {
        this.userMealService.delete(id);
        return HttpBaseModel.buildSuccess(id);
    }

    @PostMapping("/find")
    public HttpBaseModel find(@RequestBody UserMealModel meal) {
        PagerModel pager = this.userMealService.find(setLoginUser(meal));
        return HttpBaseModel.buildSuccess(pager);
    }

}
