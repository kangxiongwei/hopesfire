package com.kxw.hopesfire.biz.convert;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.basic.util.DateUtil;
import com.kxw.hopesfire.biz.exception.ServiceException;
import com.kxw.hopesfire.biz.exception.ServiceExceptionEnum;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.BaseEntity;
import com.kxw.hopesfire.dao.model.BaseModel;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 拷贝属性
 *
 * @author kangxiongwei
 * @date 2020-07-24 23:27
 */
public class BaseConvert {

    public static <M, E> E convertEntity(M model, E entity) {
        if (model == null) {
            return null;
        }
        BeanUtils.copyProperties(model, entity);
        return entity;
    }

    public static <M, E> Wrapper<E> convertWrapper(M model, E entity) {
        E e = convertEntity(model, entity);
        return new QueryWrapper<>(e);
    }

    public static <M, E> M convertModel(M model, E entity) {
        if (entity == null) {
            return null;
        }
        BeanUtils.copyProperties(entity, model);
        if (model instanceof BaseModel && entity instanceof BaseEntity) {
            BaseEntity e = (BaseEntity) entity;
            BaseModel m = (BaseModel) model;
            m.setCreateTime(DateUtil.formatDateTime(e.getCreateTime()));
            m.setUpdateTime(DateUtil.formatDateTime(e.getUpdateTime()));
        }
        return model;
    }

    @SuppressWarnings("unchecked")
    public static <M, E> List<M> convertModels(M model, List<E> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }
        List<M> list = new ArrayList<>();
        Class<?> clazz = model.getClass();
        for (E entity : entities) {
            M m = (M) createModel(clazz);
            list.add(convertModel(m, entity));
        }
        return list;
    }

    public static <M, E> PagerModel convertPageModel(M model, IPage<E> entities) {
        if (entities == null) {
            return null;
        }
        List<M> records = convertModels(model, entities.getRecords());
        return PageConvert.convertToModel(entities, records);
    }

    private static Object createModel(Class<?> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw ServiceException.build(ServiceExceptionEnum.REFLECT_ERROR);
        }
    }

}
