package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色模型
 *
 * @author kangxiongwei
 * @date 2020-07-24 23:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleModel extends BaseModel {

    private String name;
    private String remark;

}
