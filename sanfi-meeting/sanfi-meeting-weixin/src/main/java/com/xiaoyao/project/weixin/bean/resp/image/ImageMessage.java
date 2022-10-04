package com.xiaoyao.project.weixin.bean.resp.image;

import lombok.Data;

import java.io.Serializable;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/9/28 19:27
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Data
public class ImageMessage implements Serializable {

    // 接收方帐号（收到的OpenID）
    private String ToUserName;
    // 开发者微信号
    private String FromUserName;
    // 消息创建时间 （整型）
    private long CreateTime;
    // 消息类型（text/music/news）
    private String MsgType;

    private ImageBean  Image;
}
