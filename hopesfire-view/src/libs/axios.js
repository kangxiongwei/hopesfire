import Vue from 'vue'
import axios from 'axios'

//设置默认请求头，如果不需要可以取消这一步
axios.defaults.headers = {
    'X-Requested-With': 'XMLHttpRequest'
};
//请求超时的时间限制
axios.defaults.timeout = 20000;

//请求到结果的拦截处理
axios.interceptors.response.use(config => {
    return config
}, error => {
    if (error && error.response) {
        switch (error.response.status) {
            case 400:
                error.message = '客户端错误！';
                break;
            case 500:
                error.message = '服务端错误！';
                break;
        }
    } else {
        error.message = "连接服务端失败！"
    }
    return Promise.reject(error.message)
});

/**
 * 将axios 的 post 方法，绑定到 vue 实例上面的 $post
 *
 * @param url
 * @param params
 * @returns {Promise<any>}
 */
Vue.prototype.$post = function (url, params) {
    return new Promise((resolve, reject) => {
        axios.post(url, params).then(
            res => {
                resolve(res)
            }).catch(err => {
            reject(err)
        })
    })
};
/**
 * 将axios 的 get 方法，绑定到 vue 实例上面的 $get
 *
 * @param url
 * @param params
 * @returns {Promise<any>}
 */
Vue.prototype.$get = function (url, params) {
    return new Promise((resolve, reject) => {
        axios.get(url, {
            params: params
        }).then(res => {
            resolve(res);
        }).catch(err => {
            reject(err)
        })
    })
};

export default axios;
