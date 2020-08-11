package com.kxw.hopesfire.biz.convert;

import com.kxw.hopesfire.biz.enums.MealTypeEnum;
import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.model.UserMealsModel;
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
        convertMeals(entities, userMeal.getMainMeal(), MealTypeEnum.MEAL_MAIN, userMeal.getUsername());
        convertMeals(entities, userMeal.getMealName(), MealTypeEnum.MEAL_NAME, userMeal.getUsername());
        return entities;
    }

    public static List<MealEntity> convertEntity(UserMealsModel model) {
        List<MealEntity> entities = new ArrayList<>();
        for (UserMealModel meal : model.getMeals()) {
            convertMeals(entities, meal.getMainMeal(), MealTypeEnum.MEAL_MAIN, model.getUsername());
            convertMeals(entities, meal.getMealName(), MealTypeEnum.MEAL_NAME, model.getUsername());
            convertMeals(entities, meal.getMealDrink(), MealTypeEnum.MEAL_DRINKS, model.getUsername());
            convertMeals(entities, meal.getMealFruit(), MealTypeEnum.MEAL_FRUITS, model.getUsername());
        }
        return entities;
    }

    private static void convertMeals(List<MealEntity> entities, String meals, MealTypeEnum mealType, String username) {
        if (StringUtils.isBlank(meals)) {
            return;
        }
        for (String meal : meals.split(COMMA)) {
            entities.add(convertEntity(mealType, meal, username));
        }
    }

    public static MealEntity convertEntity(MealTypeEnum mealType, String mealName, String username) {
        MealEntity entity = new MealEntity();
        entity.setMealName(mealName);
        entity.setMealType(mealType.getMealType());
        entity.setUsername(username);
        return entity;
    }

}
