<style scoped>

</style>
<template>
    <div class="content">
        <Row type="flex" align="middle">
            <Col span="22">
                <Form :label-width="70" inline style="height: 34px; line-height: 34px">
                    <FormItem label="角色名称">
                        <Input size="small" type="text" v-model="roleQueryForm.name"></Input>
                    </FormItem>
                    <FormItem :label-width="0" style="text-align: center">
                        <Button @click="findRoles()" type="primary" size="small" icon="ios-search">查询</Button>
                    </FormItem>
                </Form>
            </Col>
            <Col span="2">
                <Button type="primary" size="small" @click="beforeSaveRole('insert', null)">添加角色</Button>
            </Col>
        </Row>
        <Table stripe border size="small" :columns="roleTableHeader" :data="roleTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="beforeSaveRole('update',row)">编辑</Button>
                <Button type="error" size="small" @click="deleteRole(row)">删除</Button>
                <Button type="success" size="small" @click="roleGroup(row)">群组</Button>
            </template>
        </Table>
        <template>
            <Page style="margin-top: 5px" size="small" show-total show-elevator
                  :total="roleQueryForm.total"
                  :page-size="roleQueryForm.pageSize"
                  :current="roleQueryForm.page"
                  @on-change="changeCurrentPage"
            />
        </template>
        <Drawer :title="saveRoleDrawerTitle" :closable="true" :width="40" v-model="saveRoleDrawer">
            <Form ref="roleForm" :model="roleForm" :rules="roleValidate" :label-width="80" label-position="right">
                <FormItem label="角色名称" prop="name">
                    <Input type="text" v-model="roleForm.name" placeholder="请输入角色名"></Input>
                </FormItem>
                <FormItem label="角色说明" prop="remark">
                    <Input type="text" v-model="roleForm.remark" placeholder="请输入角色说明"></Input>
                </FormItem>
                <FormItem :label-width="0" style="text-align: center">
                    <Button @click="saveRole('roleForm')" type="primary">保存</Button>
                    <Button @click="resetRole('roleForm')" type="primary">重置</Button>
                </FormItem>
            </Form>
        </Drawer>
        <Drawer title="群组管理" :closable="true" :width="40" v-model="roleGroupDrawer" :on-close="resetRoleGroups">
            <Transfer filterable
                      :data="roleGroupTable"
                      :target-keys="targetRoleGroupIds"
                      :titles="['所有群组','已有群组']"
                      :render-format="formatRoleGroups"
                      :list-style="roleGroupTransferStyle"
                      @on-change="changeRoleGroups">
            </Transfer>
            <div style="text-align: center; margin-top: 10px">
                <Button type="primary" @click="saveRoleGroups">保存</Button>
            </div>
        </Drawer>
    </div>
</template>
<script>

    import role from '../../../api/role'
    import group from '../../../api/group'

    export default {
        data() {
            return {
                roleTableHeader: [
                    {
                        title: '编号',
                        key: 'id'
                    },
                    {
                        title: '角色名',
                        key: 'name'
                    },
                    {
                        title: '角色说明',
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
                        fixed: 'right',
                        width: 220,
                        align: 'center'
                    }
                ],
                roleTable: [],
                saveRoleDrawer: false,
                saveRoleDrawerTitle: '添加角色',
                roleForm: {
                    id: null,
                    name: '',
                    remark: ''
                },
                roleValidate: {
                    name: [
                        {
                            required: true,
                            message: '角色名称不能为空！',
                            trigger: 'blur'
                        }
                    ]
                },
                roleQueryForm: {
                    name: null,
                    pageSize: 10,
                    page: 1,
                    total: null
                },
                roleGroupDrawer: false,
                selectRoleRow: null,
                targetRoleGroupIds: [],
                roleGroupTable: [],
                roleGroupTransferStyle: {
                    height: '500px',
                    width: '200px',
                    margin: '0 0 0 5%'
                },
            }
        },
        mounted() {
            this.findRoles();
        },
        methods: {
            saveRole(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        role.doSaveRole(this, {
                            id: this.roleForm.id,
                            name: this.roleForm.name,
                            remark: this.roleForm.remark
                        }).then(res => {
                            if (res) {
                                this.saveRoleDrawer = false;
                                this.saveRoleDrawerTitle = '';
                                this.findRoles();
                                this.resetRole('roleForm');
                            }
                        });
                    } else {
                        this.$Message.error("您输入的参数有误，请检查后重新输入！")
                    }
                })
            },
            beforeSaveRole(action, row) {
                this.saveRoleDrawer = true;
                if (action === 'insert') {
                    this.saveRoleDrawerTitle = '添加角色';
                    this.roleForm.id = null;
                } else {
                    this.saveRoleDrawerTitle = '更新角色';
                    this.roleForm.id = row.id;
                    this.roleForm.name = row.name;
                    this.roleForm.remark = row.remark;
                }
            },
            resetRole(name) {
                this.$refs[name].resetFields();
            },
            deleteRole(row) {
                this.$Modal.confirm({
                    title: '确认删除',
                    content: '您确认要删除该条记录吗？',
                    closable: true,
                    onOk: () => {
                        role.doDeleteRole(this, row.id).then(res => {
                            if (res) {
                                this.findRoles();
                            }
                        })
                    }
                })
            },
            changeCurrentPage(current) {
                this.roleQueryForm.page = current;
                this.findRoles();
            },
            findRoles() {
                role.doFindRoles(this, {
                    page: this.roleQueryForm.page,
                    pageSize: this.roleQueryForm.pageSize,
                    name: this.roleQueryForm.name
                }).then(response => {
                    if (response != null) {
                        this.roleTable = response.records;
                        this.roleQueryForm.total = response.total;
                    }
                })
            },
            doGetAllGroups() {
                this.roleGroupTable = []
                group.doListGroups(this, {}).then(res => {
                    if (res != null) {
                        res.forEach((item) => {
                            this.roleGroupTable.push({
                                key: item.id,
                                label: item.name
                            })
                        })
                    }
                })
            },
            doGetRoleGroups(roleId) {
                this.targetRoleGroupIds = []
                role.doListRoleGroups(this, roleId).then(res => {
                    if (res != null) {
                        res.forEach((item) => {
                            this.targetRoleGroupIds.push(item.id)
                        })
                    }
                });
            },
            roleGroup(row) {
                this.roleGroupDrawer = true;
                this.selectRoleRow = row;
                this.doGetAllGroups();
                this.doGetRoleGroups(row.id);
            },
            resetRoleGroups() {
                this.selectRoleRow = null
                this.targetRoleGroupIds = []
            },
            formatRoleGroups(item) {
                return item.label
            },
            changeRoleGroups(newTargetKeys, direction, moveKeys) {
                this.targetRoleGroupIds = newTargetKeys;
            },
            saveRoleGroups() {
                role.doSaveRoleGroups(this, {
                    roleId: this.selectRoleRow.id,
                    groupIds: this.targetRoleGroupIds
                }).then(res => {
                    if (res) {
                        this.roleGroup(this.selectRoleRow);
                    }
                })
            }
        }
    }
</script>
