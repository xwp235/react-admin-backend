package com.xwplay.crm.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {
                "com.xwplay.crm.common",
                "com.xwplay.crm.boot"
        }
)
public class AdminBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminBootApplication.class, args);
    }

}
