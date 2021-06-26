package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_route")
public class RouteEntity extends BaseEntity {

    private String name;
    private String meta;
    private String resourcePath;
    private Long parentId;
    private Long menuId;

}
