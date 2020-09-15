package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.ArticleModel;

/**
 * @author kangxiongwei
 * @date 2020/9/14 4:43 下午
 */
public interface IArticleService extends IBaseService<ArticleModel> {

    /**
     * 审核文章
     *
     * @param article
     */
    void audit(ArticleModel article);

    /**
     * 发布文章
     *
     * @param article
     */
    void publish(ArticleModel article);

}
