package com.xiaoyao.entity.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/9/29 18:52
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */


@ExcelTarget(value = "users")
@Data
public class User implements Serializable {
    @Excel(name = "序号",orderNum = "0")
    private int id;
    @Excel(name = "姓名",orderNum = "1")
    private String name;
    @Excel(name = "邮箱",orderNum = "2")
    private String email;
    @Excel(name = "联系方式",orderNum = "3")
    private String telephone;
    @Excel(name = "省份",orderNum = "4")
    private String province;
    @Excel(name = "城市",orderNum = "5")
    private String city;
    @Excel(name = "区域",orderNum = "6")
    private String zone;
    @Excel(name = "状态",orderNum = "7")
    private int status;

}
