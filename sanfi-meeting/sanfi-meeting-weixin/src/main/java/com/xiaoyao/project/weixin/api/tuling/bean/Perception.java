package com.xiaoyao.project.weixin.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/8 23:39
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

/**
 *
 参数	类型	是否必须	取值范围	说明
 inputText	-	N	-	文本信息
 inputImage	-	N	-	图片信息
 inputMedia	-	N	-	音频信息
 selfInfo	-	N	-	客户端属性
 */
@Data
public class Perception implements Serializable {
    //输入的文本对象
    private InputText inputText;
}
