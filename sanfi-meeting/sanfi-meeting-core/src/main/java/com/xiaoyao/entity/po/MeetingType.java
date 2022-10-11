package com.xiaoyao.entity.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class MeetingType implements Serializable {
    private Integer id;

    private String name;

    private String remark;

    private Short status;

    private Integer sortnum;

    private Date createDate;

}
