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
            title: ''
        },
        component: (resolve) => require(['./views/ctl/home.vue'], resolve),
        children: [
            {
                path: '/ctl/auth/user',
                component: (resolve) => require(['./views/ctl/user.vue'], resolve)
            },
            {
                path: '/ctl/auth/role',
                component: (resolve) => require(['./views/ctl/role.vue'], resolve)
            },
            {
                path: '/ctl/auth/group',
                component: (resolve) => require(['./views/ctl/group.vue'], resolve)
            }
        ]
    }
];
export default routers;