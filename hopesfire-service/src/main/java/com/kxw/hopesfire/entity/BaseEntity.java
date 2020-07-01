package com.kxw.hopesfire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author kangxiongwei
 * @date 2020-07-01 18:07
 */
@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

}
