package com.kxw.hopesfire.biz.convert;

import com.kxw.hopesfire.biz.enums.MealTypeEnum;
import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.model.UserMealsModel;
import com.kxw.hopesfire.biz.model.UserSportModel;
import com.kxw.hopesfire.dao.entity.MealEntity;
import com.kxw.hopesfire.dao.entity.UserMealEntity;
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
        convertMealEntities(entities, userMeal);
        return entities;
    }

    public static List<UserMealEntity> convertEntities(UserMealsModel model) {
        List<UserMealEntity> entities = new ArrayList<>();
        for (UserMealModel meal: model.getMeals()) {
            meal.setUsername(model.getUsername());
            meal.setAddDate(model.getAddDate());
            if (!validUserMealModel(meal, model.getSports())) {
                continue;
            }
            UserMealEntity entity = BaseConvert.convertEntity(meal, new UserMealEntity());
            if (model.getSports() != null) {
                entity.setSports(model.getSports().getSports());
            }
            entities.add(entity);
        }
        return entities;
    }

    private static boolean validUserMealModel(UserMealModel meal, UserSportModel sport) {
        if (StringUtils.isNotBlank(meal.getMainMeal())) {
            return true;
        }
        if (StringUtils.isNotBlank(meal.getMealName())) {
            return true;
        }
        if (StringUtils.isNotBlank(meal.getMealFruit())) {
            return true;
        }
        if (StringUtils.isNotBlank(meal.getMealDrink())) {
            return true;
        }
        return StringUtils.isNotBlank(sport.getSports());
    }

    public static List<MealEntity> convertEntity(UserMealsModel model) {
        List<MealEntity> entities = new ArrayList<>();
        for (UserMealModel meal : model.getMeals()) {
            meal.setUsername(model.getUsername());
            convertMealEntities(entities, meal);
        }
        return entities;
    }

    private static void convertMealEntities(List<MealEntity> entities, UserMealModel meal) {
        convertMeals(entities, meal.getMainMeal(), MealTypeEnum.MEAL_MAIN, meal.getUsername());
        convertMeals(entities, meal.getMealName(), MealTypeEnum.MEAL_NAME, meal.getUsername());
        convertMeals(entities, meal.getMealDrink(), MealTypeEnum.MEAL_DRINK, meal.getUsername());
        convertMeals(entities, meal.getMealFruit(), MealTypeEnum.MEAL_FRUIT, meal.getUsername());
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
        entity.setWeight(0);
        entity.setUsername(username);
        return entity;
    }

}
