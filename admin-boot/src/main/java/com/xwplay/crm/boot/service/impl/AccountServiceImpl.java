package com.xwplay.crm.boot.service.impl;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xwplay.crm.boot.entity.AccountEntity;
import com.xwplay.crm.boot.entity.MenuEntity;
import com.xwplay.crm.boot.mapper.AccountMapper;
import com.xwplay.crm.boot.req.NormalLoginReq;
import com.xwplay.crm.boot.resp.AccountInfoResp;
import com.xwplay.crm.boot.resp.PermissionListResp;
import com.xwplay.crm.boot.service.AccountService;
import com.xwplay.crm.boot.service.MenuService;
import com.xwplay.crm.boot.vo.MenuVO;
import com.xwplay.crm.common.bo.AccountBO;
import com.xwplay.crm.common.constants.AppConstants;
import com.xwplay.crm.common.response.JsonResult;
import com.xwplay.crm.common.utils.AuthUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final MenuService menuService;

    @Override
    public JsonResult login(HttpServletRequest request,NormalLoginReq req) {
        var username = req.getUsername();
        var password = req.getPassword();
        if("admin".equals(username) && "123456".equals(password)) {
            var accountQ = Wrappers.<AccountEntity>lambdaQuery();
            accountQ.eq(AccountEntity::getMaUsername, username);
            var account = accountMapper.selectOne(accountQ);
            UserAgent ua = UserAgentUtil.parse(request.getHeader(HttpHeaders.USER_AGENT));
            var device = ua.getPlatform().toString();
            var loginModel =new SaLoginModel()
                    // 此次登录的客户端设备类型, 用于[同端互斥登录]时指定此次登录的设备类型
                    .setDevice(device)
                    // 是否为持久Cookie（临时Cookie在浏览器关闭时会自动删除，持久Cookie在重新打开后依然存在）
                    .setIsLastingCookie(true)
                    // 是否在登录后将 Token 写入到响应头
                    .setIsWriteHeader(true);
            var accountInfo = new AccountBO();
            accountInfo.setUsername(username)
                            .setDevice(device)
                    .setUserId(account.getMaUserId());
            StpUtil.login(account.getMaUserId(),loginModel);
            AuthUtil.set(accountInfo);

            var tokenInfo = StpUtil.getTokenInfo();
            return JsonResult.ok("登录成功").setData(
                    Map.of(tokenInfo.getTokenName(), tokenInfo.getTokenValue())
            );
        }
        return JsonResult.error("登录失败");
    }

    @Override
    public PermissionListResp getUserPermissions(String userType) {
        var userId =  AuthUtil.getUserId();

        var allMenus = menuService.getAllValidMenus();

        List<String> buttonList = CollUtil.newArrayList();
        List<MenuVO> menuList = CollUtil.newArrayList();

        if (userId != AppConstants.ADMIN_USER_ID){
              // todo 对allMenus里的菜单进行验权，不是超级管理员就需要校验权限，判断哪些菜单和按钮允许被访问
        }

        for (MenuEntity menu : allMenus) {
            var menuType = menu.getSmType();
            // 得到所有按钮权限
            if (menuType == AppConstants.BUTTON_MENU){
                buttonList.add(menu.getSmCode());
            }
            if (menuType == AppConstants.PATH_MENU || menuType == AppConstants.URL_MENU){
                var menuVO = new MenuVO();
                menuVO.setId(menu.getSmId())
                        .setParentId(menu.getSmParentId())
                        .setPath(menu.getSmPath())
                        .setIcon(menu.getSmIcon())
                        .setMenuName(menu.getSmName().getZh_CN())
                        .setMenuType(menu.getSmType())
                        .setSort(menu.getSmSort())
                        .setLevel(menu.getSmLevel())
                        .setLevelChain(menu.getSmLevelChain());
                menuList.add(menuVO);
            }
        }

        // 递归生成树状菜单
        menuList = generateTreeMenu(menuList, null);

        return new PermissionListResp(buttonList,menuList);
    }

    @Override
    public AccountInfoResp getAccountInfo(String userType) {
        var userId =  AuthUtil.getUserId();
        var accountQ =  Wrappers.<AccountEntity>lambdaQuery();
        accountQ.eq(AccountEntity::getMaUserId, userId);
        var dbAccount = accountMapper.selectOne(accountQ);
        var result = new AccountInfoResp();
        result.setUsername(dbAccount.getMaUsername())
                .setUserId(dbAccount.getMaUserId())
                .setCreateTime(dbAccount.getCreateTime())
                .setDataValidPeriod(dbAccount.getDataValidPeriod());
        return result;
    }

    private List<MenuVO> generateTreeMenu(List<MenuVO> allMenus, Integer parentId) {
        var menuList = CollUtil.<MenuVO>newArrayList();
        var childTreeMenuList = CollUtil.<MenuVO>newArrayList();

        for (MenuVO item : allMenus) {
            if (item.getParentId()==null || item.getParentId().equals(parentId)){
                menuList.add(item);
            } else {
                childTreeMenuList.add(item);
            }
        }
        menuList.sort(Comparator.comparingInt(MenuVO::getSort));
        childTreeMenuList.sort(Comparator.comparingInt(MenuVO::getSort));
        for (MenuVO topMenu : menuList) {
            List<MenuVO> childrenMenuList = CollUtil.newArrayList();
            for (MenuVO childMenu : childTreeMenuList) {
                if (childMenu.getParentId().equals(topMenu.getId())) {
                    childrenMenuList.add(childMenu);
                }
            }
            if (CollUtil.isNotEmpty(childrenMenuList)) {
                generateTreeMenu(allMenus,topMenu.getId());
            }
            topMenu.setChildren(childrenMenuList);
        }
        return menuList;
    }



}
