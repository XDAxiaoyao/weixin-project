package com.xiaoyao.service;

import com.xiaoyao.entity.po.MeetingPub;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 20:28
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
public interface MeetingPubService {


    /**
     * TODO 会议添加功能
     * @param meetingPub
     * @return
     */
    int insertSelective(MeetingPub meetingPub);
}
