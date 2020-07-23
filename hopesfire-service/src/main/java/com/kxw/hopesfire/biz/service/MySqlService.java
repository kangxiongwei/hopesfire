package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.convert.UserConvert;
import com.kxw.hopesfire.dao.entity.UserEntity;
import com.kxw.hopesfire.dao.mapper.UserMapper;
import com.kxw.hopesfire.biz.model.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020-07-10 20:36
 */
@Service
public class MySqlService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private IUserService userService;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void update(UserModel user) {
        UserEntity entity = UserConvert.convertEntity(user);
        //this.insert(entity);
        userService.save(user);
        user.setNickname("updated");
        userService.save(user);
        //this.update(entity);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void insert(UserEntity entity) {
        userMapper.insert(entity);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void update(UserEntity entity) {
        userMapper.updateById(entity);
    }

}
