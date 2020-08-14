package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.UserMealEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/5 10:40 下午
 */
@Mapper
public interface UserMealMapper extends BaseMapper<UserMealEntity> {

    /**
     * 批量插入饮食记录
     *
     * @param list
     */
    void batchInsert(@Param("list") List<UserMealEntity> list);


}
