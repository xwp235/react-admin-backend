package com.xwplay.crm.boot.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.xwplay.crm.common.handler.types.MenuName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="sys_menu",schema = "public")
@KeySequence(value="sys_menu_id_seq",dbType = DbType.POSTGRE_SQL)
public class MenuEntity {

    @TableId(type = IdType.INPUT)
    private Integer id;
    private Integer smId;
    private Integer smParentId;
    private Integer smType;
    private String smPath;
    private Integer smState;
    private String smIcon;
    private MenuName smName;
    private String smCode;
    private String smLevelChain;
    private Integer smLevel;
    private Integer smSort;
    private String smRemark;
    private ZonedDateTime createTime;
    private ZonedDateTime updateTime;
    private String createAccountId;
    private String updateAccountId;
    private Boolean smEnableEdit;
    private Boolean smEnableDelete;

}
