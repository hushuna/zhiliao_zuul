package com.zhiliao.controller;

import com.zhiliao.comment.Result;
import com.zhiliao.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * 获取公钥
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getPublicKey")
    public Result getPublicKey() throws Exception {
        System.out.println(11111);
        return Result.createSuccess("200",authenticationService.getPublicKey());
    }

    /**
     * 获取token
     */
    @PostMapping(value = "/PostMapping")
    public Result getToken(String ciphertext,String publicKey){

        return Result.createSuccess("200",null);
    }
}
