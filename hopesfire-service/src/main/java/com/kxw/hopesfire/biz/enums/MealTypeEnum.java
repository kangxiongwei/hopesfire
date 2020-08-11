package com.kxw.hopesfire.biz.enums;

/**
 * @author kangxiongwei
 * @date 2020/8/6 11:17 上午
 */
public enum MealTypeEnum {

    MEAL_MAIN(1, "主食"),
    MEAL_NAME(2, "菜品"),
    MEAL_DRINKS(3, "饮品"),
    MEAL_FRUITS(4, "水果");

    private Integer mealType;
    private String mealTypeName;

    MealTypeEnum(Integer mealType, String mealTypeName) {
        this.mealType = mealType;
        this.mealTypeName = mealTypeName;
    }

    public Integer getMealType() {
        return mealType;
    }

    public String getMealTypeName() {
        return mealTypeName;
    }

}
