package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.model.UserMealModel;
import com.kxw.hopesfire.biz.service.IUserMealService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.UserMealEntity;
import com.kxw.hopesfire.dao.mapper.UserMealMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;

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

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    @Override
    public void save(UserMealModel model) {
        UserMealEntity entity = BaseConvert.convertEntity(model, new UserMealEntity());
        if (entity.getId() == null) {
            this.userMealMapper.insert(entity);
        } else {
            this.userMealMapper.updateById(entity);
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
}
