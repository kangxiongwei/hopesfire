package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分组模型
 *
 * @author kangxiongwei
 * @date 2020/8/5 10:36 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupModel extends BaseModel {

    private Long tenantId;
    private String name;
    private String remark;

}
