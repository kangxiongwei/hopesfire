import Vue from 'vue';
import ViewUI from 'view-design';
import VueRouter from 'vue-router';
import VueCropper from 'vue-cropper';
import Routers from './router';
import Util from './libs/util';
import App from './app.vue';
import axios from './libs/axios'
import {getToken} from './libs/store'
import 'view-design/dist/styles/iview.css';

Vue.use(VueRouter);
Vue.use(ViewUI, {
    transfer: true,
    size: 'small'
});
Vue.use(VueCropper);
Vue.prototype.$axios = axios;

// 路由配置
const RouterConfig = {
    routes: Routers
};
const router = new VueRouter(RouterConfig);

router.beforeEach((to, from, next) => {
    ViewUI.LoadingBar.start();
    Util.title(to.meta.title);
    let token = getToken();
    if (!token || token === '') {
        if (to.name === 'login' || to.name === 'register' || to.name === 'index') {
            next();
        } else {
            next({name: 'login'});
        }
    } else {
        next();
    }
});

router.afterEach((to, from, next) => {
    ViewUI.LoadingBar.finish();
    window.scrollTo(0, 0);
});

new Vue({
    el: '#app',
    router: router,
    render: h => h(App)
});
