<style scoped type="text/css">
    @import "../styles/index.less";
</style>
<template>
    <div id="main">
        <Layout style="width: 100%; height: 100%">
            <Header id="header">
                <Row type="flex" justify="start" class="header_nav_row">
                    <Col span="2" class="header_logo_nav">
                        <img class="header_logo_img" src="../images/logo.png" alt="logo"/>
                    </Col>
                    <Col span="2" class="header_nav">
                        <div @click="activePage('HomePage')">首页</div>
                    </Col>
                    <Col span="2" class="header_nav">
                        <div @click="activePage('ctl')">控制台</div>
                    </Col>
                    <Col span="2" class="header_nav">
                        <div @click="activePage('OurNews')">最新资讯</div>
                    </Col>
                    <Col span="2" class="header_nav">
                        <div @click="activePage('AboutUs')">关于我们</div>
                    </Col>
                    <Col :md="2" :lg="4" :xl="8" :xxl="10">

                    </Col>
                    <Col :span="1" style="text-align: center">
                        <Avatar :src="loginUserInfo.headImg" style="width: 30px; height: 30px; line-height: 30px"></Avatar>
                    </Col>
                    <Col span="2" class="header_login" style="margin-left: 0">
                        <div @click="handleLoginClick">
                            <Dropdown trigger="click" placement="top" @on-click="handleLoginInfo">
                                <a href="javascript:void(0)" style="color: white;">
                                    {{loginUserInfo.username}}
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
                <Row style="width: 100%; height: 100%">
                    <Col :span="24" style="width: 100%; height: 100%">
                        <HomePage v-if="selectPage === 'HomePage'"></HomePage>
                        <OurNews v-if="selectPage === 'OurNews'"></OurNews>
                        <AboutUs v-if="selectPage === 'AboutUs'"></AboutUs>
                    </Col>
                </Row>
            </Content>
            <Footer id="footer">
                <Row type="flex" justify="center">
                    <Col span="24">Copyright &copy; 2020-2025 希望之火 | <a class="realm_link" :href="realmUrl" target="_blank">京ICP备20021406号</a></Col>
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
    import HomePage from './home/HomePage'
    import OurNews from "./home/OurNews";
    import AboutUs from "./home/AboutUs"

    export default {

        components: {
            HomePage,
            OurNews,
            AboutUs
        },

        data() {
            return {
                showUserInfo: false,
                realmUrl: "https://beian.miit.gov.cn",
                loginUserInfo: {
                    username: '',
                    headImg: ''
                },
                selectPage: 'HomePage'
            }
        },
        mounted() {
            this.handleLoginClick();
        },
        methods: {
            handleLoginClick() {
                let loginUser = getToken();
                if (loginUser === '') {
                    this.$router.push({path: '/login'});
                } else {
                    this.loginUserInfo = JSON.parse(loginUser);
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
            activePage(type) {
                if (type === 'ctl') {
                    this.$router.push({path: '/ctl'});
                } else {
                    this.selectPage = type;
                }
            }
        }
    }
</script>
