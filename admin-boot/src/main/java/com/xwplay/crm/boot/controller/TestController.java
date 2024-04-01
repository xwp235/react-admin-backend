package com.xwplay.crm.boot.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xwplay.crm.boot.entity.AccountEntity;
import com.xwplay.crm.boot.entity.MenuEntity;
import com.xwplay.crm.boot.mapper.AccountMapper;
import com.xwplay.crm.boot.mapper.MenuMapper;
import com.xwplay.crm.common.response.JsonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.dev33.satoken.stp.StpUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final AccountMapper accountMapper;
    private final MenuMapper menuMapper;

    @GetMapping("1")
    public String test(){
        throw new RuntimeException("11");

    }

    // 测试登录，浏览器访问： http://localhost:8080/api/doLogin?username=admin&password=123456
    @GetMapping("doLogin")
    public JsonResult doLogin(String username, String password) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        if("admin".equals(username) && "123456".equals(password)) {
            var accountQ = Wrappers.<AccountEntity>lambdaQuery();
            accountQ.eq(AccountEntity::getMaUsername, username);
            var account = accountMapper.selectOne(accountQ);
            StpUtil.login(account.getMaUserId());
            return JsonResult.ok("登陆成功").setData(account);
        }
        return JsonResult.error("登录失败");
    }

    // 查询登录状态，浏览器访问： http://localhost:8080/api/isLogin
    @GetMapping("isLogin")
    public JsonResult isLogin() {
        return JsonResult.ok("当前会话是否登录：" + StpUtil.isLogin());
    }

    @GetMapping("allMenus")
    public List<MenuEntity> getMenus() {
        return menuMapper.selectList(null);
    }

}
