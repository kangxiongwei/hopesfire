package com.kxw.hopesfire.biz.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 栏目的树形结构
 *
 * @author kangxiongwei
 * @date 2020/9/15 12:23 下午
 */
@Data
public class BannerTreeModel {

    public static final BannerTreeModel ROOT_BANNER = new BannerTreeModel();

    private Long id;                                //当前节点ID
    private String title;                           //当前节点标题
    private Long value;                             //值--给级联用
    private String label;                           //标识--给级联用
    private List<BannerTreeModel> children;         //孩子节点
    private BannerModel banner;                     //当前栏目信息
    private boolean contextmenu = true;             //是否展示右键菜单
    private boolean expand = true;                  //是否展开子菜单

    static {
        //初始化一个根栏目，作为树的根节点
        ROOT_BANNER.setId(0L);
        ROOT_BANNER.setTitle("全部栏目");
        ROOT_BANNER.setValue(0L);
        ROOT_BANNER.setLabel("全部栏目");
        BannerModel root = new BannerModel(0L);
        root.setBanner(ROOT_BANNER.getTitle());
        ROOT_BANNER.setBanner(root);
        ROOT_BANNER.setChildren(new ArrayList<>());
    }

}
