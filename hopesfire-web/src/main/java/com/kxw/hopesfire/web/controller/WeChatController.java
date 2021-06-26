package com.kxw.hopesfire.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kxw.hopesfire.web.config.ApplicationConfiguration;
import com.kxw.hopesfire.web.model.HttpBaseModel;

/**
 * 微信公众号的操作
 *
 * @author kangxiongwei
 * @date 2021-03-27
 */
@RestController
@RequestMapping("/wx/token")
public class WeChatController {

    @Resource
    private ApplicationConfiguration applicationConfiguration;

    @RequestMapping("/get")
    public HttpBaseModel getToken() {

        return HttpBaseModel.buildSuccess(applicationConfiguration.getWxAppSecret());
    }


}
