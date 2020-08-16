<template>
    <div>
        <Row type="flex" align="middle">
            <Col span="22">
                <Form ref="mealQueryForm" :label-width="70" label-position="left" inline style="height: 34px; line-height: 34px">
                    <FormItem :label-width="70" label="时间范围">
                        <DatePicker v-model="mealQueryForm.dateRange" type="daterange"
                                    placeholder="查询日期范围"
                                    @on-change="changeMealDate"
                                    style="width: 200px">
                        </DatePicker>
                    </FormItem>
                    <FormItem :label-width="40" label="类型">
                        <Select v-model="mealQueryForm.mealType" size="small" placeholder="请选择类型" style="width:150px">
                            <Option label="全部" :value="0">全部</Option>
                            <Option label="早餐" :value="1">早餐</Option>
                            <Option label="午餐" :value="2">午餐</Option>
                            <Option label="晚餐" :value="3">晚餐</Option>
                        </Select>
                    </FormItem>
                    <FormItem :label-width="0" style="text-align: center">
                        <Button @click="findMeals()" type="primary" size="small" icon="ios-search">查询</Button>
                        <Button @click="resetQueryMeal" type="primary" size="small" icon="ios-nuclear">重置</Button>
                    </FormItem>
                </Form>
            </Col>
            <Col span="2">
                <Button type="primary" size="small" @click="addMeal">添加记录</Button>
            </Col>
        </Row>
        <Table stripe border size="small" :columns="mealTableHeader" :data="mealTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="updateMeal(row)">编辑</Button>
                <Button type="error" size="small" @click="deleteMeal(row)">删除</Button>
            </template>
        </Table>
        <template>
            <Page style="margin-top: 5px" size="small" show-total show-elevator
                  :total="mealQueryForm.total"
                  :page-size="mealQueryForm.pageSize"
                  :current="mealQueryForm.page"
                  @on-change="changeCurrentPage"/>
        </template>
        <Drawer :title="saveMealDrawerTitle" :closable="true" :width="40" v-model="saveMealDrawer" @on-close="resetMealDrawer">
            <saveMeal ref="saveMeal" :mainMeals="mainMeals" @success="saveMealsSuccess"></saveMeal>
        </Drawer>
    </div>
</template>

<script>

    import meal from '../../../api/meal'
    import saveMeal from "./save_meal"

    export default {
        components: {
            saveMeal
        },
        data() {
            return {
                mealQueryForm: {
                    startDate: null,
                    endDate: null,
                    mealType: null,
                    page: 1,
                    pageSize: 10,
                    total: 0,
                    sort: 'update_time',
                    order: 'desc',
                    dateRange: null
                },
                mealTable: [],
                mealTableHeader: [
                    {
                        title: '类型',
                        key: 'mealType',
                        render: (h, params) => {
                            return h('div',
                                this.formatMealType(params.row)
                            )
                        },
                        width: 70
                    },
                    {
                        title: '主食',
                        key: 'mainMeal'
                    },
                    {
                        title: '菜品',
                        key: 'mealName'
                    },
                    {
                        title: '饮品',
                        key: 'mealDrink'
                    },
                    {
                        title: '水果',
                        key: 'mealFruit'
                    },
                    {
                        title: '运动',
                        key: 'sports'
                    },
                    {
                        title: '日期',
                        key: 'addDate',
                        width: 100
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        fixed: 'right',
                        width: 130,
                        align: 'center'
                    }
                ],
                saveMealDrawer: false,
                saveMealDrawerTitle: '',
                mainMeals: []
            }
        },
        mounted() {
            this.findMeals();
        },
        methods: {
            findMeals() {
                let type = this.mealQueryForm.mealType;
                let mealType = type === 0 ? null : type;
                meal.doFindUserMeals(this, {
                    mealType: mealType,
                    startDate: this.mealQueryForm.startDate,
                    endDate: this.mealQueryForm.endDate,
                    page: this.mealQueryForm.page,
                    pageSize: this.mealQueryForm.pageSize,
                    sort: this.mealQueryForm.sort,
                    order: this.mealQueryForm.order
                }).then(res => {
                    if (res != null) {
                        this.mealTable = res.records;
                        this.mealQueryForm.total = res.total;
                    }
                })
            },
            changeCurrentPage(current) {
                this.mealQueryForm.page = current;
                this.findMeals();
            },
            formatMealType(row) {
                return meal.formatMealType(row.mealType);
            },
            addMeal() {
                this.saveMealDrawer = true;
                this.saveMealDrawerTitle = '添加饮食记录';
                this.listMeal(1);
                this.$refs['saveMeal'].addMeal();
            },
            updateMeal(row) {
                this.saveMealDrawer = true;
                this.saveMealDrawerTitle = '修改饮食记录';
                this.listMeal(1);
                this.$refs['saveMeal'].updateMeal(row);
            },
            deleteMeal(row) {
                this.$Modal.confirm({
                    title: '确认删除',
                    content: '请问您确认要删除这条记录吗？',
                    closable: true,
                    okText: '删除',
                    onOk: () => {
                        meal.doDeleteUserMeal(this, row.id).then(() => {
                            this.findMeals();
                        });
                    }
                });
            },
            resetQueryMeal() {
                this.mealQueryForm.mealType = null;
                this.mealQueryForm.startDate = null;
                this.mealQueryForm.endDate = null;
                this.mealQueryForm.dateRange = null;
            },
            resetMealDrawer() {
                this.$refs['saveMeal'].resetMeal();
            },
            listMeal(type) {
                meal.doListMeals(this, {
                    mealType: type
                }).then(res => {
                    if (type === 1) {
                        this.mainMeals = res;
                    }
                })
            },
            saveMealsSuccess() {
                this.saveMealDrawer = false;
                this.resetMealDrawer();
                this.findMeals();
            },
            changeMealDate(formatDate) {
                this.mealQueryForm.startDate = formatDate[0];
                this.mealQueryForm.endDate = formatDate[1];
            }
        }
    }
</script>

<style scoped>

</style>
