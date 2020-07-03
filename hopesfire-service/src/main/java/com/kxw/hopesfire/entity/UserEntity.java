package com.kxw.hopesfire.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author kangxiongwei
 * @date 2020-07-01 16:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user")
public class UserEntity extends BaseEntity {

    private String username;
    private String password;
    private String nickname;
    private Integer sex;
    private Date createTime;
    private Date updateTime;

}
