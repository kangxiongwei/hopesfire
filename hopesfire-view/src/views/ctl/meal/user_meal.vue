<template>
    <div>
        <Row type="flex" align="middle">
            <Col span="22">
                <Form ref="mealQueryForm" :label-width="70" label-position="left" inline style="height: 34px; line-height: 34px">
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
                  @on-change="changeCurrentPage"
            />
        </template>
        <Drawer :title="saveMealDrawerTitle" :closable="true" :width="40" v-model="saveMealDrawer" @on-close="resetMealDrawer">
            <Form ref="mealForm" :model="mealForm" :label-width="80" label-position="right">
                <FormItem label="类型" prop="mealType">
                    <RadioGroup v-model="mealForm.mealType">
                        <Radio :label="1">早餐</Radio>
                        <Radio :label="2">午餐</Radio>
                        <Radio :label="3">晚餐</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="主食" prop="mainMeal">
                    <Select multiple filterable allow-create
                            placeholder="请选择主食"
                            v-model="mealForm.mainMeal">
                        <Option v-for="(option, index) in mainMeals" :value="option.mealName" :key="index">{{option.mealName}}</Option>
                    </Select>
                </FormItem>
                <FormItem label="菜品" prop="mealName">
                    <Input type="text" v-model="mealForm.mealName"
                           placeholder="请输入菜品名称，按回车或Tab键确认" clearable
                           @on-enter="addMealTag"
                           @on-blur="addMealTag">
                    </Input>
                    <!--<AutoComplete clearable size="small" v-model="mealForm.mealName"
                                  placeholder="请输入菜品名称，按Tab键确认"
                                  :data="mealNamesComplete"
                                  @on-blur="addMealTag">
                    </AutoComplete>-->
                    <Tag v-for="(item, index) of mealTags" closable :name="index" :color="item.color" @on-close="deleteMealTag">{{item.name}}</Tag>
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
                    page: 1,
                    pageSize: 10,
                    total: 0,
                    sort: 'update_time',
                    order: 'desc'
                },
                mealTable: [],
                mealTableHeader: [
                    {
                        title: '编号',
                        key: 'id'
                    },
                    {
                        title: '类型',
                        key: 'mealType',
                        render: (h, params) => {
                            return h('div',
                                this.formatMealType(params.row)
                            )
                        }
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
                saveMealDrawer: false,
                saveMealDrawerTitle: '',
                mealForm: {
                    id: null,
                    mealType: null,
                    mainMeal: [],
                    mealName: ''
                },
                mealTags: [],
                mealTagColors: [
                    'primary',
                    'success',
                    'warning',
                    'error'
                ],
                mainMeals: [],
                mealNamesComplete: []
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
                switch (row.mealType) {
                    case 1:
                        return '早餐'
                    case 2:
                        return '午餐'
                    case 3:
                        return '晚餐'
                    default:
                        return '未知'
                }
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
            },
            addMeal() {
                this.saveMealDrawer = true;
                this.saveMealDrawerTitle = '添加饮食记录';
                this.mealForm.id = null;
                this.listMeal(1);
                //this.listMeal(2);
            },
            updateMeal(row) {
                this.saveMealDrawer = true;
                this.saveMealDrawerTitle = '修改饮食记录';
                this.mealForm.id = row.id;
                this.mealForm.mealType = row.mealType;
                this.mealForm.mainMeal = row.mainMeal.split(",");
                let mealNames = row.mealName.split(",");
                mealNames.forEach((item) => {
                    this.mealTags.push({name: item, color: this.randomMealTagColor()});
                })
                this.listMeal(1);
                //this.listMeal(2);
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
            resetMeal(name) {
                this.$refs[name].resetFields();
                this.mealTags = []
                this.mealNamesComplete = []
                this.mainMeals = []
            },
            resetQueryMeal() {
                this.mealQueryForm.mealType = null;
            },
            resetMealDrawer() {
                this.resetMeal('mealForm');
                this.mealTags = []
            },
            addMealTag(event) {
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
            listMeal(type) {
                meal.doListMeals(this, {
                    mealType: type
                }).then(res => {
                    if (type === 1) {
                        this.mainMeals = res;
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>
