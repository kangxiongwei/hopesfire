package com.kxw.hopesfire.convert;

import com.kxw.hopesfire.entity.UserEntity;
import com.kxw.hopesfire.model.UserModel;

/**
 * @author kangxiongwei
 * @date 2020-07-01 16:57
 */
public class UserConvert  {

    public static UserModel convertModel(UserEntity user) {
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setUsername(user.getUsername());
        model.setNickname(user.getNickname());
        model.setSex(user.getSex());
        return model;
    }

    public static UserEntity convertEntity(UserModel user) {
        if (user == null) {
            return null;
        }
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setUsername(user.getUsername());
        entity.setNickname(user.getNickname());
        entity.setSex(user.getSex());
        return entity;
    }

}
