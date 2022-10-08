package com.xiaoyao.project.weixin.api.tuling.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/8 23:35
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Data
public class TuLingBean implements Serializable {
    /**
     * 请求示例如下：
     * {
     * 	"reqType":0,   输入类型:0-文本(默认)、1-图片、2-音频
     *     "perception": {
     *         "inputText": {
     *             "text": "附近的酒店"
     *         },
     *         "inputImage": {
     *             "url": "imageUrl"
     *         },
     *         "selfInfo": {
     *             "location": {
     *                 "city": "北京",
     *                 "province": "北京",
     *                 "street": "信息路"
     *             }
     *         }
     *     },
     *     "userInfo": {
     *         "apiKey": "",
     *         "userId": ""
     *     }
     * }
     */

    /**
     * 参数	类型	是否必须	取值范围	说明
     * reqType	int	N	-	输入类型:0-文本(默认)、1-图片、2-音频
     * perception	-	Y	-	输入信息
     * userInfo	-	Y	-	用户参数
     */
//输入类型:0-文本(默认)、1-图片、2-音频(只需要做文本的输入 默认给0)
    private int reqType = 0;
    //perception 里面又是一个对象，所以建一个类存放里面的值 输入的信息
    private Perception perception;
    //用户参数
    private UserInfo userInfo;
}
