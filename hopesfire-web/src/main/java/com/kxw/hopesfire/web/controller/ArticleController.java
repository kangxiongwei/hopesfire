package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.ArticleModel;
import com.kxw.hopesfire.biz.service.IArticleService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章管理
 *
 * @author kangxiongwei
 * @date 2020/9/14 6:02 下午
 */
@RestController
@RequestMapping("/ctl/article")
public class ArticleController extends BaseController {

    @Resource
    private IArticleService articleService;

    @PostMapping("/save")
    public HttpBaseModel save(@RequestBody ArticleModel model) {
        model.setAuthor(this.getUsername());
        this.articleService.save(model);
        return HttpBaseModel.buildSuccess(model.getId());
    }

    /**
     * 查询文章列表
     *
     * @param model
     * @return
     */
    @PostMapping("/list")
    public HttpBaseModel list(@RequestBody ArticleModel model) {
        model.setAuthor(this.getUsername());
        List<ArticleModel> list = this.articleService.list(model);
        return HttpBaseModel.buildSuccess(list);
    }

    /**
     * 分页查询接口
     *
     * @param model
     * @return
     */
    @PostMapping("/find")
    public HttpBaseModel find(@RequestBody ArticleModel model) {
        model.setAuthor(this.getUsername());
        PagerModel pager = this.articleService.find(model);
        return HttpBaseModel.buildSuccess(pager);
    }


}
