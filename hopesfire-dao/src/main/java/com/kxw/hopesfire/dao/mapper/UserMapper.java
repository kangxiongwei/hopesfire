package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户管理DB层
 *
 * @author kangxiongwei
 * @date 2020-07-01 16:24
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
