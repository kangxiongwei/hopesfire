import moment from 'moment';

export const MEAL_TAG_COLORS = [
    'primary',
    'success',
    'warning',
    'error',
    'default'
]

export function UserMealsModel(mealTypes) {
    this.addDate = moment().format("YYYY-MM-DD");
    this.meals = [];
    this.sports = new UserSportModel();
    mealTypes.forEach(item => {
        this.addUserMeal(item);
    })
}

export function UserMealModel(mealType) {
    this.id = null;
    this.mealType = mealType;
    this.mainMeal = [];
    this.mealDrink = '';
    this.mealFruit = '';
    this.mealName = '';
    this.mealTags = [];
}

export function UserSportModel() {
    this.sports = '';
}

UserMealModel.prototype.randomMealTagColor = function () {
    let length = MEAL_TAG_COLORS.length;
    let random = Math.floor(Math.random() * length);
    return MEAL_TAG_COLORS[random];
}

UserMealModel.prototype.addMealTag = function () {
    if (this.mealName === null || this.mealName === '') {
        return;
    }
    this.mealTags.push({name: this.mealName, color: this.randomMealTagColor()});
    this.mealName = null;
}

UserMealModel.prototype.deleteMealTag = function (index) {
    this.mealTags.splice(index, 1);
}

UserMealModel.prototype.formatMeal = function () {
    if (this.mealTags.length > 0) {
        this.mealName = this.mealTags.map((item, index) => {
            return item.name
        }).join(",");
    } else {
        this.mealName = ''
    }
    if (this.mainMeal.length > 0) {
        this.mainMeal = this.mainMeal.join(",")
    } else {
        this.mainMeal = ''
    }
}

UserMealsModel.prototype.addUserMeal = function (mealType) {
    this.meals.push(new UserMealModel(mealType))
}
