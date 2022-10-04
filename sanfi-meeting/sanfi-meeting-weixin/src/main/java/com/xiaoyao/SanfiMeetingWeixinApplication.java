package com.xiaoyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.xiaoyao.mapper"})
public class SanfiMeetingWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanfiMeetingWeixinApplication.class, args);
    }

}
