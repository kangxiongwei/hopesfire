<template>
    <div>
        <Row type="flex" align="middle">
            <Col span="22">
                <Form ref="mealQueryForm" :label-width="70" label-position="left" inline style="height: 34px; line-height: 34px">
                    <FormItem :label-width="40" label="类型">
                        <Select v-model="mealQueryForm.mealType" @on-change="changeQueryForm" size="small" placeholder="请选择类型" style="width:150px">
                            <Option label="主食" :value="1">主食</Option>
                            <Option label="菜品" :value="2">菜品</Option>
                            <Option label="饮品" :value="3">饮品</Option>
                        </Select>
                    </FormItem>
                    <FormItem :label-width="0" style="text-align: center">
                        <Button @click="findMeals()" type="primary" size="small" icon="ios-search">查询</Button>
                        <Button @click="resetMeal('mealQueryForm')" type="primary" size="small" icon="ios-nuclear">重置</Button>
                    </FormItem>
                </Form>
            </Col>
            <Col span="2">
                <Button type="primary" size="small" @click="addMeal">添加菜品</Button>
            </Col>
        </Row>
        <Table stripe border size="small" :columns="mealTableHeader" :data="mealTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="updateMeal(row)">编辑</Button>
                <Button type="error" size="small" @click="deleteMeal(row)">删除</Button>
            </template>
        </Table>
        <Page style="margin-top: 5px" size="small" show-total show-elevator
              :total="totalMeals"
              :page-size="mealQueryForm.pageSize"
              :current="mealQueryForm.page"
              @on-change="changeCurrentPage"
        />
        <Drawer :title="saveMealDrawerTitle" :closable="true" :width="40" v-model="saveMealDrawer" @on-close="resetMeal('mealForm')">
            <Form ref="mealForm" :model="mealForm" :label-width="80" label-position="right">
                <FormItem label="类型" prop="mealType">
                    <RadioGroup v-model="mealForm.mealType">
                        <Radio :label="1">主食</Radio>
                        <Radio :label="2">菜品</Radio>
                        <Radio :label="3">饮品</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="名称" prop="mealName">
                    <Input type="text" v-model="mealForm.mealName" placeholder="请输入名称" clearable></Input>
                </FormItem>
                <FormItem :label-width="0" style="text-align: center">
                    <Button @click="saveMeal('mealForm')" type="primary">保存</Button>
                    <Button @click="resetMeal('mealForm')" type="primary">重置</Button>
                </FormItem>
            </Form>
        </Drawer>
    </div>
</template>

<script>

    import meal from '../../../api/meal'

    export default {
        data() {
            return {
                mealQueryForm: {
                    mealType: null,
                    mealName: null,
                    page: 1,
                    pageSize: 10
                },
                totalMeals: 0,
                mealTable: [],
                mealTableHeader: [
                    {
                        title: '菜品名称',
                        key: 'mealName'
                    },
                    {
                        title: '菜品类型',
                        key: 'mealType',
                        render: (h, params) => {
                            return h('div',
                                this.formatMealType(params.row)
                            )
                        }
                    },
                    {
                        title: '创建时间',
                        key: 'createTime'
                    },
                    {
                        title: '更新时间',
                        key: 'updateTime'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 150,
                        fixed: 'right',
                        align: 'center'
                    }
                ],
                saveMealDrawerTitle: '',
                saveMealDrawer: false,
                mealForm: {
                    id: null,
                    mealName: '',
                    mealType: null
                }
            }
        },
        mounted() {
            this.findMeals();
        },
        methods: {
            findMeals() {
                meal.doFindMeals(this, {
                    mealType: this.mealQueryForm.mealType,
                    mealName: this.mealQueryForm.mealName,
                    page: this.mealQueryForm.page,
                    pageSize: this.mealQueryForm.pageSize
                }).then(res => {
                    this.mealTable = res.records;
                    this.totalMeals = res.total;
                })
            },
            changeCurrentPage(current) {
                this.mealQueryForm.page = current;
                this.findMeals();
            },
            changeQueryForm() {
                this.mealQueryForm.page = 1;
            },
            formatMealType(row) {
                switch (row.mealType) {
                    case 1:
                        return '主食';
                    case 2:
                        return '菜品';
                    case 3:
                        return '饮品';
                    default:
                        return '未知';
                }
            },
            addMeal() {
                this.saveMealDrawer = true;
                this.saveMealDrawerTitle = '添加菜品'
            },
            updateMeal(row) {
                this.saveMealDrawer = true;
                this.saveMealDrawerTitle = '更新菜品';
                this.mealForm.id = row.id;
                this.mealForm.mealType = row.mealType;
                this.mealForm.mealName = row.mealName;
            },
            saveMeal() {
                meal.doSaveMeal(this, {
                    id: this.mealForm.id,
                    mealType: this.mealForm.mealType,
                    mealName: this.mealForm.mealName
                }).then(() => {
                    this.saveMealDrawer = false;
                    this.resetMeal('mealForm');
                    this.findMeals();
                })
            },
            deleteMeal(row) {
                this.$Modal.confirm({
                    title: '确认删除',
                    content: '请问您确认要删除这条记录吗？',
                    closable: true,
                    okText: '删除',
                    onOk: () => {
                        meal.doDeleteMeal(this, row.id).then(() => {
                            this.findMeals();
                        });
                    }
                });
            },
            resetMeal(name) {
                this.$refs[name].resetFields();
                this.mealForm.id = null;
                if (name === 'mealQueryForm') {
                    this.mealQueryForm.mealType = null;
                }
            }
        }
    }
</script>

<style scoped>

</style>
