package com.kxw.hopesfire.biz.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.dao.entity.BaseEntity;
import com.kxw.hopesfire.dao.model.BaseModel;

/**
 * 公共的父类，封装增删改查
 *
 * @author kangxiongwei
 * @date 2020-07-24 23:23
 */
public abstract class AbstractBaseService<M extends BaseModel, E extends BaseEntity> {

    private BaseMapper<E> baseMapper;

    /**
     * 设置当前的mapper
     */
    public abstract void setMapper();

    /**
     * 保存数据到DB
     *
     * @param model
     */
    public void save(M model) throws Exception {
        if (model == null) return;
        BaseEntity entity = BaseConvert.convertEntity(model);
        if (model.getId() == null) {
            this.baseMapper.updateById((E) entity);
        } else {
            this.baseMapper.insert((E) entity);
        }
    }

    protected void setBaseMapper(BaseMapper<E> baseMapper) {
        this.baseMapper = baseMapper;
    }

}
