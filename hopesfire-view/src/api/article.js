import {post} from "../libs/axios";

const ARTICLE_SAVE_URL = '/ctl/article/save'
const ARTICLE_DELETE_URL = '/ctl/article/delete'
const ARTICLE_LIST_URL = '/ctl/article/list'
const ARTICLE_FIND_URL = '/ctl/article/find'

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

function doDeleteArticle(vuex, params) {
    return post(ARTICLE_DELETE_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('删除文章失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('删除文章失败，请联系管理员！');
        return null;
    })
}


function doListArticles(vuex, params) {
    return post(ARTICLE_LIST_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询文章失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询文章失败，请联系管理员！');
        return null;
    })
}

function doFindArticle(vuex, params) {
    return post(ARTICLE_FIND_URL, params).then(res => {
        let data = res.data;
        if (data != null && data.code === 200) {
            return data.data;
        } else {
            vuex.$Message.error('查询文章列表失败！' + data.message);
            return null;
        }
    }).catch(() => {
        vuex.$Message.error('查询文章列表失败！');
        return null;
    })
}

export default {doSaveArticle, doDeleteArticle, doListArticles, doFindArticle}
