package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.RoleModel;
import com.kxw.hopesfire.dao.model.PagerModel;

/**
 * 角色控制
 *
 * @author kangxiongwei
 * @date 2020/8/4 1:30 下午
 */
public interface IRoleService {

    /**
     * 保存角色
     *
     * @param role
     */
    void save(RoleModel role);

    /**
     * 删除角色
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 获取角色信息
     *
     * @param id
     * @return
     */
    RoleModel get(Integer id);

    /**
     * 查询角色
     *
     * @param pager
     */
    PagerModel find(RoleModel pager);

}
