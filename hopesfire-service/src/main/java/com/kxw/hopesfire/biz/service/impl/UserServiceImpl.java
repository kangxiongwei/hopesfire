package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.convert.UserConvert;
import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.biz.service.IUserService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.RoleEntity;
import com.kxw.hopesfire.dao.entity.UserEntity;
import com.kxw.hopesfire.dao.entity.UserRoleEntity;
import com.kxw.hopesfire.dao.mapper.RoleMapper;
import com.kxw.hopesfire.dao.mapper.UserMapper;
import com.kxw.hopesfire.dao.mapper.UserRoleMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public void save(UserModel user) {
        UserEntity entity = BaseConvert.convertEntity(user, new UserEntity());
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

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    @Override
    public List<UserModel> list(UserModel model) {
        return null;
    }

    @Override
    public PagerModel find(UserModel user) {
        Wrapper<UserEntity> wrapper = BaseConvert.convertWrapper(user, new UserEntity());
        IPage<UserEntity> entities = this.userMapper.selectPage(PageConvert.convertPage(user), wrapper);
        List<UserModel> records = UserConvert.convertModelList(entities.getRecords());
        return PageConvert.convertToModel(entities, records);
    }

    /**
     * 给用户添加多个角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUserRoles(Long userId, List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds) || userId == null) {
            return;
        }
        List<UserRoleEntity> entities = new ArrayList<>(roleIds.size());
        this.deleteUserRoles(userId);
        for (Long roleId: roleIds) {
            UserRoleEntity entity = new UserRoleEntity();
            entity.setUserId(userId);
            entity.setRoleId(roleId);
            entities.add(entity);
        }
        this.userRoleMapper.batchInsert(entities);
    }



    /**
     * 删除用户的多个角色
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void deleteUserRoles(Long userId, List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds) || userId == null) {
            return;
        }
        this.userRoleMapper.batchDelete(userId, roleIds);
    }

    /**
     * 根据用户ID删除所有角色
     *
     * @param userId
     */
    @Override
    public void deleteUserRoles(Long userId) {
        UserRoleEntity entity = new UserRoleEntity();
        entity.setUserId(userId);
        Wrapper<UserRoleEntity> wrapper = new QueryWrapper<>(entity);
        this.userRoleMapper.delete(wrapper);
    }

    private List<Long> listUserRoleIds(Long userId) {
        UserRoleEntity entity = new UserRoleEntity();
        entity.setUserId(userId);
        Wrapper<UserRoleEntity> query = new QueryWrapper<>(entity);
        List<UserRoleEntity> entities = this.userRoleMapper.selectList(query);
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }
        return entities.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * 查询某个用户的对应的所有角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<RoleModel> listUserRoles(Long userId) {
        List<Long> roleIds = this.listUserRoleIds(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        List<RoleEntity> roles = this.roleMapper.selectBatchIds(roleIds);
        return BaseConvert.convertModels(new RoleModel(), roles);
    }

}
