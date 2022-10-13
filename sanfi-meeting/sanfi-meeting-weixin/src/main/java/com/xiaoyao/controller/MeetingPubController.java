package com.xiaoyao.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.xiaoyao.common.crypto.AESCryptoUtils;
import com.xiaoyao.common.utils.resulst.ResultCode;
import com.xiaoyao.common.utils.resulst.ResultJson;
import com.xiaoyao.entity.po.MeetingPub;
import com.xiaoyao.service.MeetingPubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    String uid = this.cryptoUid(uidCryto);
    log.info("uid",uid);
    meetingPub.setUid(Integer.parseInt(uid));

    int num = meetingPubService.insertSelective(meetingPub);
        log.info("会议发布--》{}",num);
        if (num < 1){
            return new ResultJson<>(num, ResultCode.FAIL);
        }
        return new ResultJson<>(num,ResultCode.SUCCESS);
    }


    /**
     * TODO 微信 会议-会议发布（我发布会议列表）
      */

    @GetMapping("meetingPubListByUid")
    @ResponseBody
    public Object  selectMeetingPubListByUid(@RequestParam("uidCrypto") String uidCrypto){
        String uid = this.cryptoUid(uidCrypto);
        if (uid == null){
            return new ResultJson<>(0,ResultCode.EXCEPTION);
        }
        List<MeetingPub> list = meetingPubService.selectMeetingPubListByUid(Integer.parseInt(uid));
        if (list.size() == 0){
            return new ResultJson<>(list,ResultCode.NOT_DATA);
        }
        return new ResultJson<>(list,ResultCode.SUCCESS);
    }


    /**
     * TODO 解密 uid
     * @param uidCryto
     * @return
     */
    public String cryptoUid(String uidCryto){
    String uid = null;
    try {
        //解密
        uid = AESCryptoUtils.decryptMethod(uidCryto);

    } catch (Exception e) {
        log.error("前端UID密文被修改：{}",uidCryto);
    }
    return uid;
}
}
