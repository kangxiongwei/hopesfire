package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020/8/6 10:55 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MealModel extends BaseModel {

    private Integer mealType;
    private String mealName;
    private Integer weight;
    private String username;

}
