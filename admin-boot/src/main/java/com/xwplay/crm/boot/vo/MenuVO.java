package com.xwplay.crm.boot.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class MenuVO {

    private Integer id;
    private Integer parentId;
    private String path;
    private String icon;
    private String menuName;
    private Integer menuType;
    private Integer sort;
    private Integer level;
    private String levelChain;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<MenuVO> children;

}
