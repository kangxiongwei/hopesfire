import {post} from '../libs/axios'

const BANNER_SAVE_URL = '/ctl/banner/save'
const BANNER_FIND_URL = '/ctl/banner/find'
const BANNER_LIST_URL = '/ctl/banner/list'
const BANNER_TREE_URL = '/ctl/banner/tree'
const BANNER_TYPE_URL = '/ctl/banner/types'
const BANNER_DELETE_URL = '/ctl/banner/delete'

function doSaveBanner(vuex, params) {
    return post(BANNER_SAVE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data;
        } else {
            vuex.$Message.error('保存栏目失败！' + data.message);
        }
    }).catch(() => {
        vuex.$Message.error('保存栏目失败！');
    })
}

function doFindBanners(vuex, params) {
    return post(BANNER_FIND_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询栏目列表失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询栏目列表失败！');
        return null;
    })
}

function doListBanners(vuex, params) {
    return post(BANNER_LIST_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询栏目列表失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询栏目列表失败！');
        return null;
    })
}

function doListBannerTree(vuex, params) {
    return post(BANNER_TREE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询栏目树失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询栏目树失败！');
        return null;
    })
}

function listBannerTypes(vuex) {
    return post(BANNER_TYPE_URL, {}).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询栏目类型失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询栏目类型失败！');
        return null;
    })
}

function doDeleteBanner(vuex, params) {
    return post(BANNER_DELETE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('删除栏目失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('删除栏目失败！');
        return null;
    })
}

export default {doSaveBanner, doListBannerTree, listBannerTypes, doDeleteBanner}
