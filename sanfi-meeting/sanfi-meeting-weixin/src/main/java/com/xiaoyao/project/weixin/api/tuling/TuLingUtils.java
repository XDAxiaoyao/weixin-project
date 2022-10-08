package com.xiaoyao.project.weixin.api.tuling;

import com.xiaoyao.project.weixin.api.tuling.bean.InputText;
import com.xiaoyao.project.weixin.api.tuling.bean.Perception;
import com.xiaoyao.project.weixin.api.tuling.bean.TuLingBean;
import com.xiaoyao.project.weixin.api.tuling.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Program:
 * @ClassName: TulingUtils
 * @Date: 2022/10/8 23:20
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description: TODO 图灵智能聊天机器人
 * https://www.kancloud.cn/turing/www-tuling123-com/718227 官方API文档
 */

@Component
@Slf4j
public class TuLingUtils {
    @Autowired
    private RestTemplate restTemplate;

    private static String TULING_API_POST_URL = "http://openapi.turingapi.com/openapi/api/v2";
    // public void tuLingResult(){
    public  void test(){
        TuLingBean tuLingBean = new TuLingBean();
        tuLingBean.setReqType(0);
        Perception perception = new Perception();
        InputText inputText = new InputText();
        inputText.setText("南京的天气怎么样");
        perception.setInputText(inputText);
        tuLingBean.setPerception(perception);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("潇遙");
        userInfo.setApiKey("8d98aaff96ad4ea1810a41fbbc700c09");
        tuLingBean.setUserInfo(userInfo);

        //JAVA bEAN转成json对象
        JSONObject jsonObject = JSONObject.fromObject(tuLingBean);
        System.out.println(jsonObject);
        /**
         * param1 请求url地址
         * param2 object var2 请求json对象
         * param3 方法返回值类型
         * param4 参数传递
         */

        //发送请求并响应
        String result = restTemplate.postForObject(TULING_API_POST_URL, jsonObject, String.class);
        /**
         * {
         * 	"intent": {
         * 		"appKey": "platform.weather",
         * 		"code": 0,
         * 		"operateState": 1100,
         * 		"parameters": {
         * 			"date": "",
         * 			"city": "南京"
         *                }* 	},
         * 	"results": [{
         * 		"groupType": 0,
         * 		"resultType": "text",
         * 		"values": {
         * 			"text": "南京:周日 10月09日,阴转多云 西北风,最低气温8度，最高气温19度。"
         * 		}
         * 	}]
         * }
         */
        //将字符串转换成jsonobject对象
        JSONObject jsonObject1 = JSONObject.fromObject(result);
        //得到的results是一个数组 返回值就是一个json数组
        JSONArray jsonArray = (JSONArray) jsonObject1.get("results");
        //通过下标索引得到对象 就一个 就是下标为0
        JSONObject jsonObject2 = (JSONObject) jsonArray.get(0);
        //得到的value是对象 所以返回值是对象
        JSONObject jsonObject3 = (JSONObject) jsonObject2.get("values");
        //确定text得到的值是string字符串 所以调用getstring方法获取值 避免了类型转换
        String text =  jsonObject3.getString("text");
        System.out.println(text);
    }


}
