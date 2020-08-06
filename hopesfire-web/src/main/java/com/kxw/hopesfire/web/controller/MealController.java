package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.MealModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.IMealService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/6 12:04 下午
 */
@RestController
@RequestMapping("/ctl/meal")
public class MealController {

    @Resource
    private IMealService mealService;

    @PostMapping("/save")
    public HttpBaseModel save(@RequestBody MealModel model) {
        Subject subject = SecurityUtils.getSubject();
        UserModel user = (UserModel)subject.getPrincipal();
        model.setUsername(user.getUsername());
        this.mealService.save(model);
        return HttpBaseModel.buildSuccess(model.getId());
    }

    @GetMapping("/delete/{id}")
    public HttpBaseModel delete(@PathVariable Long id) {
        this.mealService.delete(id);
        return HttpBaseModel.buildSuccess(id);
    }

    @PostMapping("/list")
    public HttpBaseModel list(@RequestBody MealModel model) {
        List<MealModel> list = this.mealService.list(model);
        return HttpBaseModel.buildSuccess(list);
    }

    @PostMapping("/find")
    public HttpBaseModel find(@RequestBody MealModel model) {
        PagerModel pager = this.mealService.find(model);
        return HttpBaseModel.buildSuccess(pager);
    }

}
