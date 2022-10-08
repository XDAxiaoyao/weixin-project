package com.xiaoyao;

import com.xiaoyao.common.utils.redis.RedisRepeatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SanfiMeetingManagerApplicationTests {


    @Autowired
    private RedisRepeatUtils repeatUtils;
  @Test
    void testRedis(){
    repeatUtils.info();
  }





}
