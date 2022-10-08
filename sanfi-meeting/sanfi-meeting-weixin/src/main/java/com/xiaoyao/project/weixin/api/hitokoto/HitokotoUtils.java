package com.xiaoyao.project.weixin.api.hitokoto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/8 21:21
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
@Component
@Slf4j
public class HitokotoUtils {
    /**
     *   一言服务器
     *   https://developer.hitokoto.cn/ 官网
     */

   private static String HITOKOTO_URL="https://v1.hitokoto.cn/?encode=text";
        @Autowired
        private RestTemplate restTemplate;

    /**
     * TODO 一言服务器api调用
     * @return
     */
        public String getHitokotoResult(){
          return   restTemplate.getForObject(HITOKOTO_URL,String.class);
        }
}
