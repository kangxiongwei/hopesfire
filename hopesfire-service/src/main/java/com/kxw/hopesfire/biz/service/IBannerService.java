package com.kxw.hopesfire.biz.service;

import com.kxw.hopesfire.biz.model.BannerModel;
import com.kxw.hopesfire.biz.model.BannerTreeModel;

import java.util.List;
import java.util.Map;

/**
 * @author kangxiongwei
 * @date 2020/9/14 5:38 下午
 */
public interface IBannerService extends IBaseService<BannerModel> {

    /**
     * 获取栏目类型
     *
     * @return
     */
    List<Map<String, Object>> listBannerTypes();

    /**
     * 查询栏目的树形结构
     *
     * @param model
     * @return
     */
    List<BannerTreeModel> listBannerTree(BannerModel model);

}
