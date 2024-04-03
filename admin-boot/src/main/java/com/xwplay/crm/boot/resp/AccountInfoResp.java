package com.xwplay.crm.boot.resp;

import com.xwplay.crm.common.handler.types.TstzRange;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
public class AccountInfoResp {

    private Long userId;
    private String username;
    private ZonedDateTime createTime;
    private TstzRange dataValidPeriod;

}
