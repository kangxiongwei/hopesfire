package com.kxw.hopesfire.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息
 *
 * @author kangxiongwei
 * @date 2020-07-01 13:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    private Long id;
    private String username;
    private String nickname;
    private Integer sex;

}
