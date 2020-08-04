package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.RoleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色DB操作
 *
 * @author kangxiongwei
 * @date 2020-07-24 23:22
 */
@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 查询用户没有的角色列表
     *
     * @param roleIds
     * @return
     */
    List<RoleEntity> otherRoles(@Param("roleIds") List<Long> roleIds);

}
