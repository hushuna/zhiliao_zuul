package com.zhiliao.controller;

import com.zhiliao.comment.Constants;
import com.zhiliao.comment.Result;
import com.zhiliao.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hsn
 * @description:
 * @date: 2019/10/17 14:14
 **/
@RestController
@RequestMapping(value = "/key")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    private Logger logger = LoggerFactory.getLogger(AuthorizationController.class);


    /**
     * 获取公钥
     */
    @GetMapping(value = "/getPublicKey")
    public Result getPublicKey(){
        try {
            System.out.println(111);
            return Result.createSuccess("200",authorizationService.getPublicKey());
        } catch (Exception e) {
            logger.error("<<======获取公钥-失败 url:{{}} params:{} reason:{}", "/key/getPublicKey", e);
            return Result.createError();
        }
    }


    /**
     * 获取token
     *
     * @param secretMessage 加密后的信息
     * @param publicKey     公钥
     * @return Result 通用返回对象
     */
    @GetMapping(value = "/gettoken")
    public Result getToken(String secretMessage, String publicKey) {
        logger.info("AccessAPI:url={},params={secretMessage={},publicKey={}}", "/key/gettoken", secretMessage, publicKey);
        if (StringUtils.isEmpty(publicKey)) {
            return Result.createError();
        }
        if (StringUtils.isEmpty(secretMessage)) {
            return Result.createError();
        }

        //加入请求头判断
        try {
            String key = authorizationService.getToken(secretMessage, publicKey);
            return Result.createSuccess("get token success", key);
        } catch (Exception e) {
//            e.printStackTrace();
            logger.error("<<<===获取token失败", e);
            return Result.createError(Constants.UNKNOWN_EXCEPTION, Constants.ERROR_SYSTEM_MEG);
        }
    }
}
