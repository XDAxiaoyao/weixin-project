package com.xiaoyao.project.weixin.service;

import com.xiaoyao.entity.po.User;
import com.xiaoyao.entity.po.WeiUser;
import com.xiaoyao.project.weixin.api.hitokoto.HitokotoUtils;
import com.xiaoyao.project.weixin.api.tuling.TuLingUtils;
import com.xiaoyao.project.weixin.bean.resp.Article;
import com.xiaoyao.project.weixin.bean.resp.NewsMessage;
import com.xiaoyao.project.weixin.bean.resp.TextMessage;
import com.xiaoyao.project.weixin.bean.resp.image.ImageBean;
import com.xiaoyao.project.weixin.bean.resp.image.ImageMessage;
import com.xiaoyao.project.weixin.main.MenuManager;
import com.xiaoyao.project.weixin.util.MessageUtil;
import com.xiaoyao.service.UserService;
import com.xiaoyao.service.WeiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CoreService {

/**
 * 一言服务器的第三方 api调用
 */
@Autowired
private HitokotoUtils hitokotoUtils;
	/**
	 * 图灵智能聊天机器人 api调用
	 */
	@Autowired
private TuLingUtils tuLingUtils;

	/**
	 * 微信个人信息业务
	 */
	@Autowired
	private WeiUserService weiUserService;


	@Autowired
	private UserService userService;
	/**
	 * 处理微信发来的请求
	 *
	 * @param request
	 * @return
	 */
	public String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id） 下面三行代码是： 从HashMap中取出消息中的字段；
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息 组装要返回的文本消息对象；
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(System.currentTimeMillis());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			// textMessage.setContent("欢迎访问<a
			// href=\"http://www.baidu.com/index.php?tn=site888_pg\">百度</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			/**
			 * 演示了如何接收微信发送的各类型的消息，根据MsgType判断属于哪种类型的消息；
			 */

			// 接收用户发送的文本消息内容
			String content = requestMap.get("Content");

			// 创建图文消息
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.setToUserName(fromUserName);
			newsMessage.setFromUserName(toUserName);
			newsMessage.setCreateTime(System.currentTimeMillis());
			newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
			newsMessage.setFuncFlag(0);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

				if(content.equals("李冰冰")){
					ImageMessage imageMessage=new ImageMessage();
					imageMessage.setFromUserName(toUserName);
					imageMessage.setToUserName(fromUserName);
					imageMessage.setCreateTime(System.currentTimeMillis());
					imageMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
					ImageBean imageBean=new ImageBean();
					imageBean.setMediaId("QRq7vdObjfpJzlPaPI_32NUcVB3t6uDNBPrtGQcBJxEkKf4wvnoiOebIdQGf6YMm");
					imageMessage.setImage(imageBean);


					String respResult=MessageUtil.imageMessageToXml(imageMessage);
					return  respResult;
				}else {

					// respContent = "您发送的是文本消息！";
					// respContent = hitokotoUtils.getHitokotoResult();
					respContent = tuLingUtils.sendText(content);
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {

					String MediaId=requestMap.get("MediaId");
					String PicUrl=requestMap.get("PicUrl");
					log.info(MediaId+"\t"+PicUrl);
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

					respContent = "欢迎关注微信公众号";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}

				//地理位置事件
				else if (eventType.equals("LOCATION")){
					String latitude = requestMap.get("Latitude");
					String longitude = requestMap.get("Longitude");
					String precision = requestMap.get("Precision");
					log.info("当前地理位置：{}，{}，{}",latitude,longitude,precision);
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					if (eventKey.equals("11")) {
						List<Article> articleList = new ArrayList<Article>();

						// 单图文消息
						Article article = new Article();
						//判断当前微信用户是否进行登录（绑定）fromUserName = openid
						WeiUser weiUser = weiUserService.selectWeiUserByOpenId(fromUserName);
						if (weiUser == null){
						respContent = "您无法操作，请点击菜单个人中心，进行登录";
						}
						//根据openid得到wei_user 主键id 判断user对象是否存在
						User user = userService.selectUserByWid(weiUser.getId());
						if (user == null){
							article.setTitle("您还未登录，暂无法操作");
							article.setDescription("会议抢单需要先进行个人登录（绑定）");
							article.setPicUrl(
									"https://t7.baidu.com/it/u=1732966997,2981886582&fm=193&f=GIF");
							article.setUrl(MenuManager.REAL_URL + "/user/userLoginToPage?openid=" + fromUserName + "&wid=" + weiUser.getId());
						}else {
							if (user.getRid() == 2){
								article.setTitle("您好，"+user.getName()+",您是抢单组，进入抢单功能的实现");
								article.setDescription("您是抢单组，可点击页面进行抢单操作");
								article.setPicUrl(
										"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.php.cn%2Fupload%2Fwebpage%2F000%2F000%2F006%2F587dcd8dd1230997.jpg&refer=http%3A%2F%2Fimg.php.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1668171171&t=5644cf5ef46a9eebf1196acd7731996d");
								article.setUrl(MenuManager.REAL_URL + "/meetingGrab/meetingGrabListToPage?uid=" + user.getId());
							}else {
								article.setTitle("您好，"+user.getName()+",您是发单组，暂无法操作会议抢单功能");
								article.setDescription("您是发单组，有发单权限，发单相关教程和描述等信息");
								article.setPicUrl(
										"https://t7.baidu.com/it/u=2269795974,2286069410&fm=193&f=GIF");
								article.setUrl(MenuManager.REAL_URL + "/user/unauth");
							}
						}




						articleList.add(article);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;

					}else if (eventKey.equals("1")){
						respContent = hitokotoUtils.getHitokotoResult();
					}else if (eventKey.equals("2")){
						ImageMessage imageMessage=new ImageMessage();
						imageMessage.setFromUserName(toUserName);
						imageMessage.setToUserName(fromUserName);
						imageMessage.setCreateTime(System.currentTimeMillis());
						imageMessage.setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
						ImageBean imageBean=new ImageBean();
						imageBean.setMediaId("QRq7vdObjfpJzlPaPI_32NUcVB3t6uDNBPrtGQcBJxEkKf4wvnoiOebIdQGf6YMm");
						imageMessage.setImage(imageBean);
						String respResult=MessageUtil.imageMessageToXml(imageMessage);
						return  respResult;
					}
					else if (eventKey.equals("70")) {

						List<Article> articleList = new ArrayList<Article>();

						// 单图文消息
						Article article = new Article();
						article.setTitle("标题");
						article.setDescription("描述内容");
						article.setPicUrl(
								"图片");
						article.setUrl("跳转连接");


						articleList.add(article);
						// 设置图文消息个数
						newsMessage.setArticleCount(articleList.size());
						// 设置图文消息
						newsMessage.setArticles(articleList);
						// 将图文消息对象转换为XML字符串
						respMessage = MessageUtil.newsMessageToXml(newsMessage);
						return respMessage;
					}

				}

			}

			// 组装要返回的文本消息对象；
			textMessage.setContent(respContent);
			// 调用消息工具类MessageUtil将要返回的文本消息对象TextMessage转化成xml格式的字符串；
			respMessage = MessageUtil.textMessageToXml(textMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}

}
