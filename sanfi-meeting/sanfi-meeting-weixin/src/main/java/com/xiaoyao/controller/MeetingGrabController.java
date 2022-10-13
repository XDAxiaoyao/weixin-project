package com.xiaoyao.controller;

import com.xiaoyao.common.utils.resulst.ResultCode;
import com.xiaoyao.common.utils.resulst.ResultJson;
import com.xiaoyao.entity.po.MeetingPub;
import com.xiaoyao.service.MeetingPubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/12 20:56
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@RequestMapping("meetingGrab")
@Controller
@Slf4j
public class MeetingGrabController {

    @Autowired
    private MeetingPubService meetingPubService;
    /**
     * TODO 进入可抢单者列表页面
     */

     @GetMapping("meetingGrabListToPage")
    public String meetingGrabListToPage(HttpServletRequest request){
    String uid = request.getParameter("uid");
    request.setAttribute("uid",uid);
    return "meetingGrab/grabList";
    }


    @ResponseBody
    @RequestMapping("selectMeetingPubGrabListByUidAndTid")
    public Object selectMeetingPubGrabListByUidAndTid(
            @RequestParam("uid") final int uid,
            @RequestParam("tid") int tid
            ){
        List<MeetingPub> list = meetingPubService.selectMeetingPubGrabListByUid(uid, tid);
        if (list.size() == 0){
            return new ResultJson<>(list, ResultCode.NOT_DATA);
        }
        return new ResultJson<>(list,ResultCode.SUCCESS);
    }

    /**
     * TODO 会议抢单--》可抢单列表 可抢单--》 进入抢单添加页面
     */
    @GetMapping("meetingGrabAddToPage")
    public String meetingGrabAddToPage(HttpServletRequest request){
        int uid = Integer.parseInt(request.getParameter("uid"));
        String pid = request.getParameter("pid");
        request.setAttribute("uid",uid);
        request.setAttribute("pid",pid);
        return "meetingGrab/add";
    }
}
