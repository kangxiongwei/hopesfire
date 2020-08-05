package com.kxw.hopesfire.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.service.IRoleService;
import com.kxw.hopesfire.dao.convert.PageConvert;
import com.kxw.hopesfire.dao.entity.RoleEntity;
import com.kxw.hopesfire.dao.mapper.RoleMapper;
import com.kxw.hopesfire.dao.model.PagerModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

}
