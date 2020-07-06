<style scoped>
    .index {
        width: 100%;
        height: 100%;
        background: #2d2d2d;
    }

    .header_nav {
        width: 100px;
        margin: 10px 0 0 10px;
        height: 30px;
        line-height: 30px;
        text-align: center;
        color: white;
    }

    .header_nav_hover {
        width: 100px;
        margin: 10px 0 0 10px;
        border-top: 1px solid white;
        border-bottom: 1px solid white;
        height: 30px;
        line-height: 30px;
        text-align: center;
        color: white;
    }

    .main {
        width: 100%;
        height: 100%;
        text-align: center;
    }

    .header_logo_img {
        width: 30px;
        height: 30px;
    }

    .header_login {
        width: 100px;
        height: 30px;
        line-height: 30px;
        text-align: center;
        color: white;
        margin: 0;
        padding: 0;
    }

    .header_login_hover {
        width: 100px;
        margin: 0;
        border-top: 1px solid white;
        border-bottom: 1px solid white;
        height: 30px;
        line-height: 30px;
        text-align: center;
        color: white;
    }

    .header_login_dropdown * {
        color: white;
    }

    .header_login_dropdown_menu {
        height: 30px;
    }

    .header_login_dropdown_item {
        height: 30px;
        color: #2d2d2d;
    }

</style>
<template>
    <div class="index">
        <Row type="flex" justify="start" align="middle" :gutter="16">
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
            <Col span="18">
                <div></div>
            </Col>
            <Col span="2" :class="ctlLoginClass">
                <div @mouseover="selectHeaderNav('login')" @mouseout="unSelectHeaderNav('login')" @click="handleLoginClick">
                    <Dropdown class="header_login_dropdown">
                        <a href="javascript:void(0)">
                            {{ctlLoginUser}}
                            <Icon type="ios-arrow-down"></Icon>
                        </a>
                        <DropdownMenu slot="list" class="header_login_dropdown_menu">
                            <DropdownItem class="header_login_dropdown_item">个人信息</DropdownItem>
                            <DropdownItem class="header_login_dropdown_item">退出登录</DropdownItem>
                        </DropdownMenu>
                    </Dropdown>
                </div>
            </Col>
        </Row>
        <br>
        <Row type="flex" justify="center" style="width: 100%; height: auto;">
            <Col span="24" class="main">正文</Col>
        </Row>
    </div>
</template>
<script>

    import {getToken} from '../libs/store'

    export default {
        data() {
            return {
                ctlLogoClass: 'header_nav',
                homeNavClass: 'header_nav',
                ctlNavClass: 'header_nav',
                ctlLoginClass: 'header_login',
                ctlLoginUser: '登录'
            }
        },
        mounted() {
            this.handleLoginClick();
        },
        methods: {
            handleStart() {
                this.$Modal.info({
                    title: 'Bravo',
                    content: 'Now, enjoy the convenience of View UI.'
                });
            },
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
            selectCtlPage() {
                this.$router.push({path: '/ctl'});
            }
        }
    }
</script>
