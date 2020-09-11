import {post} from '../libs/axios'

const LOGIN_URL = '/login'
const REGISTER_URL = '/register'
const LOGOUT_URL = '/logout'

function login(vuex, loginUser) {
    return post(LOGIN_URL, {
        username: loginUser.username,
        password: loginUser.password
    }).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            //更新本地状态和Cookie
            vuex.$store.commit('setLoginToken', data.data);
            vuex.$router.push({path: "/"})
        } else {
            vuex.$Message.error("登录系统错误！" + data.message)
        }
    }).catch(() => {
        vuex.$Message.error("登录系统错误！")
    });
}

function register(vuex, loginUser) {
    return post(REGISTER_URL, {
        username: loginUser.username,
        password: loginUser.password,
        nickname: loginUser.nickname,
        sex: loginUser.sex,
        headId: loginUser.headId,
        headImg: loginUser.headImg,
        telephone: loginUser.telephone,
        address: loginUser.address
    }).then(res => {
        if (res != null && res.data.code === 200) {
            vuex.$router.push({path: "/login"})
        } else {
            vuex.$Message.error("注册用户失败！" + res.data.message);
        }
    }).catch(() => {
        vuex.$Message.error("注册用户失败！");
    })
}

function logout(vuex) {
    return post(LOGOUT_URL).then(res => {
        if (res != null && res.data.code === 200) {
            vuex.$store.commit('setLoginToken', '');
            vuex.$router.push('/login')
        } else {
            vuex.$Message.error(res.data.message);
        }
    }).catch(() => {
        vuex.$Message.error("退出登录失败！");
    });
}

export default {login, register, logout};
