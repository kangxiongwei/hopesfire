package com.kxw.hopesfire.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangxiongwei
 * @date 2020-07-01 16:24
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
