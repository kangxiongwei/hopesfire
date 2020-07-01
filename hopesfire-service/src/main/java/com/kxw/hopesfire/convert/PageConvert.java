package com.kxw.hopesfire.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kxw.hopesfire.entity.BaseEntity;
import com.kxw.hopesfire.model.BaseModel;

/**
 * @author kangxiongwei
 * @date 2020-07-01 17:58
 */
public class PageConvert {

    /**
     * 将分页对象从model类型转为entity类型
     *
     * @param page
     * @param <T>
     * @param <E>
     * @return
     */
    public static <T extends BaseEntity, E extends BaseModel> IPage<T> convertToEntity(IPage<E> page) {
        return new Page<>(page.getCurrent(), page.getSize());
    }

    /**
     * 将分页对象从entity类型转为model类型
     *
     * @param page
     * @param <>
     * @return
     */
    public static <E extends BaseEntity, M extends BaseModel> IPage<M> convertToModel(IPage<E> page) {
        IPage<M> result = new Page<>();
        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setPages(page.getPages());
        result.setTotal(page.getTotal());
        return result;
    }
}
