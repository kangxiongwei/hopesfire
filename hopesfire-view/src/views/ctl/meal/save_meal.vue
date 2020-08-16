<template>
    <Form ref="mealForm" :model="mealForm" :label-width="40" label-position="right" class="meal_form">
        <FormItem label="日期" prop="mealDate" :label-width="80" class="meal_form_item">
            <DatePicker type="date" v-model="mealForm.addDate"
                        format="yyyy-MM-dd"
                        placeholder="请选择日期"
                        @on-change="changeAddDate"
                        clearable style="width: 100%">
            </DatePicker>
        </FormItem>
        <Steps ref="mealSteps" direction="vertical" :current="currentStep">
            <div v-for="(item,idx) in mealForm.meals">
                <Step icon="ios-aperture">
                    <div slot="title">
                        {{item.mealTypeName}}
                    </div>
                    <div slot="content" :class="currentStepClass[idx]">
                        <Row :gutter="16">
                            <Col :span="12">
                                <FormItem label="主食" prop="mainMeal" class="meal_form_item">
                                    <Input type="text" clearable placeholder="按英文逗号分隔" v-model="item.mainMeal"></Input>
                                </FormItem>
                            </Col>
                            <Col :span="12">
                                <FormItem label="菜品" prop="mealName">
                                    <Input type="text" clearable placeholder="按英文逗号分隔" v-model="item.mealName"></Input>
                                </FormItem>
                            </Col>
                        </Row>
                        <Row :gutter="16">
                            <Col :span="12">
                                <FormItem label="饮品" prop="mealDrink" class="meal_form_item">
                                    <Input type="text" clearable placeholder="按英文逗号分隔" v-model="item.mealDrink"></Input>
                                </FormItem>
                            </Col>
                            <Col :span="12">
                                <FormItem label="水果" prop="mealFruit" class="meal_form_item">
                                    <Input type="text" clearable placeholder="按英文逗号分隔" v-model="item.mealFruit"></Input>
                                </FormItem>
                            </Col>
                        </Row>
                    </div>
                </Step>
            </div>
            <Step title="运动" icon="ios-football">
                <div v-if="this.mealForm.saveType === 0" slot="content" :class="this.currentStepClass[3]">
                    <FormItem label="项目" class="meal_form_item">
                        <Input type="text" v-model="mealForm.sports.sports" placeholder="请输入运动项目" clearable></Input>
                    </FormItem>
                </div>
                <div v-if="this.mealForm.saveType === 1" slot="content" :class="this.currentStepClass[1]">
                    <FormItem label="项目" class="meal_form_item">
                        <Input type="text" v-model="mealForm.sports.sports" placeholder="请输入运动项目" clearable></Input>
                    </FormItem>
                </div>
            </Step>
        </Steps>
        <FormItem :label-width="0" style="text-align: center">
            <Button @click="nextCurrentStep()" type="primary">下一步</Button>
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
                currentStep: 0,
                currentStepClass: ['meal_step', 'meal_step_1', 'meal_step_2', 'meal_step_sports'],
                mealForm: new UserMealsModel([1, 2, 3], 0)
            }
        },
        methods: {
            resetMeal() {
                this.currentStep = 0;
                if (this.mealForm.saveType === 0) {
                    this.currentStepClass = ['meal_step', 'meal_step_1', 'meal_step_2', 'meal_step_sports'];
                    this.mealForm = new UserMealsModel([1, 2, 3], 0);
                } else {
                    let item = this.mealForm.meals[0];
                    let itemId = item.id;
                    this.currentStepClass = ['meal_step', 'meal_step_sports'];
                    this.mealForm = new UserMealsModel([item.mealType], 1);
                    this.mealForm.meals[0].id = itemId;
                }
            },
            formatMealType(mealType) {
                return meal.formatMealType(mealType);
            },
            saveMeal() {
                meal.doSaveUserMeals(this, this.mealForm).then(res => {
                    this.resetMeal('mealForm')
                    this.$emit('success', res);
                });
            },
            addMeal() {
                this.currentStep = 0;
                this.currentStepClass = ['meal_step', 'meal_step_1', 'meal_step_2', 'meal_step_sports'];
                this.mealForm = new UserMealsModel([1, 2, 3], 0);
            },
            updateMeal(row) {
                this.mealForm = new UserMealsModel([row.mealType], 1);
                this.mealForm.meals[0].initMeal(row);
                this.mealForm.sports.sports = row.sports;
                this.currentStep = 0;
                this.currentStepClass = ['meal_step', 'meal_step_sports']
            },
            changeAddDate(formatDate) {
                this.mealForm.addDate = formatDate;
            },
            nextCurrentStep() {
                //隐藏当前步骤
                if (this.mealForm.saveType === 0 && this.currentStep === 3) {
                    this.currentStepClass[3] = 'meal_step_sports';
                    this.currentStep = 0;
                } else if (this.mealForm.saveType === 1 && this.currentStep === 1) {
                    this.currentStepClass[1] = 'meal_step_sports';
                    this.currentStep = 0;
                } else {
                    this.currentStepClass[this.currentStep] = 'meal_step_' + this.currentStep;
                    this.currentStep += 1;
                }
                //展开激活的步骤
                this.currentStepClass[this.currentStep] = 'meal_step'
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

    .meal_step_0 {
        display: none;
    }

    .meal_step_1 {
        display: none;
    }

    .meal_step_2 {
        display: none;
    }

    .meal_step_sports {
        display: none;
    }

</style>
