import {get, post} from '../libs/axios'

const MEAL_SAVE_URL = '/ctl/user/meal/save'
const MEAL_DELETE_URL = '/ctl/user/meal/delete/'
const MEAL_FIND_URL = '/ctl/user/meal/find'

function doSaveUserMeal(vuex, params) {
    return post(MEAL_SAVE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return true;
        } else {
            vuex.$Message.error("保存饮食记录失败！")
            return false;
        }
    }).catch(() => {
        vuex.$Message.error("保存饮食记录失败！")
        return false;
    })
}

function doDeleteUserMeal(vuex, id) {
    return get(MEAL_DELETE_URL + id).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return true;
        } else {
            vuex.$Message.error("删除饮食记录失败！")
            return false;
        }
    }).catch(() => {
        vuex.$Message.error("删除饮食记录失败！")
        return false;
    })
}

function doFindUserMeals(vuex, params) {
    return post(MEAL_FIND_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error("查询饮食记录失败！")
            return null;
        }
    }).catch(() => {
        vuex.$Message.error("查询饮食记录失败！")
        return null;
    })

}

export default {doFindUserMeals, doSaveUserMeal, doDeleteUserMeal}
