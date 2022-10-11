package com.xiaoyao.controller;

import com.xiaoyao.common.utils.resulst.ResultCode;
import com.xiaoyao.common.utils.resulst.ResultJson;
import com.xiaoyao.entity.po.MeetingType;
import com.xiaoyao.service.MeetingTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 19:22
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
@Controller
@Slf4j
@RequestMapping("meetingType")
public class MeetingTypeController {
    @Autowired
    private MeetingTypeService meetingTypeService;

    /**
     *TODO 加载有效的课题类别
     */
@ResponseBody
@GetMapping("loadMeetingType")
    public Object loadMeetingType(){
        List<MeetingType> list = meetingTypeService.selectMeetingTypeStatusAndSortNumAsc();
        if (list.size() == 0){
            return new ResultJson<>(null, ResultCode.NOT_DATA);
        }
        return new ResultJson<>(list, ResultCode.SUCCESS);
    }
}
