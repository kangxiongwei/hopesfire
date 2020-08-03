package com.kxw.hopesfire.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 租户表
 *
 * @author kangxiongwei
 * @date 2020-07-24 22:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_tenant")
public class TenantEntity extends BaseEntity {

    private String name;
    private String remark;

}
