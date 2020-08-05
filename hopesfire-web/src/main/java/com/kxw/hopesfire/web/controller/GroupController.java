package com.kxw.hopesfire.web.controller;

import com.kxw.hopesfire.biz.model.GroupModel;
import com.kxw.hopesfire.biz.service.IGroupService;
import com.kxw.hopesfire.dao.model.PagerModel;
import com.kxw.hopesfire.web.model.HttpBaseModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分组管理接口
 *
 * @author kangxiongwei
 * @date 2020/8/5 11:49 上午
 */
@RestController
@RequestMapping("/ctl/auth")
public class GroupController {

    @Resource
    private IGroupService groupService;

    @PostMapping("/group/save")
    public HttpBaseModel save(@RequestBody GroupModel group) {
        this.groupService.save(group);
        return HttpBaseModel.buildSuccess(group.getId());
    }

    @GetMapping("/group/delete/{id}")
    public HttpBaseModel delete(@PathVariable Long id) {
        this.groupService.delete(id);
        return HttpBaseModel.buildSuccess(id);
    }

    @PostMapping("/group/find")
    public HttpBaseModel find(@RequestBody GroupModel group) {
        PagerModel model = this.groupService.find(group);
        return HttpBaseModel.buildSuccess(model);
    }

    @PostMapping("/group/list")
    public HttpBaseModel list(@RequestBody GroupModel group) {
        List<GroupModel> list = this.groupService.list(group);
        return HttpBaseModel.buildSuccess(list);
    }

}
