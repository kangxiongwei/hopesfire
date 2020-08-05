import {get, post} from '../libs/axios'

const GROUP_SAVE_URL = '/ctl/auth/group/save'
const GROUP_DELETE_URL = '/ctl/auth/group/delete/'
const GROUP_FIND_URL = '/ctl/auth/group/find'
const GROUP_LIST_URL = '/ctl/auth/group/list'

function doSaveGroup(vuex, params) {
    return post(GROUP_SAVE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return true;
        } else {
            vuex.$Message.error('保存分组信息失败！' + data.message);
        }
    }).catch(() => {
        vuex.$Message.error('保存分组信息失败！');
    })
}

function doDeleteGroup(vuex, id) {
    return get(GROUP_DELETE_URL + id).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return true;
        } else {
            vuex.$Message.error('删除分组信息失败！' + data.message);
            return false;
        }
    }).catch(() => {
        vuex.$Message.error('删除分组信息失败！');
        return false;
    })
}

function doFindGroups(vuex, params) {
    return post(GROUP_FIND_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询分组信息失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询分组信息失败！');
        return null;
    })
}

function doListGroups(vuex, params) {
    return post(GROUP_LIST_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询分组列表失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询群组列表失败！');
        return null;
    })
}

export default {doFindGroups, doSaveGroup, doDeleteGroup, doListGroups};
