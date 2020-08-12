<template>
    <Form ref="mealForm" :model="mealForm" :label-width="80" label-position="right" class="meal_form">
        <FormItem label="日期" prop="mealDate" class="meal_form_item">
            <DatePicker type="date" :value="mealForm.addDate"
                        format="yyyy-MM-dd"
                        placeholder="请选择日期"
                        clearable style="width: 100%">
            </DatePicker>
        </FormItem>
        <div class="meal_form_breakfast" v-for="item in mealForm.meals">
            <CollapseDivider :title="formatMealType(item.mealType)"></CollapseDivider>
            <FormItem label="主食" prop="mainMeal" class="meal_form_item">
                <Select multiple filterable allow-create v-model="item.mainMeal">
                    <Option v-for="(option, index) in mainMeals" :value="option.mealName" :key="index">{{option.mealName}}</Option>
                </Select>
            </FormItem>
            <FormItem label="饮品" prop="mealDrink" class="meal_form_item">
                <Input type="text" placeholder="按英文逗号分隔" v-model="item.mealDrink" clearable></Input>
            </FormItem>
            <FormItem label="水果" prop="mealFruit" class="meal_form_item">
                <Input type="text" placeholder="按英文逗号分隔" v-model="item.mealFruit" clearable></Input>
            </FormItem>
            <FormItem label="菜品" prop="mealName">
                <Input type="text" clearable placeholder="按回车或Tab键确认"
                       v-model="item.mealName"
                       @on-enter="item.addMealTag()"
                       @on-blur="item.addMealTag()">
                </Input>
                <Tag v-for="(tag, idx) of item.mealTags" closable :color="tag.color" @on-close="item.deleteMealTag(idx)">{{tag.name}}</Tag>
            </FormItem>
        </div>
        <CollapseDivider orientation="center" title="运动"></CollapseDivider>
        <FormItem label="项目" class="meal_form_item">
            <Input type="text" v-model="mealForm.sports.sports" placeholder="请输入运动项目" clearable></Input>
        </FormItem>
        <FormItem :label-width="0" style="text-align: center">
            <Button @click="saveMeal('mealForm')" type="primary">保存</Button>
            <Button @click="resetMeal('mealForm')" type="primary">重置</Button>
        </FormItem>
    </Form>
</template>

<script>
    import meal from "../../../api/meal";
    import CollapseDivider from "../../../component/CollapseDivider";
    import {UserMealsModel} from "../../../model/UserMealModel";

    export default {
        name: "saveMeal",
        props: [
            'mainMeals'
        ],
        data() {
            return {
                mealForm: new UserMealsModel([1, 2, 3])
            }
        },
        methods: {
            resetMeal() {
                this.mealForm = null;
                this.mealForm = new UserMealsModel([1, 2, 3]);
            },
            formatMealType(mealType) {
                return meal.formatMealType(mealType);
            },
            saveMeal() {
                this.mealForm.meals.forEach(item => {
                    item.formatMeal();
                })
                meal.doSaveUserMeals(this, this.mealForm).then(res => {
                    this.resetMeal('mealForm')
                    this.$emit('success', res);
                });
            },
            updateMeal(row) {
                this.mealForm = new UserMealsModel([row.mealType]);
                this.mealForm.meals.forEach(item => {
                    item.id = row.id;
                    item.mealType = row.mealType;
                    item.mainMeal = row.mainMeal.split(",");
                    if (row.mealName != null && row.mealName !== '') {
                        let mealNames = row.mealName.split(",");
                        mealNames.forEach((name) => {
                            item.mealTags.push({name: name, color: item.randomMealTagColor()});
                        })
                    }
                })
            }
        },
        components: {
            CollapseDivider
        }
    }
</script>

<style scoped>
    .meal_form_item {
        margin-bottom: 0;
    }
</style>
