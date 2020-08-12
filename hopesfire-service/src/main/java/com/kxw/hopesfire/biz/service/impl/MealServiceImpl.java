package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.model.MealModel;
import com.kxw.hopesfire.biz.service.IMealService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.MealEntity;
import com.kxw.hopesfire.dao.mapper.MealMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/6 10:56 上午
 */
@Service("mealService")
public class MealServiceImpl implements IMealService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MealServiceImpl.class);
    @Resource
    private MealMapper mealMapper;

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(MealModel model) {
        MealEntity entity = BaseConvert.convertEntity(model, new MealEntity());
        entity.setWeight(entity.getWeight() == null ? 0 : entity.getWeight());
        try {
            if (entity.getId() == null) {
                this.mealMapper.insert(entity);
                model.setId(entity.getId());
            } else {
                this.mealMapper.updateById(entity);
            }
        } catch (DuplicateKeyException e) {
            LOGGER.info("菜品已经存在！");
        } catch (Exception e) {
            LOGGER.error("保存菜品异常，", e);
        }
    }

    /**
     * 删除数据，根据ID删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.mealMapper.deleteById(id);
    }

    /**
     * 根据ID查询数据对象
     *
     * @param id
     * @return
     */
    @Override
    public MealModel get(Long id) {
        MealEntity entity = this.mealMapper.selectById(id);
        return BaseConvert.convertModel(new MealModel(), entity);
    }

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    @Override
    public List<MealModel> list(MealModel model) {
        Wrapper<MealEntity> wrapper = BaseConvert.convertWrapper(model, new MealEntity());
        List<MealEntity> entities = this.mealMapper.selectList(wrapper);
        return BaseConvert.convertModels(new MealModel(), entities);
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(MealModel model) {
        Wrapper<MealEntity> wrapper = BaseConvert.convertWrapper(model, new MealEntity());
        IPage<MealEntity> page = this.mealMapper.selectPage(PageConvert.convertPage(model), wrapper);
        return BaseConvert.convertPageModel(new MealModel(), page);
    }

}
