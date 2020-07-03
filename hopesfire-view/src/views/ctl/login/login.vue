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
                    <p class="login-tip">{{loginErrorMsg}}</p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
    export default {
        components: {},
        data() {
            return {
                loginUser: {
                    username: '',
                    password: ''
                },
                loginErrorMsg: ''
            }
        },
        methods: {
            handleSubmit() {
                this.loginErrorMsg = '';
                this.$post('/login', {
                    username: this.loginUser.username,
                    password: this.loginUser.password
                }).then(res => {
                    if (res != null && res.data.code === 200) {
                        //更新本地状态和Cookie
                        this.$store.commit('setLoginToken', res.data.data);
                        //登录成功，跳转到后台
                        this.$router.push({path: "/ctl"})
                    } else {
                        this.loginErrorMsg = res.data.message;
                    }
                });
            }
        }
    }
</script>

<style>

</style>
