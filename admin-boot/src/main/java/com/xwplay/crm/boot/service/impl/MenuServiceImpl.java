package com.xwplay.crm.boot.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xwplay.crm.boot.entity.MenuEntity;
import com.xwplay.crm.boot.mapper.MenuMapper;
import com.xwplay.crm.boot.service.MenuService;
import com.xwplay.crm.common.constants.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    @Override
    public List<MenuEntity> getAllValidMenus() {
        var allMenuQ = Wrappers.<MenuEntity>lambdaQuery();
        allMenuQ.eq(MenuEntity::getSmState, AppConstants.MENU_STATE_ON)
                .select(MenuEntity::getSmId,
                        MenuEntity::getSmParentId,
                        MenuEntity::getSmType,
                        MenuEntity::getSmPath,
                        MenuEntity::getSmIcon,
                        MenuEntity::getSmName,
                        MenuEntity::getSmLevel,
                        MenuEntity::getSmLevelChain,
                        MenuEntity::getSmCode,
                        MenuEntity::getSmSort)
                .orderByAsc(MenuEntity::getSmId);
        return menuMapper.selectList(allMenuQ);
    }

}
