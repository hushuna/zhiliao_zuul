package com.zhiliao.controller;

import com.zhiliao.comment.Result;
import com.zhiliao.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hsn
 * @description:
 * @date: 2019/10/17 14:14
 **/
@RestController
@RequestMapping(value = "/authorization")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    private Logger logger = LoggerFactory.getLogger(AuthorizationController.class);


    /**
     * 获取公钥
     */
    @RequestMapping(value = "/getPublicKey")
    public Result getPublicKey(){
        try {
            return Result.createSuccess("200",authorizationService.getPublicKey());
        } catch (Exception e) {
            return Result.createError();
        }
    }
}
