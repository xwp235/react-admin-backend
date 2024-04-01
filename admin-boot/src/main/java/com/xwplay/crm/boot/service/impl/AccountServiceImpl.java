package com.xwplay.crm.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xwplay.crm.boot.entity.AccountEntity;
import com.xwplay.crm.boot.mapper.AccountMapper;
import com.xwplay.crm.boot.req.NormalLoginReq;
import com.xwplay.crm.boot.service.AccountService;
import com.xwplay.crm.common.response.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;

    @Override
    public JsonResult login(NormalLoginReq req) {
        var username = req.getUsername();
        var password = req.getPassword();
        if("admin".equals(username) && "123456".equals(password)) {
            var accountQ = Wrappers.<AccountEntity>lambdaQuery();
            accountQ.eq(AccountEntity::getMaUsername, username);
            var account = accountMapper.selectOne(accountQ);
            StpUtil.login(account.getMaUserId());
            var tokenInfo = StpUtil.getTokenInfo();
            return JsonResult.ok("登录成功").setData(
                    Map.of(tokenInfo.getTokenName(), tokenInfo.getTokenValue())
            );
        }
        return JsonResult.error("登录失败");
    }
}
