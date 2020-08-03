package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 数据库建表基类
 *
 * @author kangxiongwei
 * @date 2020-07-01 18:07
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Date createTime;
    private Date updateTime;

}
