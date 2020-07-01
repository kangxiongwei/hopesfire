package com.kxw.hopesfire.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.convert.PageConvert;
import com.kxw.hopesfire.convert.UserConvert;
import com.kxw.hopesfire.entity.UserEntity;
import com.kxw.hopesfire.mapper.UserMapper;
import com.kxw.hopesfire.model.UserModel;
import com.kxw.hopesfire.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户管理服务
 *
 * @author kangxiongwei
 * @date 2020-07-01 16:22
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public void save(UserModel user) {
        UserEntity entity = UserConvert.convertEntity(user);
        if (user.getId() != null) {
            userMapper.updateById(entity);
        } else {
            userMapper.insert(entity);
            user.setId(entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        this.userMapper.deleteById(id);
    }

    @Override
    public IPage<UserModel> find(IPage<UserModel> page, UserModel user) {
        UserEntity entity = UserConvert.convertEntity(user);
        Wrapper<UserEntity> wrapper = new QueryWrapper<>(entity);
        IPage<UserEntity> entities = this.userMapper.selectPage(PageConvert.convertToEntity(page), wrapper);
        IPage<UserModel> models = PageConvert.convertToModel(entities);
        List<UserModel> list = entities.getRecords().stream().map(UserConvert::convertModel).collect(Collectors.toList());
        models.setRecords(list);
        return models;
    }
}
