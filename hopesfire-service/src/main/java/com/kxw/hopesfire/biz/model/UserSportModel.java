package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2020/8/11 3:25 下午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSportModel extends BaseModel {

    private String sports;      //运动项目

}
