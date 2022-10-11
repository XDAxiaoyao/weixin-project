package com.xiaoyao.project.weixin.api.accessToken;

import com.xiaoyao.project.weixin.main.MenuManager;
import com.xiaoyao.project.weixin.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;

/**
 * @Program:
 * @ClassName: AccessTokenThread
 * @Date: 2022/10/10 10:48
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description: TODO
 */
@Slf4j
public class AccessTokenThread extends Thread{


    /**
     * 对外提供accessToken数据
     */
    public static String WEIXIN_ACCESS_TOKEN_VAL;
        @Override
        public void run() {
            //生成access_token
    while (true){
        WEIXIN_ACCESS_TOKEN_VAL = this.getAccessTokenVal();
        try {
            Thread.sleep(1000 * 7200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //生成access_token
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
