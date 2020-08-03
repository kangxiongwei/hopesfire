package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.GroupEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群组DB操作
 *
 * @author kangxiongwei
 * @date 2020-07-24 23:21
 */
@Mapper
public interface GroupMapper extends BaseMapper<GroupEntity> {
}
