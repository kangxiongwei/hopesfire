import {post} from '../libs/axios'

const ATTACH_UPLOAD_URL="/ctl/attach/upload"
const ATTACH_FIND_URL="/ctl/attach/find"
const ATTACH_DELETE_URL="/ctl/attach/delete"

function uploadAttach(vuex, params) {
    return post(ATTACH_UPLOAD_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('上传附件失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('上次附件失败，请联系管理员！');
        return null;
    })
}

function doFindAttaches(vuex, params) {
    return post(ATTACH_FIND_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询附件列表失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询附件列表失败！');
        return null;
    })
}

function doDeleteAttach(vuex, params) {
    return post(ATTACH_DELETE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('删除附件失败！');
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('删除附件失败！');
        return null;
    })
}

export default {uploadAttach, doFindAttaches, doDeleteAttach}
