package com.xiaoyao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.xiaoyao.mapper"})
@ServletComponentScan //servlet相关配置生效
public class SanfiMeetingWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SanfiMeetingWeixinApplication.class, args);
    }

}
