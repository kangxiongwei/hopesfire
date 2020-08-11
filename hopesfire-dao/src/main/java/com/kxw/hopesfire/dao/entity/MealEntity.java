package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020/8/6 10:53 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_meal")
public class MealEntity extends BaseEntity {

    private Integer mealType;
    private String mealName;
    private Integer weight;
    private String username;

}
