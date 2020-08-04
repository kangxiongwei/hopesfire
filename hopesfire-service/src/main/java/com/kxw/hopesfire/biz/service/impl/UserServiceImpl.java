package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.UserConvert;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.IUserService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.UserEntity;
import com.kxw.hopesfire.dao.mapper.UserMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public UserModel get(Long id) {
        UserEntity entity = this.userMapper.selectById(id);
        return UserConvert.convertModel(entity);
    }

    @Override
    public PagerModel find(UserModel user) {
        Wrapper<UserEntity> wrapper = new QueryWrapper<>(UserConvert.convertEntity(user));
        IPage<UserEntity> entities = this.userMapper.selectPage(PageConvert.convertPage(user), wrapper);
        List<UserModel> records = UserConvert.convertModelList(entities.getRecords());
        return PageConvert.convertToModel(entities, records);
    }

}
