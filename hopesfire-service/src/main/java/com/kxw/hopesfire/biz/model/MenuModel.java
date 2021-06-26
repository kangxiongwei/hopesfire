package com.kxw.hopesfire.biz.model;

import java.util.List;

import com.kxw.hopesfire.dao.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单模型
 *
 * @author kangxiongwei
 * @date 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuModel extends BaseModel {

    private String name;
    private String icon;
    private String to;                  //和DB中的routePath对应
    private List<MenuModel> children;   //子菜单

}
