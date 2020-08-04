package com.kxw.hopesfire.biz.model;

import lombok.Data;

import java.util.List;

/**
 * 用户角色模型
 *
 * @author kangxiongwei
 * @date 2020/8/4 11:16 下午
 */
@Data
public class UserRoleModel {

    private Long userId;
    private List<Long> roleIds;

}
