package com.xiaoyao.project.weixin.api.accessToken;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Program:
 * @ClassName:  AccessTokenListener
 * @Date: 2022/10/10 11:50
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description: TODO 监听器
 */

@Component
@Slf4j
public class AccessTokenListener implements ServletContextListener {

    //重写监听器方法
    @Override
    //上下文初始化监听方法
    public void contextInitialized(ServletContextEvent sce) {
        log.info("项目启动啦----》");
    }

    @Override
    //项目运行结束监听方法
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("项目关闭啦----》");
    }
}
