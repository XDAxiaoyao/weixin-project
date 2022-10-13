package com.xiaoyao.service;

import com.xiaoyao.entity.po.MeetingPub;

import java.util.List;

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


    /**
     * 我得会议列表（发布）
     * 创建sql语句查询列表数据
     */
    public List<MeetingPub> selectMeetingPubListByUid(int uid);


    /**
     * TODO 会议抢单--可抢单列表 根据抢单人id
     * @param uid 抢单人id
     * @param tid 课题类别的id
     */
    public List<MeetingPub> selectMeetingPubGrabListByUid(int uid,int tid);
}
