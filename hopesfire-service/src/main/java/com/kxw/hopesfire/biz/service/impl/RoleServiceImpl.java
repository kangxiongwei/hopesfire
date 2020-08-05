package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.model.GroupModel;
import com.kxw.hopesfire.biz.model.RoleGroupModel;
import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.service.IRoleService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.GroupEntity;
import com.kxw.hopesfire.dao.entity.RoleEntity;
import com.kxw.hopesfire.dao.entity.RoleGroupEntity;
import com.kxw.hopesfire.dao.mapper.GroupMapper;
import com.kxw.hopesfire.dao.mapper.RoleGroupMapper;
import com.kxw.hopesfire.dao.mapper.RoleMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色控制
 *
 * @author kangxiongwei
 * @date 2020/8/4 1:33 下午
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private RoleGroupMapper roleGroupMapper;

    /**
     * 保存角色
     *
     * @param role
     */
    @Override
    public void save(RoleModel role) {
        RoleEntity entity = BaseConvert.convertEntity(role, new RoleEntity());
        if (entity.getId() == null) {
            this.roleMapper.insert(entity);
        } else {
            this.roleMapper.updateById(entity);
        }
    }

    /**
     * 删除角色
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.roleMapper.deleteById(id);
    }

    /**
     * 获取角色信息
     *
     * @param id
     * @return
     */
    @Override
    public RoleModel get(Long id) {
        RoleEntity entity = this.roleMapper.selectById(id);
        return entity == null ? null : BaseConvert.convertModel(new RoleModel(), entity);
    }

    /**
     * 查询角色
     *
     * @param role
     */
    @Override
    public PagerModel find(RoleModel role) {
        Wrapper<RoleEntity> wrapper = BaseConvert.convertWrapper(role, new RoleEntity());
        IPage<RoleEntity> entities = this.roleMapper.selectPage(PageConvert.convertPage(role), wrapper);
        return BaseConvert.convertPageModel(new RoleModel(), entities);
    }

    /**
     * 查询角色
     *
     * @param role
     * @return
     */
    @Override
    public List<RoleModel> list(RoleModel role) {
        Wrapper<RoleEntity> wrapper = BaseConvert.convertWrapper(role, new RoleEntity());
        List<RoleEntity> entities = this.roleMapper.selectList(wrapper);
        return BaseConvert.convertModels(new RoleModel(), entities);
    }

    /**
     * 给角色添加分组
     *
     * @param roleGroup
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void addRoleGroups(RoleGroupModel roleGroup) {
        if (roleGroup.getRoleId() == null || CollectionUtils.isEmpty(roleGroup.getGroupIds())) {
            return;
        }
        this.deleteRoleGroups(roleGroup.getRoleId());
        List<RoleGroupEntity> list = new ArrayList<>();
        for (Long groupId: roleGroup.getGroupIds()) {
            RoleGroupEntity entity = new RoleGroupEntity();
            entity.setRoleId(roleGroup.getRoleId());
            entity.setGroupId(groupId);
            list.add(entity);
        }
        this.roleGroupMapper.batchInsert(list);
    }

    /**
     * 删除角色对应的所有分组
     *
     * @param roleId
     */
    @Override
    public void deleteRoleGroups(Long roleId) {
        if (roleId == null) {
            return;
        }
        RoleGroupEntity entity = new RoleGroupEntity();
        entity.setRoleId(roleId);
        Wrapper<RoleGroupEntity> wrapper = new QueryWrapper<>(entity);
        this.roleGroupMapper.delete(wrapper);
    }

    /**
     * 查询角色对应的所有分组
     *
     * @param roleId
     * @return
     */
    @Override
    public List<GroupModel> listRoleGroups(Long roleId) {
        RoleGroupEntity entity = new RoleGroupEntity();
        entity.setRoleId(roleId);
        Wrapper<RoleGroupEntity> wrapper = new QueryWrapper<>(entity);
        List<RoleGroupEntity> entities = this.roleGroupMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return new ArrayList<>();
        }
        List<Long> groupIds = entities.stream().map(RoleGroupEntity::getGroupId).collect(Collectors.toList());
        List<GroupEntity> groups = this.groupMapper.selectBatchIds(groupIds);
        return BaseConvert.convertModels(new GroupModel(), groups);
    }

}
