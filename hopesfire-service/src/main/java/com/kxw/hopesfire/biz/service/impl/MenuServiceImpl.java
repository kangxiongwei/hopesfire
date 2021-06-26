package com.kxw.hopesfire.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.kxw.hopesfire.biz.convert.BaseConvert;
import com.kxw.hopesfire.biz.convert.MenuConvert;
import com.kxw.hopesfire.biz.model.MenuModel;
import com.kxw.hopesfire.biz.service.IMenuService;
import com.kxw.hopesfire.dao.entity.MenuEntity;
import com.kxw.hopesfire.dao.mapper.MenuMapper;
import com.kxw.hopesfire.dao.model.PagerModel;

/**
 * 菜单管理
 *
 * @author kangxiongwei
 * @date 2021-06-26
 */
@Service("menuService")
public class MenuServiceImpl implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    /**
     * 保存数据，有ID时更新，无ID时插入
     *
     * @param model
     */
    @Override
    public void save(MenuModel model) {

    }

    /**
     * 删除数据，根据ID删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {

    }

    /**
     * 根据ID查询数据对象
     *
     * @param id
     * @return
     */
    @Override
    public MenuModel get(Long id) {
        return null;
    }

    /**
     * 带条件查询结果
     *
     * @param model
     * @return
     */
    @Override
    public List<MenuModel> list(MenuModel model) {
        Wrapper<MenuEntity> wrapper = BaseConvert.convertSortWrapper(model, new MenuEntity());
        List<MenuEntity> entities = menuMapper.selectList(wrapper);
        return MenuConvert.convertMenuTree(entities);
    }

    /**
     * 带条件分页查询结果
     *
     * @param model
     * @return
     */
    @Override
    public PagerModel find(MenuModel model) {
        return null;
    }
}
