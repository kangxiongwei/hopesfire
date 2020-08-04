package com.kxw.hopesfire.biz.model;

import com.kxw.hopesfire.dao.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户信息
 *
 * @author kangxiongwei
 * @date 2020-07-01 13:46
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserModel extends BaseModel {

    private String username;
    private String password;
    private String nickname;
    private String headImg;
    private Integer sex;
    private String telephone;
    private String address;
    private List<RoleModel> roles;

}
