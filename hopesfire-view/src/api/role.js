import {get, post} from '../libs/axios'

const ROLE_SAVE_URL = '/ctl/auth/role/save'
const ROLE_DELETE_URL = '/ctl/auth/role/delete/'
const ROLE_FIND_URL = '/ctl/auth/role/find'
const ROLE_GROUP_LIST_URL = '/ctl/auth/role/group/list/'
const ROLE_GROUP_SAVE_URL = '/ctl/auth/role/group/save'

function doSaveRole(vuex, params) {
    return post(ROLE_SAVE_URL, params).then(response => {
        if (response != null && response.data.code === 200) {
            return true;
        } else {
            vuex.$Message.error("保存角色失败！" + response.data.message);
            return false;
        }
    }).catch(() => {
        vuex.$Message.error("保存角色失败！")
        return false;
    })
}

function doDeleteRole(vuex, id) {
    return get(ROLE_DELETE_URL + id).then(response => {
        if (response != null && response.data.code === 200) {
            return true;
        } else {
            vuex.$Message.error("删除角色失败！")
            return false;
        }
    })
}

function doFindRoles(vuex, params) {
    return post(ROLE_FIND_URL, params).then(response => {
        let data = response.data;
        if (data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error("查询角色列表失败！" + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error("查询角色列表失败！");
        return null;
    })
}

function doListRoleGroups(vuex, roleId) {
    return get(ROLE_GROUP_LIST_URL + roleId).then(res => {
        let data = res.data;
        if (data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error("查询角色群组失败！" + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error("查询角色群组失败！");
        return null;
    })
}

function doSaveRoleGroups(vuex, params) {
    return post(ROLE_GROUP_SAVE_URL, params).then(res => {
        if (res.data.code === 200) {
            return true;
        } else {
            vuex.$Message.error("保存角色群组失败！" + res.data.message);
            return false;
        }
    }).catch(() => {
        vuex.$Message.error("保存角色群组失败！");
        return false;
    })
}

export default {doSaveRole, doDeleteRole, doFindRoles, doListRoleGroups, doSaveRoleGroups}
