package com.kxw.hopesfire.biz.model;

import lombok.Data;

import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/11 3:25 下午
 */
@Data
public class UserMealsModel {

    private int saveType;             //添加类型，0 添加 1更新
    private String addDate;
    private List<UserMealModel> meals;
    private UserSportModel sports;
    private String username;

}
