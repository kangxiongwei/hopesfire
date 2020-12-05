<style scoped lang="less">
    @import "../../styles/home.less";

    #sider_icon {
        width: 100%;
        height: 24px;
        text-align: center;
        line-height: 24px;
        position: absolute;
        bottom: 0;
    }

    .realm_link {
        color: white;
    }

    .realm_link:hover {
        color: #2d8cf0;
    }

</style>
<template>
    <div id="ctl_layout">
        <Layout style="height: 100%;">
            <Header id="ctl_header">
                <Row type="flex" justify="start">
                    <Col :span="2" style="width: 100px; text-align: center">
                        <img class="header_nav_logo" src="../../images/logo.png" @click="goToIndexPage"/>
                    </Col>
                    <Col :span="4" style="width: 190px">
                        <h2 class="header_nav_title">hopesfire</h2>
                    </Col>
                    <Col :md="16">
                        <div></div>
                    </Col>
                    <Col :span="1" style="text-align: center">
                        <Avatar :src="loginUser.headImg" style="width: 30px; height: 30px; line-height: 30px"></Avatar>
                    </Col>
                    <Col span="2" class="header_login" style="text-align: left; width: 110px">
                        <Dropdown trigger="click" placement="top" @on-click="handleLoginInfo">
                            <a href="javascript:void(0)" style="color: white;">
                                {{loginUser.username}}
                                <Icon type="ios-arrow-down"></Icon>
                            </a>
                            <DropdownMenu slot="list">
                                <DropdownItem name="userInfo">个人信息</DropdownItem>
                                <DropdownItem name="logout">退出登录</DropdownItem>
                            </DropdownMenu>
                        </Dropdown>
                    </Col>
                </Row>
            </Header>
            <Layout id="ctl_main">
                <Sider style="background: #F5F7F9" ref="nav" collapsible
                       breakpoint="md" :collapsed-width="78" v-model="isCollapsed" hide-trigger>
                    <!--侧边栏导航组件-->
                    <SiderMenu :isCollapsed="isCollapsed"></SiderMenu>
                    <!--侧边栏伸缩图标-->
                    <Icon id="sider_icon" :type="navBarType" @click.native="collapsedSider"></Icon>
                </Sider>

                <Content id="ctl_content">
                    <Breadcrumb id="ctl_breadcrumb">
                        <BreadcrumbItem v-for="path in currentPath" :key="path">{{ path }}</BreadcrumbItem>
                    </Breadcrumb>
                    <Card id="ctl_card" dis-hover :padding="0" style="padding-left: 5px">
                        <router-view></router-view>
                    </Card>
                </Content>
            </Layout>
            <Footer id="ctl_footer">
                Copyright &copy; 2020-2025 希望之火 | <a class="realm_link" :href="realmUrl" target="_blank">京ICP备20021406号</a>
            </Footer>
        </Layout>
        <Modal v-model="showUserInfo" closable draggable title="个人信息" ok-text="确认" cancel-text="取消">
            <p>用户名: {{ loginUser.username }}</p>
            <p>用户昵称：{{ loginUser.nickname }}</p>
            <p>电话：{{ loginUser.telephone}}</p>
            <p>住址：{{loginUser.address}}</p>
        </Modal>
    </div>
</template>
<script>

    import {getToken} from "../../libs/store";
    import login from "../../api/login";
    import SiderMenu from "./sider_menu";

    export default {
        components: {SiderMenu},
        data() {
            return {
                isCollapsed: false,
                currentPath: [],
                navBarType: 'ios-arrow-back',
                navBarClass: 'nav-bar-open',
                loginUser: {},
                showUserInfo: false,
                realmUrl: "https://beian.miit.gov.cn"
            }
        },
        methods: {
            setCurrentPath(route) {
                this.currentPath = [];
                route.matched.forEach(item => {
                    this.currentPath.push(item.name);
                });
            },
            goToIndexPage() {
                this.$router.push({name: "index"});
            },
            getLoginUser() {
                let token = getToken();
                if (token === '') {
                    this.loginUser.username = '';
                } else {
                    this.loginUser = JSON.parse(token);
                }
            },
            handleLoginInfo(name) {
                if (name === 'userInfo') {
                    this.showUserInfo = true;
                } else if (name === 'logout') {
                    //退出登录
                    login.logout(this);
                }
            },
            collapsedSider() {
                if (this.isCollapsed) {
                    this.navBarType = 'ios-arrow-back'
                    this.navBarClass = 'nav-bar-open'
                } else {
                    this.navBarType = 'ios-arrow-forward'
                    this.navBarClass = 'nav-bar-close'
                }
                this.$refs['nav'].toggleCollapse();
            }
        },
        watch: {
            '$route'(newRoute) {
                this.setCurrentPath(newRoute);
            }
        },
        mounted() {
            this.setCurrentPath(this.$route);
            this.getLoginUser();
        }
    }
</script>
