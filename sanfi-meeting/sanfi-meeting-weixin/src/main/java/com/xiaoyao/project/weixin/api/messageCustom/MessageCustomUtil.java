package com.xiaoyao.project.weixin.api.messageCustom;

import com.xiaoyao.project.weixin.api.accessToken.AccessTokenRedis;
import com.xiaoyao.project.weixin.api.messageCustom.bean.CustomBean;
import com.xiaoyao.project.weixin.api.messageCustom.bean.TextBean;
import com.xiaoyao.project.weixin.util.WeixinUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 01:06
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 *
 * 客服接口 - 发消息
 * 接口调用请求说明
 *
 * http请求方式: POST https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
 */

@Component
@Slf4j
public class MessageCustomUtil {
    //redis 获取 access_token
    @Autowired
    private AccessTokenRedis accessTokenRedis;
private static String WEIXIN_POST_MESSAGE_CUSTOM_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

public void sendMessageText(String openid,String content){
        //封装请求对象，转成json
    CustomBean customBean = new CustomBean();
    customBean.setMsgtype("text");
    customBean.setTouser(openid);
    TextBean textBean = new TextBean();
    textBean.setContent(content);
    customBean.setText(textBean);
    //对象转json
    JSONObject jsonObject = JSONObject.fromObject(customBean);
    //发送post请求
    String url = WEIXIN_POST_MESSAGE_CUSTOM_URL.replace("ACCESS_TOKEN",accessTokenRedis.getAccessTokenRedisVal());
    WeixinUtil.httpRequest(url,"POST",jsonObject.toString());
}
}
