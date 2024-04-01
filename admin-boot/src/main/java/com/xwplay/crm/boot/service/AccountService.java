package com.xwplay.crm.boot.service;

import com.xwplay.crm.boot.req.NormalLoginReq;
import com.xwplay.crm.common.response.JsonResult;

public interface AccountService {
    JsonResult login(NormalLoginReq req);
}
