package com.xiaoyao.project.weixin.api.accessToken;

import com.xiaoyao.common.utils.redis.RedisRepeatUtils;
import com.xiaoyao.project.weixin.main.MenuManager;
import com.xiaoyao.project.weixin.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/10 13:26
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Component
@Slf4j
public class AccessTokenRedis {


    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<String,String> opsForValue;


    private static String REDIS_WEIXIN_ACCESS_TOKEN_KEY = "weixin:access_token:key";

    /**
     * TODO Redis 方案实现access_token解决
     * 先判断redis中是否存在key，如果不存在，
     * 调用微信服务器生成 access_token ，并存入redis 并设置过期事件是1小时30分钟
     * 如果存在，直接查询redis返回即可
     * @return
     */
    public String getAccessTokenRedisVal() {
        if (redisTemplate.hasKey(REDIS_WEIXIN_ACCESS_TOKEN_KEY)){
            return opsForValue.get(REDIS_WEIXIN_ACCESS_TOKEN_KEY);
        }else {
            String access_token = this.getAccessTokenVal();
            opsForValue.set(REDIS_WEIXIN_ACCESS_TOKEN_KEY,access_token,90, TimeUnit.MINUTES);
            return access_token;
        }

    }


    /**
     * TODO access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间
     * 1.access_token的有效期目前为2小时，需要定时刷新
     * 2.重复获取将导致上次获取的access_token失效
     * 3.正式号 access_token 调用次数不能超过10000次
     */
    private static String WEIXIN_GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public String getAccessTokenVal(){
        String url =WEIXIN_GET_ACCESS_TOKEN_URL.replace("APPID", MenuManager.appId).replace("APPSECRET",MenuManager.appSecret);
        JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);
        /**
         * 返回说明
         * 正常情况下，微信会返回下述 JSON 数据包给公众号：
         * {"access_token":"ACCESS_TOKEN","expires_in":7200}
         */
        log.info("线程获取access_token:" + jsonObject);

        return jsonObject.getString("access_token");
    }

}
