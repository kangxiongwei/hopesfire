package com.kxw.hopesfire.convert;

import com.kxw.hopesfire.entity.UserEntity;
import com.kxw.hopesfire.model.UserModel;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kangxiongwei
 * @date 2020-07-01 16:57
 */
public class UserConvert {

    /**
     * 单个转换
     *
     * @param user
     * @return
     */
    public static UserModel convertModel(UserEntity user) {
        if (user == null) {
            return null;
        }
        UserModel model = new UserModel();
        model.setId(user.getId());
        model.setUsername(user.getUsername());
        model.setNickname(user.getNickname());
        model.setSex(user.getSex());
        return model;
    }

    /**
     * 批量转换
     *
     * @param entities
     * @return
     */
    public static List<UserModel> convertModelList(List<UserEntity> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Lists.newArrayList();
        }
        return entities.stream().map(UserConvert::convertModel).collect(Collectors.toList());
    }

    /**
     * 单个转换
     *
     * @param user
     * @return
     */
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
