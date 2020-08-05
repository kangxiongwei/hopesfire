package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户组，和租户一对多关系
 *
 * @author kangxiongwei
 * @date 2020-07-24 22:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_group")
public class GroupEntity extends BaseEntity {

    private String name;
    private Long tenantId;
    private String remark;

}
