<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login">
        <div class="login-con">
            <Card icon="log-in" title="欢迎登录" :bordered="false">
                <div class="form-con">
                    <Form :model="loginUser">
                        <FormItem prop="username">
                            <Input v-model="loginUser.username" placeholder="请输入用户名">
                            <span slot="prepend">
                              <Icon :size="16" type="ios-person"></Icon>
                            </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="loginUser.password" placeholder="请输入密码">
                            <span slot="prepend">
                              <Icon :size="14" type="md-lock"></Icon>
                            </span>
                            </Input>
                        </FormItem>
                        <FormItem style="text-align: center">
                            <Button @click="handleSubmit" type="primary" long>登录</Button>
                        </FormItem>
                    </Form>
                    <p class="register-tip">如果您还没有账号，请先<a @click="handleRegister">注册</a></p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>

    import login from '../../api/login'
    import {getToken} from "../../libs/store";

    export default {
        components: {},
        data() {
            return {
                loginUser: {
                    username: '',
                    password: ''
                }
            }
        },
        mounted() {
            this.initLoginUser();
        },
        methods: {
            initLoginUser() {
                let token = getToken();
                if (token !== '') {
                    let user = JSON.parse(token);
                    this.loginUser.username = user.username;
                    this.loginUser.password = user.password;
                    this.handleSubmit();
                }
            },
            handleSubmit() {
                login.login(this, this.loginUser);
            },
            handleRegister() {
                this.$router.push({path: "/register"})
            }
        }
    }
</script>
