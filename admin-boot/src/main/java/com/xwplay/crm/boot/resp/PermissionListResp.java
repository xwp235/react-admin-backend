package com.xwplay.crm.boot.resp;

import com.xwplay.crm.boot.vo.MenuVO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PermissionListResp {

    private List<String> buttonList;
    private List<MenuVO> menuList;

}
