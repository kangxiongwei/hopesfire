import {post} from '../libs/axios'

const MENU_TREE_URL = '/ctl/menu/tree'

function doListMenuTree(vuex, params) {
    return post(MENU_TREE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询菜单列表失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询菜单列表失败！');
        return null;
    })
}

export default {doListMenuTree}