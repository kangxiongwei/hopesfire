import Vue from 'vue'
import Vuex from 'vuex'
import Cookies from 'js-cookie'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        loginToken: ''
    },
    mutations: {
        setLoginToken(state, token) {
            state.loginToken = token;
            setToken(state.loginToken);
        }
    },
    getters: {
        getLoginToken: state => {
            return state.loginToken;
        }
    }
});

Vue.prototype.$store = store;


//Cookie相关操作
export const TOKEN_KEY = 'hopesfireToken';
export const setToken = (token) => {
    Cookies.set(TOKEN_KEY, token, {expires: 1})
};
export const getToken = () => {
    const token = Cookies.get(TOKEN_KEY);
    return token ? token : '';
};

export default store;
