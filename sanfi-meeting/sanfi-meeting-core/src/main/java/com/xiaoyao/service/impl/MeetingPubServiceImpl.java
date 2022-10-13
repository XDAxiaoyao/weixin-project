package com.xiaoyao.service.impl;

import com.xiaoyao.entity.po.MeetingPub;
import com.xiaoyao.mapper.MeetingPubMapper;
import com.xiaoyao.service.MeetingPubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 20:31
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Service
@Slf4j
public class MeetingPubServiceImpl implements MeetingPubService {

    @Autowired
    private MeetingPubMapper meetingPubMapper;

    @Override
    public int insertSelective(MeetingPub meetingPub) {
meetingPub.setId(UUID.randomUUID().toString());
meetingPub.setPcode(this.meetingPcodeGenerator(meetingPub.getPtime()));
//会议编号（生成规则）
        //meetingPub.setPcode();

        meetingPub.setCreateDate(new Date());
        meetingPub.setSortnum(1);
        meetingPub.setStatus((short) 1);
        return meetingPubMapper.insertSelective(meetingPub);
    }

    @Override
    public List<MeetingPub> selectMeetingPubListByUid(int uid) {
        return meetingPubMapper.selectMeetingPubListByUid(uid);
    }

    @Override
    public List<MeetingPub> selectMeetingPubGrabListByUid(int uid, int tid) {
        return meetingPubMapper.selectMeetingPubGrabListByUid(uid,tid);
    }

    /**
     * TODO 我得会议列表（发布）
     */



    /**
     * TODO 会议编号 生成规则
     * 根据会议召开时间 生成规则 2022-10-12 12：30
     * 判断当天是否存在召开会议，如果不存在 20221012 001
     * 如果存在 20221012 002 .。。
     *
     * 代码实现业务逻辑
     * 先查看当天最大的会议编号
     * select max(pcode) from meeting_pub where LEFT(pcode ,8) = '20221011'
     * 如果返回值是null +001
     * 如果不为null 改返回值得到+1
     */
    public  String meetingPcodeGenerator(String ptime){
        //2022-10-11T16:21  截取20221011
        String time = ptime.substring(0,9).replaceAll("-","");  //2022-10-11
        String code = meetingPubMapper.selectMeetingPubMaxPcodeByTime(time);
        if (code == null){
            return time + "001";
        }else {
            long num = (Long.parseLong(code) )+ 1;
            return num + "";
        }

    }

    public static void main(String[] args) {
        String ptime = "2022-10-11T16:21";
        String str = ptime.substring(0,10).replaceAll("-","");
        System.out.println(str);
    }

}
