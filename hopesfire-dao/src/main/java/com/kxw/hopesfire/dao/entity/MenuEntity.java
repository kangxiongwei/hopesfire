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
@TableName("t_menu")
public class MenuEntity extends BaseEntity {

    private String name;
    private String icon;
    private Long parentId;
    private Integer level;
    private Long routeId;
    private String routePath;

}
