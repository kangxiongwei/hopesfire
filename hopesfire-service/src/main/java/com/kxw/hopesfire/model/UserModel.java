package com.kxw.hopesfire.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private String nickname;
    private Integer sex;

}
