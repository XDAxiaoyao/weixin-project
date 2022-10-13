package com.xiaoyao;

import com.xiaoyao.entity.po.MeetingPub;
import com.xiaoyao.project.weixin.api.tuling.TuLingUtilsTest;
import com.xiaoyao.service.MeetingPubService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SanfiMeetingWeixinApplicationTests {
    /**
     * 图灵机器人的测试
     */
    @Autowired
    private MeetingPubService meetingPubService;

    @Test
    void contextLoads() {
        int uid = 53;
        int tid = -1;
        List<MeetingPub> meetingPubs = meetingPubService.selectMeetingPubGrabListByUid(uid, tid);
        System.out.println(meetingPubs.size());
        for (MeetingPub meetingPub : meetingPubs) {
            System.out.println(meetingPub);
        }
    }

}
