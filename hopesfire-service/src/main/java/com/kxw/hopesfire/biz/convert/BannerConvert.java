package com.kxw.hopesfire.biz.convert;

import java.util.List;

import com.kxw.hopesfire.biz.model.BannerModel;
import com.kxw.hopesfire.biz.model.BannerTreeModel;
import com.kxw.hopesfire.dao.entity.BannerEntity;

/**
 * 转换树节点的属性
 *
 * @author kangxiongwei
 * @date 2020/9/15 1:50 下午
 */
public class BannerConvert {

    public static BannerTreeModel convertTree(BannerEntity entity) {
        BannerTreeModel node = new BannerTreeModel();
        node.setId(entity.getId());
        node.setTitle(entity.getBanner());
        node.setValue(entity.getId());
        node.setLabel(entity.getBanner());
        node.setBanner(BaseConvert.convertModel(new BannerModel(), entity));
        return node;
    }

    public static void convertTreeChildren(BannerTreeModel parent, List<BannerTreeModel> children) {
        parent.setChildren(children);
        for (BannerTreeModel n : children) {
            BannerModel banner = n.getBanner();
            banner.setParent(parent.getBanner());
        }
    }

}
