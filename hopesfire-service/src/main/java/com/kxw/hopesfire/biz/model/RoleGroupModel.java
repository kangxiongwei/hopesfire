package com.kxw.hopesfire.biz.model;

import lombok.Data;

import java.util.List;

/**
 * 角色和分组关系
 *
 * @author kangxiongwei
 * @date 2020/8/5 10:36 上午
 */
@Data
public class RoleGroupModel {

    private Long roleId;
    private List<Long> groupIds;

}
