package com.xwplay.crm.boot.service;

import com.xwplay.crm.boot.req.NormalLoginReq;
import com.xwplay.crm.boot.resp.AccountInfoResp;
import com.xwplay.crm.boot.resp.PermissionListResp;
import com.xwplay.crm.common.response.JsonResult;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountService {
    JsonResult login(HttpServletRequest request,NormalLoginReq req);
    PermissionListResp getUserPermissions(String userType);
    AccountInfoResp getAccountInfo(String userType);
}
