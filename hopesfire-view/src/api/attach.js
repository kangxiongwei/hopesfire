import {post} from '../libs/axios'

const ATTACH_UPLOAD_URL="/ctl/attach/upload"

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

export default {uploadAttach}
