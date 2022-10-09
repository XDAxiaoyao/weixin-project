package com.xiaoyao;

import com.xiaoyao.project.weixin.api.tuling.TuLingUtilsTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SanfiMeetingWeixinApplicationTests {
    /**
     * 图灵机器人的测试
     */
    @Autowired
    private TuLingUtilsTest tuLingUtils;

    @Test
    void contextLoads() {
        tuLingUtils.test();
    }

}
