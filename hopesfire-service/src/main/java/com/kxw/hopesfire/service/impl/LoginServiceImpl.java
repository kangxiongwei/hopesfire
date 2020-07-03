package com.kxw.hopesfire.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kxw.hopesfire.convert.UserConvert;
import com.kxw.hopesfire.entity.UserEntity;
import com.kxw.hopesfire.exception.ServiceException;
import com.kxw.hopesfire.exception.ServiceExceptionEnum;
import com.kxw.hopesfire.mapper.UserMapper;
import com.kxw.hopesfire.model.UserModel;
import com.kxw.hopesfire.service.ILoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020-07-03 18:21
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserModel login(String username, String password) {
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        Wrapper<UserEntity> wrapper = new QueryWrapper<>(entity);
        UserEntity user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw ServiceException.build(ServiceExceptionEnum.LOGIN_USER_NOT_EXIST);
        }
        if (!password.equals(user.getPassword())) {
            throw ServiceException.build(ServiceExceptionEnum.LOGIN_PASSWORD_ERROR);
        }
        return UserConvert.convertModel(user);
    }

}
