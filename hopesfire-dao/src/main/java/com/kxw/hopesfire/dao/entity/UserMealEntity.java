package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020/8/5 10:38 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_meal")
public class UserMealEntity extends BaseEntity {

    private Integer mealType;
    private String mainMeal;
    private String mealName;
    private String username;

}
