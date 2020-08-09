<template>
    <Menu active-name="home"
          :open-names="['home']"
          :theme="menuTheme"
          width="auto"
          :class="menuitemClasses"
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
                <MenuItem :name="item.to" :to="item.to">
                    <Icon :type="item.icon"/>
                    <span>{{ item.name }}</span>
                </MenuItem>
                <DropdownMenu slot="list">
                    <DropdownItem v-for="(children, index) in item.children" :key="index" style="padding: 0 0; background-color:#515a6e;">
                        <MenuItem :name="children.to" :to="children.to">{{ children.name }}</MenuItem>
                    </DropdownItem>
                </DropdownMenu>
            </Dropdown>

            <!-- 不展开无子菜单 -->
            <Tooltip v-else-if="isCollapsed" :content="item.name" placement="right" v-bind:key="componentIndex">
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
        name: 'ViewSider',
        data() {
            return {
                isCollapsed: false,
                keepAliveList: [],
                title: "Home",
                showSettingPage: false,
                showSuggestionPage: false,
                menuTheme: 'dark',
                menu: [
                    {
                        name: "主页",
                        to: "home",
                        icon: "md-apps",
                        children: []
                    },
                    {
                        name: "菜单分类1",
                        to: "user",
                        icon: "ios-create-outline",
                        children: [
                            {
                                name: "子菜单1",
                                to: "user"
                            },
                            {
                                name: "子菜单2",
                                to: "menu"
                            },
                            {
                                name: "子菜单3",
                                to: "shared_report"
                            },

                        ]
                    },
                    {
                        name: "菜单分类2",
                        to: "user2",
                        icon: "ios-archive-outline",
                        children: [
                            {
                                name: "子菜单2-1",
                                to: "firewall"
                            },
                            {
                                name: "子菜单2-2",
                                to: "navi_page"
                            }
                        ]
                    }
                ]
            }
        },
        methods: {
            menuitemClasses () {
                return [
                    'menu-item',
                    this.isCollapsed ? 'collapsed-menu' : ''
                ]
            }
        }
    }
</script>

<style scoped>

</style>
