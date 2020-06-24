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
        component: (resolve) => require(['./views/ctl/home.vue'], resolve)
    }
];
export default routers;