package com.xwplay.crm.common.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccountBO {

    private Long userId;
    private String username;
    private String device;

}
