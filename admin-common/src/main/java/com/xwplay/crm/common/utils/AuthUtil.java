package com.xwplay.crm.common.utils;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.xwplay.crm.common.bo.AccountBO;
import com.xwplay.crm.common.constants.AppConstants;

public class AuthUtil {

    public static SaSession getSession() {
       return StpUtil.getSession();
    }

    public static String getUsername() {
        return get().getUsername();
    }
    public static Long getUserId() {
        return get().getUserId();
    }

    public static void set(AccountBO account) {
        getSession().set(AppConstants.SA_LOGIN_USER, JSONUtil.toJsonStr(account));
    }

    public static AccountBO get() {
        String account = (String)getSession().get(AppConstants.SA_LOGIN_USER);
        return JSONUtil.toBean(account, AccountBO.class);
    }

    public static boolean isLogin() {
        return StpUtil.isLogin();
    }

}
