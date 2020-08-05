package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.biz.model.UserModel;

import java.util.List;

/**
 * 用户管理服务
 *
 * @author kangxiongwei
 * @date 2020-07-01 16:21
 */
public interface IUserService extends IBaseService<UserModel> {

    /**
     * 给用户添加多个角色
     *
     * @param userId
     * @param roleIds
     */
    void addUserRoles(Long userId, List<Long> roleIds);

    /**
     * 删除用户的多个角色
     *
     * @param userId
     * @param roleIds
     */
    void deleteUserRoles(Long userId, List<Long> roleIds);

    /**
     * 根据用户ID删除所有角色
     *
     * @param userId
     */
    void deleteUserRoles(Long userId);

    /**
     * 查询某个用户的对应的所有角色
     *
     * @param userId
     * @return
     */
    List<RoleModel> listUserRoles(Long userId);

}
