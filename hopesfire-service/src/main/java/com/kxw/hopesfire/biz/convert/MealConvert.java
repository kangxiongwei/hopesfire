package com.kxw.hopesfire.biz.convert;

import com.kxw.hopesfire.biz.enums.MealTypeEnum;
import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.dao.entity.MealEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/6 11:22 上午
 */
public class MealConvert {

    private static final String COMMA = ",";

    /**
     * 装换菜品
     *
     * @param userMeal
     * @return
     */
    public static List<MealEntity> convertEntity(UserMealModel userMeal) {
        List<MealEntity> entities = new ArrayList<>();
        if (StringUtils.isNotBlank(userMeal.getMainMeal())) {
            for (String meal: userMeal.getMainMeal().split(COMMA)) {
                entities.add(convertEntity(MealTypeEnum.MEAL_MAIN, meal, userMeal.getUsername()));
            }
        }
        if (StringUtils.isNotBlank(userMeal.getMealName())) {
            for (String meal: userMeal.getMealName().split(COMMA)) {
                entities.add(convertEntity(MealTypeEnum.MEAL_NAME, meal, userMeal.getUsername()));
            }
        }
        return entities;
    }

    public static MealEntity convertEntity(MealTypeEnum mealType, String mealName, String username) {
        MealEntity entity = new MealEntity();
        entity.setMealName(mealName);
        entity.setMealType(mealType.getMealType());
        entity.setWeight(0);
        entity.setUsername(username);
        return entity;
    }

}
