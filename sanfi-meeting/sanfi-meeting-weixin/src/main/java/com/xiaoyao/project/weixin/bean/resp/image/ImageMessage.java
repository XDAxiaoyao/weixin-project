package com.xiaoyao.project.weixin.bean.resp.image;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ImageMessage
 * @Description TODO
 * @Author guoweixin
 * @Date 2022/9/28
 * @Version 1.0
 */
@Data
public class ImageMessage  implements Serializable {
   /*
   <xml>
      <ToUserName><![CDATA[toUser]]></ToUserName>
      <FromUserName><![CDATA[fromUser]]></FromUserName>
      <CreateTime>12345678</CreateTime>
      <MsgType><![CDATA[image]]></MsgType>
      <Image>
        <MediaId><![CDATA[media_id]]></MediaId>
      </Image>
    </xml>



    */


    // 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/music/news）
    private String MsgType;

    private ImageBean Image;
}
