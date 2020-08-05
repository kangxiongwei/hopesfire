const MenuComponent = (resolve) => require(['./views/ctl/menu.vue'], resolve);

const routers = [
    {
        path: '/',
        name: 'index',
        meta: {
            title: '首页'
        },
        component: (resolve) => require(['./views/index.vue'], resolve)
    },
    {
        path: '/login',
        name: 'login',
        meta: {
            title: '登录'
        },
        component: (resolve) => require(['./views/login/login.vue'], resolve)
    },
    {
        path: '/register',
        name: 'register',
        meta: {
            title: '注册'
        },
        component: (resolve) => require(['./views/login/register.vue'], resolve)
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
            },
            {
                path: '/ctl/meal',
                name: '饮食管理',
                component: MenuComponent,
                children: [
                    {
                        path: '/ctl/user/meal',
                        name: '饮食记录',
                        component: (resolve) => require(['./views/ctl/meal/meal'], resolve)
                    }
                ]
            },
        ]
    }
];
export default routers;
