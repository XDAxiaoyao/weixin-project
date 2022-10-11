package com.xiaoyao.project.oauth;

import com.xiaoyao.entity.po.WeiUser;
import com.xiaoyao.project.weixin.main.MenuManager;
import com.xiaoyao.project.weixin.util.WeixinUtil;
import com.xiaoyao.service.WeiUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/9 20:48
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description: TODO 微信公众号 oauth2.0
 */

@RequestMapping("oauth")
@Controller
@Slf4j
public class WeiXinOauth {


    /**
     * 引导用户进入授权页面同意授权，获取code
     * 参考文档地址：
     * https://developers.weixin.qq.com/doc/offiaccount/OA_Web_Apps/Wechat_webpage_authorization.html
     *
     * 请求路径：oauth/weixin
     */

    @RequestMapping("weixin")
    public void oauth(HttpServletResponse response) throws IOException {

        //uri表示用户同意授权以后跳转的uri地址
        String redirect_uri = "http://chenxy.natapp1.cc/oauth/invoke";
        //redirect_uri是授权后重定向的回调链接地址， 请使用 urlEncode 对链接进行处理
        try {

            redirect_uri = URLEncoder.encode(redirect_uri,"UTF-8");
            log.info("--invokeurl--->{}",redirect_uri);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
        "appid=" + MenuManager.appId +
        "&redirect_uri=" + redirect_uri +
        "&response_type=code" +
//scope	是	应用授权作用域，
// snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
// snsapi_userinfo （弹出授权页面，可通过 openid 拿到昵称、性别、所在地。
// 并且， 即使在未关注的情况下，只要用户授权，也能获取其信息 ）
        "&scope=snsapi_userinfo" +
//state	否	重定向后会带上 state 参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节 值随意
        "&state=java2203" +
                "&forcePopup=true" +
                "#wechat_redirect";

        response.sendRedirect(url);
}

/**
 * http://chenxy.natapp1.cc/oauth/weixin
 * 用户同意授权后
 * 如果用户同意授权，页面将跳转至 oauth/invoke/?code=CODE&state=STATE。
 * code说明：
 * code作为换取access_token的票据，每次用户授权带上的 code 将不一样，code只能使用一次，5分钟未被使用自动过期。
 */

@RequestMapping("invoke")
public Object invoke(HttpServletRequest request){
    log.info("6666");
//获取授权码
    String code = request.getParameter("code");
    String state = request.getParameter("state");

    //通过code 换取网页授权access_token (微信 认证服务器地址)
    //获取code后，请求以下链接获取access_token
    String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
            "appid=" +MenuManager.appId +
            "&secret=" + MenuManager.appSecret +
            "&code=" + code +
            "&grant_type=authorization_code";

    //JAVA原生 UrlConnection apache:HttpClient 阿里：OKHttp Spring:RestTemplate. 自定义WeixinUtil
    JSONObject jsonObject = WeixinUtil.httpRequest(url, "GET", null);

    /**
     * 返回说明
     *
     * 正确时返回的 JSON 数据包如下：
     *
     * {
     *   "access_token":"ACCESS_TOKEN",
     *   "expires_in":7200,
     *   "refresh_token":"REFRESH_TOKEN",
     *   "openid":"OPENID",
     *   "scope":"SCOPE"
     * }
     */
    String access_token = jsonObject.getString("access_token");
    String openid = jsonObject.getString("openid");

    log.info("{}---->{}",access_token,openid);

    //第四步：拉取用户信息(需 scope 为 snsapi_userinfo) (微信 资源服务器地址)
    String url1 = "https://api.weixin.qq.com/sns/userinfo?" +
            "access_token=" + access_token +
            "&openid=" + openid +
            "&lang=zh_CN";
    //获取值之后发送get请求
    JSONObject jsonObject1 = WeixinUtil.httpRequest(url1, "GET", null);
request.setAttribute("jsonObject1",jsonObject1);
    return "oauth";

}
}
