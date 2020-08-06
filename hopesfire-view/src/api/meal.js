import {get, post} from '../libs/axios'

const USER_MEAL_SAVE_URL = '/ctl/user/meal/save'
const USER_MEAL_DELETE_URL = '/ctl/user/meal/delete/'
const USER_MEAL_FIND_URL = '/ctl/user/meal/find'
const MEAL_SAVE_URL = '/ctl/meal/save'
const MEAL_DELETE_URL = '/ctl/meal/delete/'
const MEAL_FIND_URL = '/ctl/meal/find'
const MEAL_LiST_URL = '/ctl/meal/list'

function doFind(vuex, url, params, keyword) {
    return post(url, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error("查询" + keyword + "失败！")
            return null;
        }
    }).catch(() => {
        vuex.$Message.error("查询" + keyword + "失败！")
        return null;
    })
}

function doSave(vuex, url, params, keyword) {
    return post(url, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return true;
        } else {
            vuex.$Message.error("保存" + keyword + "失败！")
            return false;
        }
    }).catch(() => {
        vuex.$Message.error("保存" + keyword + "失败！")
        return false;
    })
}

function doDelete(vuex, url, keyword) {
    return get(url).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return true;
        } else {
            vuex.$Message.error("删除" + keyword + "失败！")
            return false;
        }
    }).catch(() => {
        vuex.$Message.error("删除" + keyword + "失败！")
        return false;
    })
}

function doSaveUserMeal(vuex, params) {
    return doSave(vuex, USER_MEAL_SAVE_URL, params, '饮食记录')
}

function doDeleteUserMeal(vuex, id) {
    return doDelete(vuex, USER_MEAL_DELETE_URL + id, '饮食记录')
}

function doFindUserMeals(vuex, params) {
    return doFind(vuex, USER_MEAL_FIND_URL, params, '饮食记录')
}

function doFindMeals(vuex, params) {
    return doFind(vuex, MEAL_FIND_URL, params, '菜品列表')
}

function doSaveMeal(vuex, params) {
    return doSave(vuex, MEAL_SAVE_URL, params, '菜品');
}

function doDeleteMeal(vuex, id) {
    return doDelete(vuex, MEAL_DELETE_URL + id, '菜品')
}

function doListMeals(vuex, params) {
    return doFind(vuex, MEAL_LiST_URL, params, '菜品列表');
}

export default {
    doFindUserMeals,
    doSaveUserMeal,
    doDeleteUserMeal,
    doFindMeals,
    doSaveMeal,
    doDeleteMeal,
    doListMeals
}
