package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.model.UserMealsModel;

/**
 * @author kangxiongwei
 * @date 2020/8/5 10:41 下午
 */
public interface IUserMealService extends IBaseService<UserMealModel> {

    /**
     * 保存用户的菜单
     *
     * @param model
     */
    void saveUserMeals(UserMealsModel model);


}
