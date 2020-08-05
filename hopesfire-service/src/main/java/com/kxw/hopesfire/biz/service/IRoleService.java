package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.GroupModel;
import com.kxw.hopesfire.biz.model.RoleGroupModel;
import com.kxw.hopesfire.biz.model.RoleModel;

import java.util.List;

/**
 * 角色控制
 *
 * @author kangxiongwei
 * @date 2020/8/4 1:30 下午
 */
public interface IRoleService extends IBaseService<RoleModel> {

    /**
     * 给角色添加分组
     *
     * @param roleGroup
     */
    void addRoleGroups(RoleGroupModel roleGroup);

    /**
     * 删除角色对应的所有分组
     *
     * @param roleId
     */
    void deleteRoleGroups(Long roleId);

    /**
     * 查询角色对应的所有分组
     *
     * @param roleId
     * @return
     */
    List<GroupModel> listRoleGroups(Long roleId);

}
