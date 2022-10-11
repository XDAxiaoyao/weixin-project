package com.xiaoyao.project.weixin.main;

import com.xiaoyao.project.weixin.pojo.*;
import com.xiaoyao.project.weixin.util.WeixinUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 菜单管理器类
 *
 */
public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
/***
 * 自定义菜单的创建步骤
	1、找到AppId和AppSecret。自定义菜单申请成功后，在“高级功能”-“开发模式”-“接口配置信息”的最后两项就是；
	2、根据AppId和AppSecret，以https get方式获取访问特殊接口所必须的凭证access_token；
	3、根据access_token，将json格式的菜单数据通过https post方式提交。

 */

	public final static String REAL_URL="http://chenxy.natapp1.cc/"; //个人花生壳
	//public final static String REAL_URL = "http://wxmobsa.yidatec.com/weixin/"; //正式号服务器

	public final static String appId="wxd4a114d65273354a";
	public final static String appSecret = "f8c0d3bb7eab1fdb3bc32574c0e59057";

	public static void resultMenu(){
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = MenuManager.appId;
		// 第三方用户唯一凭证密钥
		String appSecret = MenuManager.appSecret;
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(),at.getToken());

			// 判断菜单创建结果
			if (0 == result)
				log.info("菜单创建成功！");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}

	/**
	 * 组装菜单数据
	 *
	 * @return
	 */
	private static Menu getMenu() {

		//二级子菜单 与链接网页有关的
		ViewButton btn10 = new ViewButton();
		btn10.setName("会议发单");
		btn10.setType("view");
		btn10.setUrl(REAL_URL + "weiXinMenu/oauth/meetingPub");

		CommonButton btn11 = new CommonButton();
		btn11.setName("会议抢单");
		btn11.setType("click");
		btn11.setKey("11");


//-------------------------------------------------------
		//与调用接口有关的
		ViewButton btn20 = new ViewButton();
		btn20.setName("发单排行榜");
		btn20.setType("view");
		btn20.setUrl("https://www.jd.com/");

		ViewButton btn21 = new ViewButton();
		btn21.setName("抢单排行榜");
		btn21.setType("view");
		btn21.setUrl("https://www.jd.com/");

		ViewButton btn22 = new ViewButton();
		btn22.setName("每日签到");
		btn22.setType("view");
		btn22.setUrl("https://www.jd.com/");

//------------------------------------------------------------
		CommonButton btn31 = new CommonButton(); //返回图文消息
		btn31.setName("联系我们");
		btn31.setType("click");
		btn31.setKey("31");


		CommonButton btn32 = new CommonButton(); //返回图文消息
		btn32.setName("版本消息");
		btn32.setType("click");
		btn32.setKey("32");

		ViewButton btn33 = new ViewButton();
		btn33.setName("个人中心");
		btn33.setType("view");
		btn33.setUrl(REAL_URL + "weiXinMenu/oauth/user");

		//###############################################一级子菜单
		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("会议");//一栏
		mainBtn1.setSub_button(new Button[] { btn10,btn11});

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("公告板");  //二栏
		mainBtn2.setSub_button(new Button[] { btn20,btn21,btn22});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("系统消息");// btn31, btn32, btn33,
		mainBtn3.setSub_button(new Button[] {btn33,btn31,btn32});

		/**
		 * 这是公众号目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2,mainBtn3});

		return menu;
	}
}
