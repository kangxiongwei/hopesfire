package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020/8/5 10:41 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserMealModel extends BaseModel {

    private Integer mealType;
    private String mainMeal;
    private String mealDrink;
    private String mealName;
    private String mealFruit;
    private String username;

}
