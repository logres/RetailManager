package com.hit.logres.phoneproject.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hit.logres.phoneproject.provider.mapper")
public class PhoneProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneProjectApplication.class, args);
    }

}
