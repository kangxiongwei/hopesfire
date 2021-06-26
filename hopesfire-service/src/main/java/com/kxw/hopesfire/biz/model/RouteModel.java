package com.kxw.hopesfire.biz.model;

import java.util.List;

import com.kxw.hopesfire.dao.model.BaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author kangxiongwei
 * @date 2021-06-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RouteModel extends BaseModel {

    private String name;
    private String meta;
    private String resourcePath;
    private Long parentId;
    private Long menuId;
    private List<RouteModel> children;

}
