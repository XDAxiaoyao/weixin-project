package com.xiaoyao.project.weixin.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/8 23:47
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

/**
 *
 参数	类型	是否必须	取值范围	说明
 apiKey	String	Y	32位	机器人标识
 userId	String	Y	长度小于等于32位	用户唯一标识
 groupId	String	N	长度小于等于64位	群聊唯一标识
 userIdName	String	N	长度小于等于64位	群内用户昵称
 */
@Data
public class UserInfo implements Serializable {
    //userinfo中有两个参数
    //机器人标识
    private String apiKey;
    //用户唯一标识
    private String userId;
}
