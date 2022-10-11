package com.xiaoyao.project.weixin.api.messageCustom.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 01:10
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
@Data
public class CustomBean implements Serializable {
    private String touser;
    private String msgtype = "text";
    private TextBean text;
}
