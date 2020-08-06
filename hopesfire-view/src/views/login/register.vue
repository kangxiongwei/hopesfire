<style lang="less">
    @import './login.less';
    .login {
        &-con {
            top: 60%;
        }
    }
</style>

<template>
    <div class="login">
        <div class="login-con">
            <Card icon="log-in" title="欢迎注册" :bordered="false">
                <div class="form-con">
                    <Form ref="loginUser" :model="loginUser" :rules="userValidate" :label-width="70" label-position="left">
                        <FormItem label="用户名" prop="username">
                            <Input type="text" v-model="loginUser.username" placeholder="请输入用户名"></Input>
                        </FormItem>
                        <FormItem label="密码" prop="password">
                            <Input type="password" v-model="loginUser.password" placeholder="请输入密码"></Input>
                        </FormItem>
                        <FormItem label="昵称" prop="nickname">
                            <Input type="text" v-model="loginUser.nickname" placeholder="请输入昵称"></Input>
                        </FormItem>
                        <FormItem label="头像" prop="headImg">
                            <Upload action="/uploadHeadImg" v-model="loginUser.headImg">
                                <Button icon="ios-cloud-upload-outline">上传头像</Button>
                            </Upload>
                        </FormItem>
                        <FormItem label="性别" prop="sex">
                            <RadioGroup v-model="loginUser.sex">
                                <Radio label="1">男</Radio>
                                <Radio label="2">女</Radio>
                            </RadioGroup>
                        </FormItem>
                        <FormItem label="电话" prop="telephone">
                            <Input type="tel" v-model="loginUser.telephone" placeholder="请输入电话号码"></Input>
                        </FormItem>
                        <FormItem label="地址" prop="address">
                            <Input type="text" v-model="loginUser.address" placeholder="请输入住址"></Input>
                        </FormItem>
                        <FormItem :label-width="0" style="text-align: center">
                            <Button @click="handleSubmit('loginUser')" type="primary">注册</Button>
                            <Button @click="handleReset('loginUser')" type="primary">重置</Button>
                        </FormItem>
                    </Form>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>

    import login from '../../api/login'

    export default {
        components: {},
        data() {
            return {
                loginUser: {
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
                            trigger: "change"
                        }
                    ]
                }
            }
        },
        methods: {
            handleSubmit(name) {
                this.$refs[name].validate((valid) => {
                    if (valid) {
                        login.register(this, this.loginUser);
                    } else {
                        this.$Message.error('您输入的参数有误，请确认后再提交！');
                    }
                })
            },
            handleReset(name) {
                this.$refs[name].resetFields();
            }
        }
    }
</script>

<style>

</style>
