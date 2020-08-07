<style scoped lang="less">
    @import "../../styles/home.less";
</style>
<template>
    <div id="ctl_layout">
        <Layout style="height: 100%;">
            <Header id="ctl_header">
                <Row>
                    <Col :span="1" style="width: 100px; text-align: center">
                        <img class="header_nav_logo" src="../../images/logo.png" @click="goToIndexPage"/>
                    </Col>
                    <Col :span="10">
                        <h2 class="header_nav_title">欢迎使用hopesfire系统！</h2>
                    </Col>
                    <Col span="2" :offset="10" class="header_login" style="text-align: center">
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
                <Sider ref="nav" collapsible :collapsed-width="78" v-model="isCollapsed" hide-trigger>
                    <Menu style="height: 100%" width="auto" accordion :open-names="['系统首页']" :class="this.navBarClass">
                        <div id="ctl_nav_bar" @click="collapsedSider">
                            <Icon :type="navBarType" size="20"/>
                        </div>
                        <Submenu name="系统首页">
                            <template slot="title">
                                <Icon type="ios-menu"/>
                                <span>系统首页</span>
                            </template>
                            <MenuItem name="数据大盘" to="/ctl/root/dashboard">数据大盘</MenuItem>
                        </Submenu>
                        <Submenu name="权限控制">
                            <template slot="title">
                                <Icon type="ios-settings"/>
                                <span>权限控制</span>
                            </template>
                            <MenuItem name="2-1" to="/ctl/auth/user">用户管理</MenuItem>
                            <MenuItem name="2-2" to="/ctl/auth/role">角色管理</MenuItem>
                            <MenuItem name="2-3" to="/ctl/auth/group">群组管理</MenuItem>
                        </Submenu>
                        <Submenu name="盼盼的饮食管理">
                            <template slot="title">
                                <Icon type="ios-alarm"/>
                                <span>盼盼的饮食管理</span>
                            </template>
                            <MenuItem name="3-1" to="/ctl/meal/category">盼盼的菜品管理</MenuItem>
                            <MenuItem name="3-2" to="/ctl/user/meal">盼盼的饮食记录</MenuItem>
                        </Submenu>
                    </Menu>
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
                Copyright &copy; 2020-2025 希望之火 | 京ICP备20021406号
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

    export default {
        data() {
            return {
                isCollapsed: false,
                currentPath: [],
                navBarType: 'ios-arrow-back',
                navBarClass: 'nav-bar-open',
                loginUser: {},
                showUserInfo: false
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
                /*if (this.isCollapsed) {
                    this.navBarType = 'ios-arrow-back'
                    this.navBarClass = 'nav-bar-open'
                } else {
                    this.navBarType = 'ios-arrow-forward'
                    this.navBarClass = 'nav-bar-close'
                }*/
                //this.$refs['nav'].toggleCollapse();
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
