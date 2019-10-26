package com.zhiliao.controller;

import com.zhiliao.comment.BizException;
import com.zhiliao.comment.Result;
import com.zhiliao.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    private Logger logger=LoggerFactory.getLogger(AuthenticationController.class);

    /**
     * 获取token
     */
    @PostMapping(value = "/getToken")
    public Result getToken() {
        try {
            return Result.createSuccess("200",authenticationService.getToken());
        }catch (BizException b){
            logger.error("<<======获取token-失败 url:{{}} params:{}","/authentication/getToken",b);
            return Result.createBizError(-500,b.getMessage());
        }catch (Exception e) {
            logger.error("<<======获取token-失败 url:{{}} params:{}","/authentication/getToken",e);
            return Result.createError();
        }
    }
}
