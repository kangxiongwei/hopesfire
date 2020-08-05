<style scoped>

</style>
<template>
    <div>
        <Row type="flex" align="middle">
            <Col span="22">
                <Form :label-width="70" inline style="height: 34px; line-height: 34px">
                    <FormItem label="分组名称">
                        <Input size="small" type="text" v-model="groupQueryForm.name"></Input>
                    </FormItem>
                    <FormItem :label-width="0" style="text-align: center">
                        <Button @click="findGroups()" type="primary" size="small" icon="ios-search">查询</Button>
                    </FormItem>
                </Form>
            </Col>
            <Col span="2">
                <Button type="primary" size="small" @click="addGroup">添加分组</Button>
            </Col>
        </Row>
        <Table stripe border size="small" :columns="groupTableHeader" :data="groupTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="updateGroup(row)">编辑</Button>
                <Button type="error" size="small" @click="deleteGroup(row)">删除</Button>
            </template>
        </Table>
        <Drawer :title="saveGroupDrawerTitle" :closable="true" :width="40" v-model="saveGroupDrawer">
            <Form ref="groupForm" :model="groupForm" :rules="groupValidate" :label-width="80" label-position="right">
                <FormItem label="群组名称" prop="name">
                    <Input type="text" v-model="groupForm.name" placeholder="请输入群组名"></Input>
                </FormItem>
                <FormItem label="群组说明" prop="remark">
                    <Input type="text" v-model="groupForm.remark" placeholder="请输入群组说明"></Input>
                </FormItem>
                <FormItem :label-width="0" style="text-align: center">
                    <Button @click="saveGroup('groupForm')" type="primary">保存</Button>
                    <Button @click="resetGroup('groupForm')" type="primary">重置</Button>
                </FormItem>
            </Form>
        </Drawer>
    </div>
</template>
<script>
    import group from '../../../api/group'

    export default {
        data() {
            return {
                groupTable: [],
                groupTableHeader: [
                    {
                        title: '编号',
                        key: 'id'
                    },
                    {
                        title: '分组名称',
                        key: 'name'
                    },
                    {
                        title: '分组说明',
                        key: 'remark'
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
                saveGroupDrawer: false,
                saveGroupDrawerTitle: '',
                groupQueryForm: {
                    tenantId: null,
                    name: null,
                    page: 1,
                    pageSize: 10
                },
                groupForm: {
                    id: null,
                    name: '',
                    remark: '',
                    tenantId: 0
                },
                groupValidate: {
                    name: [
                        {
                            required: true,
                            message: '群组名称不能为空！',
                            trigger: 'blur'
                        }
                    ]
                }
            }
        },
        mounted() {
            this.findGroups();
        },
        methods: {
            findGroups() {
                group.doFindGroups(this, {
                    tenantId: this.groupQueryForm.tenantId,
                    name: this.groupQueryForm.name,
                    page: this.groupQueryForm.page,
                    pageSize: this.groupQueryForm.pageSize
                }).then(res => {
                    if (res != null) {
                        this.groupTable = res.records;
                    }
                });
            },
            addGroup() {
                this.saveGroupDrawer = true;
                this.saveGroupDrawerTitle = '添加群组';
                this.resetGroup('groupForm');
                this.groupForm.id = null;
            },
            updateGroup(row) {
                this.saveGroupDrawer = true;
                this.saveGroupDrawerTitle = '更新群组';
                this.groupForm.id = row.id;
                this.groupForm.name = row.name;
                this.groupForm.remark = row.remark;
            },
            saveGroup(name) {
                this.$refs[name].validate(valid => {
                    if (valid) {
                        group.doSaveGroup(this, {
                            id: this.groupForm.id,
                            name: this.groupForm.name,
                            tenantId: this.groupForm.tenantId,
                            remark: this.groupForm.remark
                        }).then(res => {
                            this.saveGroupDrawer = false
                            this.findGroups();
                        });
                    } else {
                        this.$Message.error("您输入的参数有误，请检查后重新输入！")
                    }
                })
            },
            deleteGroup(row) {
                group.deleteGroup(this, row.id);
                this.findGroups();
            },
            resetGroup(name) {
                this.$refs[name].resetFields();
            }
        }

    }
</script>
