package com.xiaoyao.service;

import com.xiaoyao.entity.po.WeiUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/10 20:00
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

public interface WeiUserService {
    /**
     * TODO 根据openid查询wei_user对象
     */
    WeiUser selectWeiUserByOpenId(String openid);

    /**
     * TODO 添加wei_user （收集微信个人信息）
     */
    int insertSelective(WeiUser record);
}
