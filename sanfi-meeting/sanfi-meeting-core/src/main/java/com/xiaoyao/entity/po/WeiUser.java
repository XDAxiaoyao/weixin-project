package com.xiaoyao.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class WeiUser implements Serializable {
    private Integer id;

    private String openid;

    private String nickname;

    private Short sex;

    private String city;

    private String province;

    private String country;

    private String headimgurl;

    private Date createDate;

    //用于json对象转换的属性
    private String[] privilege;

    private String language;
}
