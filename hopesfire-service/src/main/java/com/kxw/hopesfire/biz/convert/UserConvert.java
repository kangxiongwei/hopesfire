package com.kxw.hopesfire.biz.convert;

import com.kxw.hopesfire.dao.entity.UserEntity;
import com.kxw.hopesfire.biz.model.UserModel;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
        model.setPassword(user.getPassword());
        model.setNickname(user.getNickname());
        model.setSex(user.getSex());
        model.setHeadImg(user.getHeadImg());
        model.setTelephone(user.getTelephone());
        model.setAddress(user.getAddress());
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
            return new ArrayList<>();
        }
        return entities.stream().map(UserConvert::convertModel).collect(Collectors.toList());
    }

}
