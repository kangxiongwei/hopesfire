package com.kxw.hopesfire.bi.controller;

import com.kxw.hopesfire.bi.service.BiUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kangxiongwei
 * @date 2020-07-14 15:59
 */
@RestController
@RequestMapping("/bi/user")
public class BiUserController {

    @Resource
    private BiUserService biUserService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public int getAllUser() {
        return biUserService.getAllUser();
    }

}
