<style scoped>

</style>
<template>
    <div class="content">
        <Row>
            <Col span="22">
                <Form size="small" :label-width="70" inline>
                    <FormItem label="角色名称">
                        <Input size="small" type="text" v-model="queryRoleForm.name"></Input>
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
        <Table stripe border  size="small" :columns="roleTableHeader" :data="roleTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="beforeSaveRole('update',row)">编辑</Button>
                <Button type="error" size="small" @click="deleteRole(row)">删除</Button>
            </template>
        </Table>
        <template>
            <Page style="margin-top: 5px" size="small" show-total show-elevator
                  :total="queryRoleForm.total"
                  :page-size="queryRoleForm.pageSize"
                  :current="queryRoleForm.page"
                  @change="findRoles"
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
    </div>
</template>
<script>
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
                        width: 150
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
                queryRoleForm: {
                    name: null,
                    pageSize: 10,
                    page: 1,
                    total: null
                }
            }
        },
        mounted() {
            this.findRoles();
        },
        methods: {
            doSaveRole() {
                this.$post('/ctl/auth/role/save', {
                    id: this.roleForm.id,
                    name: this.roleForm.name,
                    remark: this.roleForm.remark
                }).then(response => {
                    if (response != null && response.data.code === 200) {
                        this.findRoles();
                        this.resetRole('roleForm');
                    } else {
                        this.$Message.error("保存角色失败！")
                    }
                })
            },
            doDeleteRole(id) {
                this.$get('/ctl/auth/role/delete/' + id)
                    .then(response => {
                        if (response != null && response.data.code === 200) {
                            this.findRoles();
                        } else {
                            this.$Message.error("删除角色失败！")
                        }
                    })
            },
            saveRole(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.doSaveRole();
                        this.saveRoleDrawer = false;
                        this.saveRoleDrawerTitle = '';
                    } else {
                        this.$Message.error("您输入的参数有误，请检查后重新输入！")
                    }
                })
            },
            beforeSaveRole(action, row) {
                this.saveRoleDrawer = true;
                if (action === 'insert') {
                    this.saveRoleDrawerTitle = '添加角色';
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
                        this.doDeleteRole(row.id)
                    }
                })
            },
            findRoles() {
                this.$post('/ctl/auth/role/find', {
                    page: this.queryRoleForm.page,
                    pageSize: this.queryRoleForm.pageSize,
                    name: this.queryRoleForm.name
                }).then(response => {
                    let data = response.data;
                    if (data.code === 200) {
                        this.roleTable = data.data.records;
                        this.queryRoleForm.total = data.data.total;
                    }
                })
            }
        }
    }
</script>
