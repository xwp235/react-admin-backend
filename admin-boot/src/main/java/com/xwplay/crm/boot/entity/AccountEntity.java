package com.xwplay.crm.boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xwplay.crm.common.handler.types.TstzRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value="mast_account",schema = "public")
public class AccountEntity {
    @TableId
    private Long id;
    private Long maUserId;
    private String maUsername;

    private ZonedDateTime createTime;
    private ZonedDateTime updateTime;
    private Long updateUserId;
    private TstzRange dataValidPeriod;
//    private String nickname;
//    private String email;
//    private String mobile;
//    private String telephone;
//    private String avatar;
//    private Integer state;
//    private Boolean disabled;
//    private Boolean accountLocked;
//    private Integer loginCount;
//    private LocalDateTime lastLoginTime;
//    private Boolean activated;
//    private Long ranking;

}
