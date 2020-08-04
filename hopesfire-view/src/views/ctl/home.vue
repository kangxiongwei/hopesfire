<style scoped>
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
                </Row>
            </Header>
            <Layout id="ctl_main">
                <Sider ref="nav" collapsible :collapsed-width="78" v-model="isCollapsed" hide-trigger>
                    <Menu style="height: 100%" active-name="数据大盘" width="auto">
                        <Submenu name="系统首页">
                            <template slot="title">
                                <Icon type="ios-menu"/>
                                系统首页
                            </template>
                            <MenuItem name="数据大盘" to="/ctl/root/dashboard">数据大盘</MenuItem>
                        </Submenu>
                        <Submenu name="权限控制">
                            <template slot="title">
                                <Icon type="ios-settings"/>
                                权限控制
                            </template>
                            <MenuItem name="2-1" to="/ctl/auth/user">用户管理</MenuItem>
                            <MenuItem name="2-2" to="/ctl/auth/role">角色管理</MenuItem>
                            <MenuItem name="2-3" to="/ctl/auth/group">群组管理</MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Content id="ctl_content">
                    <Breadcrumb id="ctl_breadcrumb">
                        <BreadcrumbItem v-for="path in currentPath" :key="path">{{ path }}</BreadcrumbItem>
                    </Breadcrumb>
                    <Card id="ctl_card" dis-hover padding="0" style="padding-left: 5px">
                        <router-view></router-view>
                    </Card>
                </Content>
            </Layout>
            <Footer id="ctl_footer">
                Copyright &copy; 2020-2025 希望之火 | 京ICP备20021406号
            </Footer>
        </Layout>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                isCollapsed: false,
                currentPath: []
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
            }
        },
        watch: {
            '$route'(newRoute) {
                this.setCurrentPath(newRoute);
            }
        },
        mounted() {
            this.setCurrentPath(this.$route);
        }
    }
</script>
