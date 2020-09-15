package com.kxw.hopesfire.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kxw.hopesfire.dao.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章管理
 *
 * @author kangxiongwei
 * @date 2020/9/14 4:38 下午
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {
}
