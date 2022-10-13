package com.xiaoyao.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class MeetingGrab implements Serializable {
    private String id;

    private String pid;

    private String remark;

    private Integer uid;

    private Date grabDate;

    private Date createDate;

    private Short grabStatus;

    private Short status;


}
