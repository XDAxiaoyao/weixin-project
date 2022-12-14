package com.xiaoyao.project.weixin.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import java.io.IOException;


/**

 */
public class HttpClientHelper {



	public String callTrustHttps1(String url, String request_body) {
		try {
		    HttpClient client = new HttpClient();
		  //  Protocol myhttps = new Protocol("https", new MySSLProtocolSocketFactory(), 443);
		   // Protocol.registerProtocol("https", myhttps);
		    PostMethod post = new PostMethod(url);
		    if (request_body != null) {
		    	//post.setRequestBody(request_body);
		    	RequestEntity requestEntity = new StringRequestEntity(request_body, "text/xml", "UTF-8");
		    	post.setRequestEntity(requestEntity);
		    }
		    post.getParams().setContentCharset("UTF-8");
		    //发送http请求
		    String respStr = "";
		    try {
		        client.executeMethod(post);
		        respStr = post.getResponseBodyAsString();
		    } catch (HttpException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		    return respStr;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		new HttpClientHelper().Test3();
	}


	public static void Test3(){
		String busiCode="?pageMark=3&paymentContent=busiCode%3D13942877747&queryPageinfo=1&netType=181&IEVersionFlag=0&ComputID=10&PlatFlag=0&areaCodeTmp=3400&areaNameTmp=%B4%F3%C1%AC&dse_menuid=PM002&IN_PAYITEMCODE=PJ110011011000001960&isShortpay=&maskFlag=0&isP3bank=0";
		String url="https://fee.icbc.com.cn/servlet/ICBCINBSEstablishSessionServlet"+busiCode;


		String html=new HttpClientHelper().callTrustHttps1(url, null);
		System.out.println(html);
		// https://fee.icbc.com.cn/icbc/newperbank/onlinePayment/selfhelp/selfhelp_proddetail.jsp

	}
}
