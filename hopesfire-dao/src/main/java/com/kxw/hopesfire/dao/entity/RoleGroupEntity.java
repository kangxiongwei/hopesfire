package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020/8/5 11:22 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_role_group")
public class RoleGroupEntity extends BaseEntity {

    private Long roleId;
    private Long groupId;

}
