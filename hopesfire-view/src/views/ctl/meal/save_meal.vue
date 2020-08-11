<template>
    <Form ref="mealForm" :model="mealForm" :label-width="80" label-position="right">
        <FormItem label="日期" prop="mealDate" class="meal_form_item">
            <DatePicker type="date" :value="mealForm.addDate"
                        format="yyyy-MM-dd"
                        placeholder="请选择日期"
                        clearable style="width: 100%">
            </DatePicker>
        </FormItem>
        <Divider style="margin-top: 0; margin-bottom: 0;">早餐</Divider>
        <FormItem label="饮品" prop="mealDrinks" class="meal_form_item">
            <Input type="text" :value="mealForm.mealDrinks" placeholder="请输入饮品" clearable></Input>
        </FormItem>
        <FormItem label="水果" prop="mealFruits" class="meal_form_item">
            <Input type="text" :value="mealForm.mealFruits" placeholder="请输入水果" clearable></Input>
        </FormItem>
        <Divider style="margin-top: 0; margin-bottom: 0">运动</Divider>
        <FormItem label="项目" class="meal_form_item">
            <Input type="text" :value="mealForm.sports" placeholder="请输入运动项目" clearable></Input>
        </FormItem>
        <FormItem :label-width="0" style="text-align: center">
            <Button @click="saveMeal('mealForm')" type="primary">保存</Button>
            <Button @click="resetMeal('mealForm')" type="primary">重置</Button>
        </FormItem>
    </Form>
</template>

<script>
    import meal from "../../../api/meal";
    import moment from 'moment';

    export default {
        name: "saveMeal",
        props: [
            'mainMeals'
        ],
        data() {
            return {
                mealForm: {
                    id: null,
                    addDate: null,
                    mealType: null,
                    mainMeal: '',
                    mealDrinks: '',
                    mealFruits: '',
                    mealName: '',
                    doSports: false,
                    sports: ''
                },
                mealTags: [],
                mealTagColors: [
                    'primary',
                    'success',
                    'warning',
                    'error'
                ],
                mealNamesComplete: []
            }
        },
        mounted() {
            this.mealForm.addDate = moment().format("YYYY-MM-DD");
        },
        methods: {
            addMealTag() {
                if (this.mealForm.mealName === null || this.mealForm.mealName === '') {
                    return;
                }
                this.mealTags.push({name: this.mealForm.mealName, color: this.randomMealTagColor()});
                this.mealForm.mealName = null;
            },
            deleteMealTag(event, name) {
                const index = this.mealTags.indexOf(name);
                this.mealTags.splice(index, 1);
            },
            randomMealTagColor() {
                let length = this.mealTagColors.length;
                let random = Math.floor(Math.random() * length);
                return this.mealTagColors[random];
            },
            resetMeal(name) {
                this.$refs[name].resetFields();
                this.mealTags = []
                this.mealNamesComplete = []
                this.mainMeals = []
            },
            saveMeal(name) {
                let mealNames = [];
                this.mealTags.forEach((item) => {
                    mealNames.push(item.name)
                });
                let mainMeals = this.mealForm.mainMeal;
                meal.doSaveUserMeal(this, {
                    id: this.mealForm.id,
                    mealType: this.mealForm.mealType,
                    mainMeal: mainMeals.join(","),
                    mealName: mealNames.join(",")
                }).then(() => {
                    this.saveMealDrawer = false;
                    this.resetMealDrawer();
                    this.findMeals();
                })
            }
        }
    }
</script>

<style scoped>
    .meal_form_item {
        margin-bottom: 0;
    }
</style>
