package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.enums.ArticleStatusEnum;
import com.kxw.hopesfire.biz.model.ArticleModel;
import com.kxw.hopesfire.biz.service.IArticleService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.ArticleEntity;
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
        return BaseConvert.convertModel(new ArticleModel(), entity);
    }

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    @Override
    public List<ArticleModel> list(ArticleModel model) {
        Wrapper<ArticleEntity> wrapper = BaseConvert.convertWrapper(model, new ArticleEntity());
        List<ArticleEntity> entities = this.articleMapper.selectList(wrapper);
        return BaseConvert.convertModels(new ArticleModel(), entities);
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(ArticleModel model) {
        Wrapper<ArticleEntity> wrapper = BaseConvert.convertWrapper(model, new ArticleEntity());
        IPage<ArticleEntity> pager = PageConvert.convertPage(model);
        IPage<ArticleEntity> articles = this.articleMapper.selectPage(pager, wrapper);
        return BaseConvert.convertPageModel(new ArticleModel(), articles);
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
