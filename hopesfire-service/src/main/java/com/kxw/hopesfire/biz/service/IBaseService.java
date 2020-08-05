package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.dao.model.PagerModel;

import java.util.List;

/**
 * 基本操作顶级接口
 *
 * @author kangxiongwei
 * @date 2020/8/5 10:48 上午
 */
public interface IBaseService<T> {

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    void save(T model);

    /**
     * 删除数据，根据ID删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据ID查询数据对象
     *
     * @param id
     * @return
     */
    T get(Long id);

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    List<T> list(T model);

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    PagerModel find(T model);

}
