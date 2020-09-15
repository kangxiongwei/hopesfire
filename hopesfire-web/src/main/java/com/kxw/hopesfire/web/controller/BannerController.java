package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.convert.BannerConvert;
import com.kxw.hopesfire.biz.model.BannerModel;
import com.kxw.hopesfire.biz.model.BannerTreeModel;
import com.kxw.hopesfire.biz.service.IBannerService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 栏目管理
 *
 * @author kangxiongwei
 * @date 2020/9/14 6:33 下午
 */
@RestController
@RequestMapping("/ctl/banner")
public class BannerController extends BaseController {

    @Resource
    private IBannerService bannerService;

    @PostMapping("/save")
    public HttpBaseModel save(@RequestBody BannerModel model) {
        model.setUsername(this.getUsername());
        this.bannerService.save(model);
        return HttpBaseModel.buildSuccess(model.getId());
    }

    @PostMapping("/delete")
    public HttpBaseModel delete(@RequestBody BannerModel model) {
        this.bannerService.delete(model.getId());
        return HttpBaseModel.buildSuccess(model.getId());
    }

    @PostMapping("/list")
    public HttpBaseModel list(@RequestBody BannerModel model) {
        model.setUsername(this.getUsername());
        List<BannerModel> list = this.bannerService.list(model);
        return HttpBaseModel.buildSuccess(list);
    }

    @PostMapping("/find")
    public HttpBaseModel find(@RequestBody BannerModel model) {
        model.setUsername(this.getUsername());
        PagerModel pager = this.bannerService.find(model);
        return HttpBaseModel.buildSuccess(pager);
    }

    @PostMapping("/tree")
    public HttpBaseModel tree(@RequestBody BannerModel model) {
        model.setUsername(this.getUsername());
        List<BannerTreeModel> tree = this.bannerService.listBannerTree(model);
        BannerTreeModel root = BannerTreeModel.ROOT_BANNER;
        BannerConvert.convertTreeChildren(root, tree);
        return HttpBaseModel.buildSuccess(Collections.singletonList(root));
    }

    @PostMapping("/types")
    public HttpBaseModel listBannerTypes() {
        List<Map<String, Object>> bannerTypes = this.bannerService.listBannerTypes();
        return HttpBaseModel.buildSuccess(bannerTypes);
    }

}
