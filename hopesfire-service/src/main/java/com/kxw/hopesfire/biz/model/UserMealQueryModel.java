package com.kxw.hopesfire.biz.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 查询条件
 *
 * @author kangxiongwei
 * @date 2020/8/14 8:20 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserMealQueryModel extends UserMealModel {

    private String startDate;
    private String endDate;

}
