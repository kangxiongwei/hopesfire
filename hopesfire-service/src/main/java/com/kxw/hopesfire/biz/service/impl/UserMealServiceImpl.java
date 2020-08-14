package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.convert.MealConvert;
import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.model.UserMealQueryModel;
import com.kxw.hopesfire.biz.model.UserMealsModel;
import com.kxw.hopesfire.biz.service.IUserMealService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.MealEntity;
import com.kxw.hopesfire.dao.entity.UserMealEntity;
import com.kxw.hopesfire.dao.mapper.MealMapper;
import com.kxw.hopesfire.dao.mapper.UserMealMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/5 10:42 下午
 */
@Service("userMealService")
public class UserMealServiceImpl implements IUserMealService {

    @Resource
    private UserMealMapper userMealMapper;
    @Resource
    private MealMapper mealMapper;

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void save(UserMealModel model) {
        UserMealEntity entity = BaseConvert.convertEntity(model, new UserMealEntity());
        if (entity.getId() == null) {
            this.userMealMapper.insert(entity);
        } else {
            this.userMealMapper.updateById(entity);
        }
        List<MealEntity> entities = MealConvert.convertEntity(model);
        if (!CollectionUtils.isEmpty(entities)) {
            this.mealMapper.batchInsert(entities);
        }
    }

    /**
     * 删除数据，根据ID删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.userMealMapper.deleteById(id);
    }

    /**
     * 根据ID查询数据对象
     *
     * @param id
     * @return
     */
    @Override
    public UserMealModel get(Long id) {
        UserMealEntity entity = this.userMealMapper.selectById(id);
        return BaseConvert.convertModel(new UserMealModel(), entity);
    }

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    @Override
    public List<UserMealModel> list(UserMealModel model) {
        Wrapper<UserMealEntity> wrapper = BaseConvert.convertWrapper(model, new UserMealEntity());
        List<UserMealEntity> entities = this.userMealMapper.selectList(wrapper);
        return BaseConvert.convertModels(new UserMealModel(), entities);
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(UserMealModel model) {
        Wrapper<UserMealEntity> wrapper = BaseConvert.convertWrapper(model, new UserMealEntity());
        IPage<UserMealEntity> page = this.userMealMapper.selectPage(PageConvert.convertPage(model), wrapper);
        return BaseConvert.convertPageModel(new UserMealModel(), page);
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(UserMealQueryModel model) {
        Wrapper<UserMealEntity> wrapper = BaseConvert.convertWrapper(model, new UserMealEntity());
        QueryWrapper<UserMealEntity> condition = (QueryWrapper<UserMealEntity>) wrapper;
        if (StringUtils.isNotBlank(model.getStartDate())) {
            condition.ge("add_date", model.getStartDate());
        }
        if (StringUtils.isNotBlank(model.getEndDate())) {
            condition.le("add_date", model.getEndDate());
        }
        IPage<UserMealEntity> page = this.userMealMapper.selectPage(PageConvert.convertPage(model), wrapper);
        return BaseConvert.convertPageModel(new UserMealModel(), page);
    }

    /**
     * 保存用户的菜单
     *
     * @param model
     */
    @Override
    public void saveUserMeals(UserMealsModel model) {
        List<UserMealModel> meals = model.getMeals();
        if (CollectionUtils.isEmpty(meals)) {
            return;
        }
        List<UserMealEntity> entities = MealConvert.convertEntities(model);
        //更新，每次只能更新一条
        if (model.getSaveType() == 1) {
            UserMealEntity entity = entities.get(0);
            this.userMealMapper.updateById(entity);
        } else {
            this.userMealMapper.batchInsert(entities);
        }
    }

}
