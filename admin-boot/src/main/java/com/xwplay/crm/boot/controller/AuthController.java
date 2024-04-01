package com.xwplay.crm.boot.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xwplay.crm.boot.entity.AccountEntity;
import com.xwplay.crm.boot.req.NormalLoginReq;
import com.xwplay.crm.boot.service.AccountService;
import com.xwplay.crm.common.response.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @PostMapping("login")
    public JsonResult login(@RequestBody NormalLoginReq req) {
        return accountService.login(req);
    }

}
