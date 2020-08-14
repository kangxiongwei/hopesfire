package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.model.UserMealQueryModel;
import com.kxw.hopesfire.biz.model.UserMealsModel;
import com.kxw.hopesfire.biz.service.IUserMealService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020/8/5 10:49 下午
 */
@RestController
@RequestMapping("/ctl/user/meal")
public class UserMealController extends BaseController {

    @Resource
    private IUserMealService userMealService;

    @PostMapping("/save")
    public HttpBaseModel save(@RequestBody UserMealModel meal) {
        meal.setUsername(this.getLoginUsername());
        this.userMealService.save(meal);
        return HttpBaseModel.buildSuccess(meal.getId());
    }

    @GetMapping("/delete/{id}")
    public HttpBaseModel delete(@PathVariable Long id) {
        this.userMealService.delete(id);
        return HttpBaseModel.buildSuccess(id);
    }

    @PostMapping("/find")
    public HttpBaseModel find(@RequestBody UserMealQueryModel meal) {
        meal.setUsername(this.getLoginUsername());
        PagerModel pager = this.userMealService.find(meal);
        return HttpBaseModel.buildSuccess(pager);
    }

    @PostMapping("/save/meals")
    public HttpBaseModel saveUserMeals(@RequestBody UserMealsModel model) {
        model.setUsername(this.getLoginUsername());
        this.userMealService.saveUserMeals(model);
        return HttpBaseModel.buildSuccess(null);
    }

}
