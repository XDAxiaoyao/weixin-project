package com.xiaoyao.common.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/8 17:28
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:// TODO: 2022/10/8 高并发 （重复提交） 解决方案
 */

@Component
@Slf4j
public class RedisRepeatUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
//redis过期时间key （重复提交）
    private static long REPEATED_TOKEN_EXPIRE_MINUTES = 30;
    public void info() {
        redisTemplate.opsForValue().set("java", "java2203");
        String result = (String) redisTemplate.opsForValue().get("java");
        log.info("结果=" + result);
    }


/**
 *todo 生成token repeated generate
 *          * 1.进入添加页面
 *          * 2.生成redis token唯一标识值
 *          * 3.存到redis库中
 *          * 4.存到request作用域中
 * @param request
 * @param key 格式按照页面的路径名称来写
 */
public void repeatedGenerateToken(HttpServletRequest request,String key){
    //利用uuid工具类生成一个随机的uuid当作唯一标识
    String uuid = UUID.randomUUID().toString();
    //redis中设置key和value 存到redis库中
    redisTemplate.opsForValue().set(key+uuid,uuid,REPEATED_TOKEN_EXPIRE_MINUTES, TimeUnit.MINUTES);
    //请求域中存储uuid的值
    request.setAttribute("token",uuid);
}

/**
 * TODO 校验token repeated check
 * @param request
 *
 */

public boolean repeatedCheck(HttpServletRequest request,String key){
    //前端传递
    String token = request.getParameter("token");
    //redis key
    key = key + token;
    String redisValue = (String) redisTemplate.opsForValue().get(key);
    redisTemplate.delete(key);
    if (!token.equals(redisValue)){
        log.error("此次请求是重复提交");
        log.info("失败redisValue="+ redisValue);
        return false;
    }
    return true;
}

}
