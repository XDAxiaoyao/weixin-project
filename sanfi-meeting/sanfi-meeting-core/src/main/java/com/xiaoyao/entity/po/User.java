package com.xiaoyao.entity.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class User implements Serializable {
    @Excel(name = "序号",orderNum = "0")
    private Integer id;
    @Excel(name = "姓名",orderNum = "1")
    private String name;
    @Excel(name = "邮箱",orderNum = "2")
    private String email;
    @Excel(name = "联系方式",orderNum = "5")
    private String telephone;
    @Excel(name = "省份",orderNum = "4")
    private String province;
    @Excel(name = "城市",orderNum = "3")
    private String city;
    @Excel(name = "区域",orderNum = "6")
    private String zone;
    @Excel(name = "权限",orderNum = "7" ,replace = {"发单组_1","抢单组_2"})
    private Integer rid;

    private Integer wid;

    private Short status = 0;

    private Date createDate = new Date();


}
