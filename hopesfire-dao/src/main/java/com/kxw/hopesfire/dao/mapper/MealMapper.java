package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.MealEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/6 10:54 上午
 */
@Mapper
public interface MealMapper extends BaseMapper<MealEntity> {

    /**
     * 插入菜品方法
     *
     * @param entity
     * @return
     */
    @Override
    int insert(@Param("entity") MealEntity entity);

    /**
     * 批量插入
     *
     * @param list
     */
    void batchInsert(List<MealEntity> list);

}
