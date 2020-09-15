import Vue from 'vue';
import ViewUI from 'view-design';
import VueRouter from 'vue-router';
import VueCropper from 'vue-cropper';
import VueQuillEditor from "vue-quill-editor";
import Routers from './router';
import Util from './libs/util';
import App from './app.vue';
import axios from './libs/axios'
import {getToken} from './libs/store'
import 'view-design/dist/styles/iview.css';
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

Vue.use(VueRouter);
Vue.use(ViewUI, {
    transfer: true,
    size: 'small'
});
Vue.use(VueCropper);
Vue.use(VueQuillEditor, {
    theme: 'snow',
    modules: {
        toolbar: [
            [{'font': []}, {'size': ['small', false, 'large']}, {'color': []}, {'background': []}],   //字体、大小、颜色、背景
            ['bold', 'italic', 'underline', 'strike'],        //加粗、倾斜、下划线、中划线
            [{'script': 'sub'}, {'script': 'super'}],         //底数、指数
            [{'align': []}, {'list': 'ordered'}, {'list': 'bullet'}],        //有序列表、无序列表
            ['code-block'],                                   //代码块
            [{'indent': '-1'}, {'indent': '+1'}],             //左缩进、右缩进
            [{'direction': 'rtl'}],                           //文本向左还是向右对齐
            ['link', 'image', 'video'],                       //链接、图片、视频
            ['clean']                                         //清除格式
        ]
    }
});
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
