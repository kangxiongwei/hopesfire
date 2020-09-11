package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.AttachModel;
import com.kxw.hopesfire.biz.service.IAttachService;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 网站主页接口
 *
 * @author kangxiongwei
 * @date 2020/9/11 7:12 下午
 */
@RestController
@RequestMapping("/ctl/home")
public class HomeController extends BaseController {

    @Resource
    private IAttachService attachService;

    /**
     * 首页轮播图
     *
     * @return
     */
    @PostMapping("/carousel")
    public HttpBaseModel listHomeImages() {
        AttachModel model = new AttachModel();
        model.setUsername(this.getUsername());
        List<AttachModel> images = attachService.list(model);
        return HttpBaseModel.buildSuccess(images);
    }

}
