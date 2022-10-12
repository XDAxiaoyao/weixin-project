package com.xiaoyao.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class MeetingPub implements Serializable {
    private String id;

    private String pcode;

    private String ptime;

    private Integer tid;

    private String ptitle;

    private String pzone;

    private String remark;

    private Integer uid;

    private Short status;

    private Integer sortnum;

    private Date createDate;

    /**
     * 一对一配置
     */
    private MeetingType meetingType;


    //uid 加密后的字符串
    private String uidCryto;

}
