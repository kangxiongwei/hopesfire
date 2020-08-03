package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kxw.hopesfire.biz.convert.UserConvert;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.ILoginService;
import com.kxw.hopesfire.dao.entity.UserEntity;
import com.kxw.hopesfire.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kangxiongwei
 * @date 2020-07-03 18:21
 */
@Service("loginService")
public class LoginServiceImpl implements ILoginService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserModel get(String username) {
        Map<String, Object> columns = new HashMap<>(2);
        columns.put("username", username);
        List<UserEntity> entities = userMapper.selectByMap(columns);
        return CollectionUtils.isEmpty(entities) ? null : UserConvert.convertModel(entities.get(0));
    }

    @Override
    public UserModel login(String username, String password) {
        UserEntity entity = new UserEntity();
        entity.setUsername(username);
        Wrapper<UserEntity> wrapper = new QueryWrapper<>(entity);
        UserEntity user = userMapper.selectOne(wrapper);
        return UserConvert.convertModel(user);
    }

}
