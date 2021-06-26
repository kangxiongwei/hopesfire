package com.kxw.hopesfire.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kxw.hopesfire.web.model.HttpBaseModel;

/**
 * @author kangxiongwei
 * Created on 2021-01-14
 */
@RestController
@RequestMapping("/ctl/task")
public class TaskController {


    /**
     * 保存task版本信息
     *
     * @return
     */
    @PostMapping("/version/save")
    public HttpBaseModel save() {


        return HttpBaseModel.buildSuccess(null);
    }


}
