package com.wangfuzeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wangfuzeng.*.dao")
public class WorkSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkSysApplication.class, args);
    }

}
