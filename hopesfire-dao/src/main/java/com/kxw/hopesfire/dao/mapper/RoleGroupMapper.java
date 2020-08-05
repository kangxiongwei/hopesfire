package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.RoleGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色和分组关系表
 *
 * @author kangxiongwei
 * @date 2020/8/5 11:26 上午
 */
@Mapper
public interface RoleGroupMapper extends BaseMapper<RoleGroupEntity> {

    /**
     * 批量插入
     *
     * @param list
     */
    void batchInsert(@Param("list") List<RoleGroupEntity> list);

    /**
     * 批量删除
     *
     * @param roleId
     * @param groupIds
     */
    void batchDelete(@Param("roleId") Long roleId, @Param("groupIds") List<Long> groupIds);

}
