import Vue from 'vue'
import '../libs/axios'

/**
 * 登录方法
 * @param loginUser 登录用户
 * @param target 目标URL
 */
function login(loginUser, target) {
    Vue.prototype.$post('/login', {
        username: loginUser.username,
        password: loginUser.password
    }).then(res => {
        if (res != null && res.data.code === 200) {
            //更新本地状态和Cookie
            this.$store.commit('setLoginToken', res.data.data);
            //登录成功，跳转到后台
            this.$router.push({path: target})
        } else {
            this.$store.commit('setLoginError', res.data.message);
        }
    });
}

/**
 * 退出登录方法
 * @param loginUser
 * @param target
 */
function logout(loginUser, target) {
    Vue.prototype.$post('/logout').then(res => {
        if (res != null && res.data.code === 200) {
            this.$store.commit('setLoginToken', '');
            this.$router.push({path: target})
        } else {
            this.$store.commit('setLoginError', res.data.message);
        }
    });
}

export default login;
