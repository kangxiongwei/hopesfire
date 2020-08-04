package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.UserModel;
import com.kxw.hopesfire.dao.model.PagerModel;

import java.util.List;

/**
 * 用户管理服务
 *
 * @author kangxiongwei
 * @date 2020-07-01 16:21
 */
public interface IUserService {

    /**
     * 保存用户
     *
     * @param user
     */
    void save(UserModel user);

    /**
     * 删除用户
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 获取单个用户信息
     *
     * @param id
     * @return
     */
    UserModel get(Long id);

    /**
     * 分页查询用户列表
     *
     * @param user
     * @return
     */
    PagerModel find(UserModel user);

    /**
     * 给用户添加多个角色
     *
     * @param userId
     * @param roleIds
     */
    void addUserRoles(Long userId, List<Long> roleIds);

}
