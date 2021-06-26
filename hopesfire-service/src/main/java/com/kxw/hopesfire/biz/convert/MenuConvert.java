package com.kxw.hopesfire.biz.convert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import com.kxw.hopesfire.biz.model.MenuModel;
import com.kxw.hopesfire.dao.entity.MenuEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 菜单转换器
 *
 * @author kangxiongwei
 * @date 2021-06-26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MenuConvert {

    /**
     * 根据当前的层级，组装树
     *
     * @param menus 菜单
     * @return 树
     */
    public static List<MenuModel> convertMenuTree(List<MenuEntity> menus) {
        if (CollectionUtils.isEmpty(menus)) {
            return new ArrayList<>();
        }
        Map<Long, List<MenuEntity>> parentMap = menus.stream().collect(Collectors.groupingBy(MenuEntity::getParentId));
        List<MenuEntity> roots = parentMap.get(0L);
        List<MenuModel> tree = new ArrayList<>();
        for (MenuEntity root : roots) {
            tree.add(convertMenuTree(menus, root));
        }
        return tree;
    }

    /**
     * 装换单棵树
     */
    public static MenuModel convertMenuTree(List<MenuEntity> menus, MenuEntity parent) {
        MenuModel parentModel = convertMenu(parent);
        Map<Long, List<MenuEntity>> parentMap = menus.stream().collect(Collectors.groupingBy(MenuEntity::getParentId));
        List<MenuEntity> childrenList = parentMap.getOrDefault(parent.getId(), new ArrayList<>());
        List<MenuModel> children = Lists.newArrayList();
        for (MenuEntity child : childrenList) {
            children.add(convertMenuTree(menus, child));
        }
        parentModel.setChildren(children);
        return parentModel;
    }

    public static MenuModel convertMenu(MenuEntity entity) {
        MenuModel model = BaseConvert.convertModel(new MenuModel(), entity);
        model.setTo(entity.getRoutePath());
        return model;
    }

}
