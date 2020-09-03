<template>
    <Menu :theme="menuTheme"
          width="auto"
          class="sider-menu"
          accordion>

        <template v-for="(item, componentIndex) in menu">

            <!-- 展开并且有子菜单 -->
            <Submenu v-if="!isCollapsed && item.children.length" v-bind:key="componentIndex" :name="componentIndex">
                <template slot="title">
                    <Icon :type="item.icon"/>
                    <span>{{ item.name }}</span>
                </template>
                <MenuItem v-for="(children, index) in item.children" :key="index" :name="children.to" :to="children.to">
                    {{ children.name }}
                </MenuItem>
            </Submenu>

            <!-- 展开但没有子菜单 -->
            <MenuItem v-else-if="!isCollapsed" :name="item.to" :to="item.to" v-bind:key="componentIndex">
                <Icon :type="item.icon"/>
                <span>{{ item.name }}</span>
            </MenuItem>

            <!-- 不展开有子菜单 -->
            <Dropdown v-else-if="isCollapsed && item.children.length" v-bind:key="componentIndex" placement="right-start" class="menu-dropdown">
                <MenuItem :name="item.name" :to="item.to">
                    <Icon :type="item.icon"/>
                    <span>{{ item.name }}</span>
                </MenuItem>
                <DropdownMenu slot="list">
                    <DropdownItem v-for="(children, index) in item.children" :key="index" style="padding-left: 5px">
                        <MenuItem :name="children.to" :to="children.to">{{ children.name }}</MenuItem>
                    </DropdownItem>
                </DropdownMenu>
            </Dropdown>

            <!-- 不展开无子菜单 -->
            <Tooltip v-else-if="isCollapsed" :content="item.name" placement="right" v-bind:key="componentIndex" class="menu-tooltip">
                <MenuItem :name="item.to" :to="item.to">
                    <Icon :type="item.icon"/>
                    <span>{{ item.name }}</span>
                </MenuItem>
            </Tooltip>
        </template>
    </Menu>
</template>

<script>
    export default {
        name: "sider_menu",
        props: [
            "isCollapsed"
        ],
        data() {
            return {
                keepAliveList: [],
                showSettingPage: false,
                showSuggestionPage: false,
                menuTheme: 'light',
                menu: [
                    {
                        name: "系统首页",
                        icon: "md-apps",
                        children: [
                            {
                                name: "数据大盘",
                                to: "/ctl/root/dashboard"
                            }
                        ]
                    },
                    {
                        name: "权限控制",
                        icon: "ios-settings",
                        children: [
                            {
                                name: "用户管理",
                                to: "/ctl/auth/user"
                            },
                            {
                                name: "角色管理",
                                to: "/ctl/auth/role"
                            },
                            {
                                name: "群组管理",
                                to: "/ctl/auth/group"
                            }
                        ]
                    },
                    {
                        name: "内容管理",
                        icon: "ios-folder",
                        children: [
                            {
                                name: "文章管理",
                                to: "/ctl/attach/articles"
                            },
                            {
                                name: "附件管理",
                                to: "/ctl/attach/attaches"
                            }
                        ]
                    },
                    {
                        name: "盼盼的饮食管理",
                        icon: "ios-alarm",
                        children: [
                            {
                                name: "盼盼的菜品管理",
                                to: "/ctl/meal/category"
                            },
                            {
                                name: "盼盼的饮食记录",
                                to: "/ctl/user/meal"
                            }
                        ]
                    }
                ]
            }
        },
        methods: {
        }
    }
</script>

<style scoped>

    .menu-tooltip {
        width: 100%;
    }

    .menu-tooltip span {
        display: none;
    }

    .menu-tooltip i {
        transform: translateX(5px);
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 22px;
    }

    .menu-dropdown {
        width: 100%;
    }

    .menu-dropdown span {
        display: none;
        transition: width .2s ease;
    }
    .menu-dropdown i {
        transform: translateX(5px);
        transition: font-size .2s ease .2s, transform .2s ease .2s;
        vertical-align: middle;
        font-size: 22px;
    }

</style>
