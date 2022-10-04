package com.xiaoyao.project.weixin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/8/29 17:50
 * @Auther: 潇遙
 * @Project_Name: 2203班
 * @Description:
 */

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
