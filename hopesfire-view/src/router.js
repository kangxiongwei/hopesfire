const MenuComponent = (resolve) => require(['./views/ctl/menu.vue'], resolve);

const routers = [
    {
        path: '/',
        meta: {
            title: ''
        },
        component: (resolve) => require(['./views/index.vue'], resolve)
    },
    {
        path: '/ctl',
        meta: {
            title: '首页'
        },
        name: '首页',
        component: (resolve) => require(['./views/ctl/home.vue'], resolve),
        children: [
            {
                path: '/ctl/root',
                name: '系统首页',
                component: MenuComponent,
                children: [
                    {
                        path: '/ctl/root/dashboard',
                        name: '数据看板',
                        component: (resolve) => require(['./views/ctl/root/dashboard'], resolve)
                    }
                ]
            },
            {
                path: '/ctl/auth',
                name: '权限控制',
                component: MenuComponent,
                children: [
                    {
                        path: '/ctl/auth/user',
                        name: '用户管理',
                        component: (resolve) => require(['./views/ctl/auth/user.vue'], resolve)
                    },
                    {
                        path: '/ctl/auth/role',
                        name: '角色管理',
                        component: (resolve) => require(['./views/ctl/auth/role.vue'], resolve)
                    },
                    {
                        path: '/ctl/auth/group',
                        name: '群组管理',
                        component: (resolve) => require(['./views/ctl/auth/group.vue'], resolve)
                    }
                ]
            }
        ]
    }
];
export default routers;
