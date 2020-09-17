import {post} from "../libs/axios";

const ARTICLE_SAVE_URL = '/ctl/article/save'

function doSaveArticle(vuex, params) {
    let keywordTags = params.keywordTags;
    params.keyword = keywordTags.length > 0 ? keywordTags.join('|') : '';
    return post(ARTICLE_SAVE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('保存文章失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('保存文章失败，请联系管理员！');
        return null;
    })
}

export default {doSaveArticle}
