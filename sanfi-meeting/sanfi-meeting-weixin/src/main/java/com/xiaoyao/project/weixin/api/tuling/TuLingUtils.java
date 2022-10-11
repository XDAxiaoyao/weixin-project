package com.xiaoyao.project.weixin.api.tuling;

import com.xiaoyao.project.weixin.api.tuling.bean.InputText;
import com.xiaoyao.project.weixin.api.tuling.bean.Perception;
import com.xiaoyao.project.weixin.api.tuling.bean.TuLingBean;
import com.xiaoyao.project.weixin.api.tuling.bean.UserInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/9 00:54
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
@Component
public class TuLingUtils {

    @Autowired
    private RestTemplate restTemplate;
    //将url设置为动态的常量url
    private static String TULING_API_POST_URL = "http://openapi.turingapi.com/openapi/api/v2";

    /**
     * 1.请求封装
     */
    public JSONObject requestJsonObject(String text,String apiKey){
        //引用tuLingBean对象
        TuLingBean tuLingBean = new TuLingBean();
        //ReqType 默认为0  文本格式
        tuLingBean.setReqType(0);
        Perception perception = new Perception();
        InputText inputText = new InputText();
        inputText.setText(text);
        perception.setInputText(inputText);
        tuLingBean.setPerception(perception);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("潇遙");
        userInfo.setApiKey(apiKey);
        tuLingBean.setUserInfo(userInfo);

        //JAVA bEAN转成json对象
        JSONObject jsonObject = JSONObject.fromObject(tuLingBean);
        return jsonObject;
    }


    /**
     * 2.发送请求，并购返回结果
     */

    public String requestResult(JSONObject jsonObject){

        /**
         * param1 请求url地址
         * param2 object var2 请求json对象
         * param3 方法返回值类型
         * param4 参数传递
         */
        //发送请求并响应
        String result = restTemplate.postForObject(TULING_API_POST_URL, jsonObject, String.class);
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

        return text;
    }

public String sendMessage(String text,String apiKey){
    //1.封装请求的对象信息
    JSONObject jsonObject = this.requestJsonObject(text,APIKEYS_ALL[KEY_INDEX]);
    //2.发送请求并得到结果
    String result = this.requestResult(jsonObject);
    return result;
}
    /**
     * 收集机器人API KEY 存储介质：数据库/redis/内存
     */
    private static String[] APIKEYS_ALL={
            "9c8f002686014beb965d9715f41f1f7c",
            "9c8f002686014beb965d9715f41f1f7c",
            "9c8f002686014beb965d9715f41f1f7c"
    };
    /**
     * 当前机器人下标索引KEY
     */
    private static int KEY_INDEX = 0;
//判断整个机器人是否可用 开关flag true机器人智能回复功能开启 false 关闭
    public static boolean TULING_KEY_FLAG = true;



    /**
     * 对外访问机器人方法
     * @param text
     * @param
     * @return
     */
    public String sendText(String text){
        if (TULING_KEY_FLAG == false){
            return "今天我累了，明天再找我聊吧";
        }
        String result = this.sendMessage(text, APIKEYS_ALL[KEY_INDEX]);
        if ("请求次数超限制!".equals(result)){
            //调用下一个机器人
            KEY_INDEX++;

            if (KEY_INDEX >= APIKEYS_ALL.length){
                KEY_INDEX = 0;
                return "今天我累了，明天再找我聊吧。";
            }
            return this.sendText(text);
        }
        return result;



    }


















    // public void tuLingResult(){
    public  void test(){
        //引用tuLingBean对象
        TuLingBean tuLingBean = new TuLingBean();
        //ReqType 默认为0  文本格式
        tuLingBean.setReqType(0);
        Perception perception = new Perception();
        InputText inputText = new InputText();
        inputText.setText("南京的天气怎么样");
        perception.setInputText(inputText);
        tuLingBean.setPerception(perception);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("潇遙");
        userInfo.setApiKey("fb5a78bb2e79482d8075acd90b13231d");
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
