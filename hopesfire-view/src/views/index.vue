<style scoped type="text/css">
    @import "../styles/index.less";
</style>
<template>
    <div id="main">
        <Layout style="width: 100%; height: 100%">
            <Header id="header">
                <Row type="flex" justify="start" class="header_nav_row">
                    <Col span="2" :class="ctlLogoClass">
                        <div @mouseover="selectHeaderNav('logo')" @mouseout="unSelectHeaderNav('logo')">
                            <img class="header_logo_img" src="../images/logo.png" alt="logo"/>
                        </div>
                    </Col>
                    <Col span="2" :class="homeNavClass">
                        <div @mouseover="selectHeaderNav('home')" @mouseout="unSelectHeaderNav('home')">
                            首页
                        </div>
                    </Col>
                    <Col span="2" :class="ctlNavClass">
                        <div @mouseover="selectHeaderNav('ctl')" @mouseout="unSelectHeaderNav('ctl')" @click="selectCtlPage">
                            控制台
                        </div>
                    </Col>
                    <Col span="14">
                        <div></div>
                    </Col>
                    <Col span="2" :class="ctlLoginClass">
                        <div @mouseover="selectHeaderNav('login')" @mouseout="unSelectHeaderNav('login')" @click="handleLoginClick">
                            <Dropdown trigger="click" placement="top" @on-click="handleLoginInfo">
                                <a href="javascript:void(0)" style="color: white;">
                                    {{ctlLoginUser}}
                                    <Icon type="ios-arrow-down"></Icon>
                                </a>
                                <DropdownMenu slot="list">
                                    <DropdownItem name="userInfo">个人信息</DropdownItem>
                                    <DropdownItem name="logout">退出登录</DropdownItem>
                                </DropdownMenu>
                            </Dropdown>
                        </div>
                    </Col>
                </Row>
            </Header>
            <Content id="content">
                <Row type="flex" justify="center" style="width: 100%; height: 100%">
                    <Col span="24" style="width: 100%; height: 100%">
                        <img style="width: 100%; height: 100%" src="../images/login-bg.jpg"/>
                    </Col>
                </Row>
            </Content>
            <Footer id="footer">
                <Row type="flex" justify="center">
                    <Col span="24">Copyright &copy; 2020-2025 希望之火 | 京ICP备20021406号</Col>
                </Row>
            </Footer>
        </Layout>
        <Modal v-model="showUserInfo" closable draggable title="个人信息" ok-text="确认" cancel-text="取消">
            <p>用户名: {{ loginUserInfo.username }}</p>
            <p>用户昵称：{{ loginUserInfo.nickname }}</p>
            <p>电话：{{ loginUserInfo.telephone}}</p>
            <p>住址：{{loginUserInfo.address}}</p>
        </Modal>
    </div>
</template>
<script>

    import {getToken} from '../libs/store'
    import login from '../api/login'

    export default {
        data() {
            return {
                ctlLogoClass: 'header_nav',
                homeNavClass: 'header_nav',
                ctlNavClass: 'header_nav',
                ctlLoginClass: 'header_login',
                ctlLoginUser: '',
                showUserInfo: false,
                loginUserInfo: {}
            }
        },
        mounted() {
            this.handleLoginClick();
        },
        methods: {
            selectHeaderNav(type) {
                if (type === 'home') {
                    this.homeNavClass = 'header_nav_hover';
                } else if (type === 'ctl') {
                    this.ctlNavClass = 'header_nav_hover';
                } else if (type === 'logo') {
                    this.ctlLogoClass = 'header_nav'
                } else if (type === 'login') {
                    this.ctlLoginClass = 'header_login_hover'
                }
            },
            unSelectHeaderNav(type) {
                if (type === 'home') {
                    this.homeNavClass = 'header_nav';
                } else if (type === 'ctl') {
                    this.ctlNavClass = 'header_nav';
                } else if (type === 'logo') {
                    this.ctlLogoClass = 'header_nav'
                } else if (type === 'login') {
                    this.ctlLoginClass = 'header_login'
                }
            },
            handleLoginClick() {
                let loginUser = getToken();
                if (loginUser === '') {
                    this.$router.push({path: '/login'});
                } else {
                    let user = JSON.parse(loginUser);
                    this.ctlLoginUser = user.username;
                }
            },
            handleLoginInfo(name) {
                if (name === 'userInfo') {
                    this.showUserInfo = true;
                    let user = getToken();
                    this.loginUserInfo = JSON.parse(user);
                } else if (name === 'logout') {
                    //退出登录
                    login.logout(this);
                }
            },
            selectCtlPage() {
                this.$router.push({path: '/ctl'});
            }
        }
    }
</script>
