package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BannerConvert;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.enums.BannerTypeEnum;
import com.kxw.hopesfire.biz.model.BannerModel;
import com.kxw.hopesfire.biz.model.BannerTreeModel;
import com.kxw.hopesfire.biz.service.IBannerService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.BannerEntity;
import com.kxw.hopesfire.dao.mapper.BannerMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 栏目接口
 *
 * @author kangxiongwei
 * @date 2020/9/14 5:39 下午
 */
@Service("bannerService")
public class BannerServiceImpl implements IBannerService {

    @Resource
    private BannerMapper bannerMapper;

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    @Override
    public void save(BannerModel model) {
        BannerEntity entity = BaseConvert.convertEntity(model, new BannerEntity());
        entity.setParentId(model.getParent() == null ? null : model.getParent().getId());
        if (model.getId() == null) {
            this.bannerMapper.insert(entity);
            model.setId(entity.getId());
        } else {
            this.bannerMapper.updateById(entity);
        }
    }

    /**
     * 删除数据，根据ID删除
     *
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Map<String, Object> deleteCondition = new HashMap<>();
        deleteCondition.put("parent_id", id);
        this.bannerMapper.deleteByMap(deleteCondition);
        this.bannerMapper.deleteById(id);
    }

    /**
     * 根据ID查询数据对象
     *
     * @param id
     * @return
     */
    @Override
    public BannerModel get(Long id) {
        BannerEntity entity = this.bannerMapper.selectById(id);
        BannerModel model = BaseConvert.convertModel(new BannerModel(), entity);
        //递归获取父级栏目
        if (entity.getParentId() > 0) {
            BannerModel parent = this.get(entity.getParentId());
            model.setParent(parent);
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
    public List<BannerModel> list(BannerModel model) {
        Wrapper<BannerEntity> wrapper = BaseConvert.convertSortWrapper(model, new BannerEntity());
        if (model.getParent() != null && model.getParent().getId() >= 0) {
            BannerEntity entity = wrapper.getEntity();
            entity.setParentId(model.getParent().getId());
        }
        List<BannerEntity> entities = this.bannerMapper.selectList(wrapper);
        return BaseConvert.convertModels(new BannerModel(), entities);
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(BannerModel model) {
        Wrapper<BannerEntity> wrapper = BaseConvert.convertWrapper(model, new BannerEntity());
        IPage<BannerEntity> page = PageConvert.convertPage(model);
        IPage<BannerEntity> entities = this.bannerMapper.selectPage(page, wrapper);
        return BaseConvert.convertPageModel(new BannerModel(), entities);
    }

    /**
     * 获取栏目类型
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> listBannerTypes() {
        List<Map<String, Object>> bannerTypes = new ArrayList<>();
        for (BannerTypeEnum e : BannerTypeEnum.values()) {
            Map<String, Object> map = new HashMap<>(1);
            map.put("key", e.getCode());
            map.put("value", e.getMessage());
            bannerTypes.add(map);
        }
        return bannerTypes;
    }

    /**
     * 查询栏目的树形结构，从顶级目录开始查询
     *
     * @param model
     * @return
     */
    @Override
    public List<BannerTreeModel> listBannerTree(BannerModel model) {
        List<BannerTreeModel> tree = new ArrayList<>();
        Wrapper<BannerEntity> wrapper = BaseConvert.convertSortWrapper(model, new BannerEntity());
        BannerEntity entity = wrapper.getEntity();
        entity.setParentId(model.getParent() == null ? 0L : model.getParent().getId());
        List<BannerEntity> entities = this.bannerMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(entities)) {
            for (BannerEntity e: entities) {
                BannerTreeModel node = BannerConvert.convertTree(e);
                //查询当前节点的子节点
                model.setParent(new BannerModel(e.getId()));
                List<BannerTreeModel> children = this.listBannerTree(model);
                BannerConvert.convertTreeChildren(node, children);
                //添加到树节点中
                tree.add(node);
            }
        }
        return tree;
    }

}
