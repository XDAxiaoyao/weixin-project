package com.xiaoyao.project.weixin.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/8 23:42
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

/**
 *
 参数	类型	是否必须	取值范围	说明
 text	String	Y	1-128字符	直接输入文本
 */
@Data
public class InputText implements Serializable {

    //string 类型的参数 text
    private String text;
}
