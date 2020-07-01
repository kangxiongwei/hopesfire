package com.kxw.hopesfire.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kxw.hopesfire.model.UserModel;

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
     * 分页查询用户列表
     *
     * @param page
     * @param user
     * @return
     */
    IPage<UserModel> find(IPage<UserModel> page, UserModel user);


}
