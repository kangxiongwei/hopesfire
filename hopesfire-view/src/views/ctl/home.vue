<style scoped>
    #ctl_layout {
        width: 100%;
        height: 100%;
        border: 1px solid #d7dde4;
        background: #f5f7f9;
        position: relative;
        overflow: hidden;
    }

    #ctl_header {
        height: 64px;
        background: #515a6e;
        padding: 0;
    }

    #ctl_footer {
        width: 100%;
        text-align: center;
    }

    #ctl_content {
        width: 100%;
        height: 100%;
        margin: 5px 0 0 5px;
    }

    #ctl_breadcrumb {
        width: 100%;
        margin: 5px 0 5px 5px;
    }

    #ctl_card {
        width: 100%;
        height: 95%;
    }

    #ctl_nav {

    }

</style>
<template>
    <div id="ctl_layout">
        <Layout style="width: 100%; height: 100%">
            <Header id="ctl_header">
                <h2 style="color: #fff; position: relative; left: 10px;">欢迎使用hopesfire系统！</h2>
            </Header>
            <Layout style="width: 100%; height: 100%;">
                <Sider id="ctl_nav" ref="nav" collapsible :collapsed-width="78" v-model="isCollapsed" hide-trigger>
                    <Menu style="height: 100%" active-name="1-1" width="auto">
                        <Submenu name="system">
                            <template slot="title">
                                <Icon type="ios-menu" />
                                系统首页
                            </template>
                            <MenuItem name="1-1" to="/ctl/auth/user">数据大盘</MenuItem>
                        </Submenu>
                        <Submenu name="auth">
                            <template slot="title">
                                <Icon type="ios-settings" />
                                权限控制
                            </template>
                            <MenuItem name="1-1" to="/ctl/auth/user">用户管理</MenuItem>
                            <MenuItem name="1-2" to="/ctl/auth/role">角色管理</MenuItem>
                            <MenuItem name="1-3" to="/ctl/auth/group">群组管理</MenuItem>
                        </Submenu>
                    </Menu>
                </Sider>
                <Content id="ctl_content" style="width: 100%">
                    <Breadcrumb id="ctl_breadcrumb">
                        <BreadcrumbItem>Home</BreadcrumbItem>
                        <BreadcrumbItem>Components</BreadcrumbItem>
                        <BreadcrumbItem>Layout</BreadcrumbItem>
                    </Breadcrumb>
                    <Card id="ctl_card" dis-hover>
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
                isCollapsed: false
            }
        },
        computed: {
        },
        methods: {}
    }
</script>
