package com.xiaoyao.controller;

import com.xiaoyao.common.crypto.AESCryptoUtils;
import com.xiaoyao.common.utils.resulst.ResultCode;
import com.xiaoyao.common.utils.resulst.ResultJson;
import com.xiaoyao.entity.po.MeetingPub;
import com.xiaoyao.service.MeetingPubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 20:33
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Controller
@Slf4j
@RequestMapping("meetingPub")
public class MeetingPubController {
    @Autowired
    private MeetingPubService meetingPubService;

    /**
     * 页面跳转
     */
    @GetMapping("meetingPubAddPage")
public String meetingPubAddPage(HttpServletRequest request){
        String uidCryto = request.getParameter("uidCryto");
        request.setAttribute("uidCryto",uidCryto);
        return "meetingPub/add";
}
    /**
 * TODO 微信 会议-会议发布（增加）
 */

@ResponseBody
@PostMapping("addMeetingPub")
    public Object addMeetingPub(MeetingPub meetingPub){
    //加密后的uid
    String uidCryto = meetingPub.getUidCryto();
    String uid = null;
    try {
        //解密
        uid = AESCryptoUtils.decryptMethod(uidCryto);
    } catch (Exception e) {
        log.error("前端UID密文被修改：{}",uidCryto);
       return  new ResultJson<>(0,ResultCode.EXCEPTION);
    }
    meetingPub.setUid(Integer.parseInt(uid));

    int num = meetingPubService.insertSelective(meetingPub);
        log.info("会议发布--》{}",num);
        if (num < 1){
            return new ResultJson<>(num, ResultCode.FAIL);
        }
        return new ResultJson<>(num,ResultCode.SUCCESS);
    }


}
