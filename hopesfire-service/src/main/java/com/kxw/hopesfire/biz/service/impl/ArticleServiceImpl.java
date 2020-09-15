package com.kxw.hopesfire.biz.service.impl;

import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.enums.ArticleStatusEnum;
import com.kxw.hopesfire.biz.model.ArticleModel;
import com.kxw.hopesfire.biz.model.AttachModel;
import com.kxw.hopesfire.biz.model.BannerModel;
import com.kxw.hopesfire.biz.service.IArticleService;
import com.kxw.hopesfire.dao.entity.ArticleEntity;
import com.kxw.hopesfire.dao.entity.AttachEntity;
import com.kxw.hopesfire.dao.entity.BannerEntity;
import com.kxw.hopesfire.dao.mapper.ArticleMapper;
import com.kxw.hopesfire.dao.mapper.AttachMapper;
import com.kxw.hopesfire.dao.mapper.BannerMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 文章处理接口
 *
 * @author kangxiongwei
 * @date 2020/9/14 4:43 下午
 */
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private AttachMapper attachMapper;
    @Resource
    private BannerMapper bannerMapper;

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    @Override
    public void save(ArticleModel model) {
        ArticleEntity entity = BaseConvert.convertEntity(model, new ArticleEntity());
        entity.setIconId(model.getIcon() == null ? null : model.getIcon().getId());
        entity.setBannerId(model.getBanner() == null ? null : model.getBanner().getId());
        if (model.getId() == null) {
            this.articleMapper.insert(entity);
            model.setId(entity.getId());
        } else {
            this.articleMapper.updateById(entity);
        }
    }

    /**
     * 删除数据，根据ID删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.articleMapper.deleteById(id);
    }

    /**
     * 根据ID查询数据对象
     *
     * @param id
     * @return
     */
    @Override
    public ArticleModel get(Long id) {
        ArticleEntity entity = this.articleMapper.selectById(id);
        ArticleModel model = BaseConvert.convertModel(new ArticleModel(), entity);
        if (entity.getIconId() != null && entity.getIconId() > 0) {
            AttachEntity icon = this.attachMapper.selectById(entity.getIconId());
            model.setIcon(BaseConvert.convertModel(new AttachModel(), icon));
        }
        if (entity.getBannerId() != null && entity.getBannerId() > 0) {
            BannerEntity banner = this.bannerMapper.selectById(entity.getBannerId());
            BannerModel bannerModel = BaseConvert.convertModel(new BannerModel(), banner);
            bannerModel.setParent(new BannerModel(banner.getParentId()));
            model.setBanner(bannerModel);
        }
        return model;
    }

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    @Override
    public List<ArticleModel> list(ArticleModel model) {
        return null;
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(ArticleModel model) {
        return null;
    }

    /**
     * 审核文章
     *
     * @param article
     */
    @Override
    public void audit(ArticleModel article) {
        ArticleEntity entity = BaseConvert.convertEntity(article, new ArticleEntity());
        this.articleMapper.updateById(entity);
    }

    /**
     * 发布文章
     *
     * @param article
     */
    @Override
    public void publish(ArticleModel article) {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(article.getId());
        entity.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        entity.setPublishTime(new Date());
        this.articleMapper.updateById(entity);
    }
}