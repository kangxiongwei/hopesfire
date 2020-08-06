package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.AttachEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kangxiongwei
 * @date 2020/8/6 9:02 下午
 */
@Mapper
public interface AttachMapper extends BaseMapper<AttachEntity> {

    /**
     * 批量插入附件
     *
     * @param list
     */
    void batchInsert(@Param("list") List<AttachEntity> list);

}
