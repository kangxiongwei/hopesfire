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
                path: '/ctl/auth',
                name: '权限控制',
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
            },

        ]
    }
];
export default routers;
