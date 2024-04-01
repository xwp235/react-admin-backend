package com.xwplay.crm.boot.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AuthMenuResp {

    private Integer id;
    private Integer parentId;
    private String path;
    private String icon;
    private String menuName;
    private Integer menuType;
    private Integer sort;
    private Integer level;
    private String levelChain;
    private List<AuthMenuResp> children;

}
