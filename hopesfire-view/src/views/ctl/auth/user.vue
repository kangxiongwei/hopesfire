<style scoped>

</style>
<template>
    <div class="content">
        <Table stripe border :columns="userTableHeader" :data="userTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" style="margin-right: 5px" @click="updateUser(row)">编辑</Button>
                <Button type="error" size="small" @click="deleteUser(row)">删除</Button>
            </template>
        </Table>
        <Drawer title="更新用户" :closable="true" :width="40" v-model="updateUserDrawer">
            <Form ref="userForm" :rules="userValidate" :model="userForm" :label-width="70" label-position="left">
                <FormItem label="用户名" prop="username">
                    <Input type="text" v-model="userForm.username" placeholder="请输入用户名"></Input>
                </FormItem>
                <FormItem label="密码" prop="password">
                    <Input type="password" v-model="userForm.password" placeholder="请输入密码"></Input>
                </FormItem>
                <FormItem label="昵称" prop="nickname">
                    <Input type="text" v-model="userForm.nickname" placeholder="请输入昵称"></Input>
                </FormItem>
                <FormItem label="头像" prop="headImg">
                    <Upload action="/uploadHeadImg" v-model="userForm.headImg">
                        <Button icon="ios-cloud-upload-outline">上传头像</Button>
                    </Upload>
                </FormItem>
                <FormItem label="性别" prop="sex">
                    <RadioGroup v-model="userForm.sex">
                        <Radio :label="1">男</Radio>
                        <Radio :label="2">女</Radio>
                    </RadioGroup>
                </FormItem>
                <FormItem label="电话" prop="telephone">
                    <Input type="tel" v-model="userForm.telephone" placeholder="请输入电话号码"></Input>
                </FormItem>
                <FormItem label="地址" prop="address">
                    <Input type="text" v-model="userForm.address" placeholder="请输入住址"></Input>
                </FormItem>
                <FormItem :label-width="0" style="text-align: center">
                    <Button @click="saveUser('userForm')" type="primary">更新</Button>
                    <Button @click="doReset('userForm')" type="primary">重置</Button>
                </FormItem>
            </Form>
        </Drawer>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                userTable: [],
                userTableHeader: [
                    {
                        title: '编号',
                        key: 'id'
                    },
                    {
                        title: '姓名',
                        key: 'username'
                    },
                    {
                        title: '昵称',
                        key: 'nickname'
                    },
                    {
                        title: '性别',
                        key: 'sex',
                        render: (h, params) => {
                            return h('div',
                                this.formatSex(params.row)
                            )
                        }
                    },
                    {
                        title: '电话',
                        key: 'telephone'
                    },
                    {
                        title: '地址',
                        key: 'address',
                        tooltip: true
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 150,
                        align: 'center',
                        fixed: 'right'
                    }
                ],
                updateUserDrawer: false,
                deleteUserModal: false,
                userForm: {
                    id: '',
                    username: '',
                    password: '',
                    nickname: '',
                    sex: '',
                    headImg: '',
                    telephone: '',
                    address: ''
                },
                userValidate: {
                    username: [
                        {
                            required: true,
                            message: "用户名不能为空！",
                            trigger: "blur"
                        }
                    ],
                    password: [
                        {
                            required: true,
                            message: "密码不能为空！",
                            trigger: "blur"
                        }
                    ],
                    sex: [
                        {
                            required: true,
                            message: "性别不能为空！",
                            trigger: "change",
                            type: 'number'
                        }
                    ]
                }
            }
        },
        mounted() {
            this.findUsers();
        },
        methods: {
            findUsers() {
                this.$post('/ctl/auth/user/find', {"page": 1, "pageSize": 10})
                    .then(response => {
                        let data = response.data;
                        if (data.code === 200) {
                            this.userTable = data.data.records;
                        }
                    })
                    .catch(error => {
                        this.$Modal.error({
                            title: '错误提示',
                            content: '删除失败，' + error
                        })
                    });
            },
            formatSex(row) {
                return row.sex === 1 ? '男' : (row.sex === 2 ? '女' : '无')
            },
            updateUser(row) {
                this.updateUserDrawer = true;
                this.userForm.id = row.id;
                this.userForm.username = row.username;
                this.userForm.password = row.password;
                this.userForm.nickname = row.nickname;
                this.userForm.telephone = row.telephone;
                this.userForm.sex = row.sex;
                this.userForm.headImg = row.headImg;
                this.userForm.address = row.address;
            },
            saveUser(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        this.$post('/ctl/auth/user/save', {
                            id: this.userForm.id,
                            username: this.userForm.username,
                            password: this.userForm.password,
                            nickname: this.userForm.nickname,
                            sex: this.userForm.sex,
                            headImg: this.userForm.headImg,
                            telephone: this.userForm.telephone,
                            address: this.userForm.address
                        }).then(res => {
                            if (res != null && res.data.code === 200) {
                                this.updateUserDrawer = false
                                this.findUsers();
                            } else {
                                this.$Message.error('更新用户失败！');
                            }
                        });
                    } else {
                        this.$Message.error('您输入的参数有误，请确认后再提交！');
                    }
                })
            },
            deleteUser(row) {
                this.$Modal.confirm({
                    title: '确认删除',
                    content: '请问您确认要删除这条记录吗？',
                    closable: true,
                    okText: '删除',
                    onOk: () => {
                        this.doDelete(row);
                    }
                });
            },
            doReset(name) {
                this.$refs[name].resetFields();
            },
            doDelete(row) {
                this.$get('/ctl/auth/user/delete/' + row.id)
                    .then(response => {
                        if (response.data.code === 200) {
                            //重新加载表格
                            this.findUsers()
                        }
                    })
                    .catch(error => {
                        this.$Message.error('删除用户失败！' + error);
                    })
            }
        }
    }
</script>
