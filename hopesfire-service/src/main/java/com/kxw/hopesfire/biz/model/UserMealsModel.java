package com.kxw.hopesfire.biz.model;

import lombok.Data;

import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/11 3:25 下午
 */
@Data
public class UserMealsModel {

    private String addDate;
    private List<UserMealModel> meals;
    private UserSportModel sports;
    private String username;

}
