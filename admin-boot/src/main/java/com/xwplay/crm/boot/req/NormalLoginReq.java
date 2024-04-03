package com.xwplay.crm.boot.req;

import lombok.Data;

@Data
public class NormalLoginReq {

    private String username;
    private String password;
    private String userType;

}
