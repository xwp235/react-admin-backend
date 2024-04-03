package com.xwplay.crm.boot.controller;

import com.xwplay.crm.boot.req.NormalLoginReq;
import com.xwplay.crm.boot.resp.AccountInfoResp;
import com.xwplay.crm.boot.resp.PermissionListResp;
import com.xwplay.crm.boot.service.AccountService;
import com.xwplay.crm.common.response.JsonResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;

    @PostMapping("login")
    public JsonResult login(@RequestBody NormalLoginReq req, HttpServletRequest request) {
        return accountService.login(request,req);
    }

    @GetMapping("{userType}/permission")
    public PermissionListResp permissions(@PathVariable String userType){
        return accountService.getUserPermissions(userType);
    }

    @GetMapping("{userType}/profile")
    public AccountInfoResp profile(@PathVariable String userType) {
        return accountService.getAccountInfo(userType);
    }


}
