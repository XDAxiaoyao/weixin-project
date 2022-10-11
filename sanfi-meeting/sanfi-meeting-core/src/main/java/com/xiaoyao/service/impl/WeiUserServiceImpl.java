package com.xiaoyao.service.impl;

import com.xiaoyao.entity.po.WeiUser;
import com.xiaoyao.mapper.WeiUserMapper;
import com.xiaoyao.service.WeiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/10 20:01
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Service
@Slf4j
public class WeiUserServiceImpl implements WeiUserService {

    @Autowired
    private WeiUserMapper weiUserMapper;

    @Override
    public WeiUser selectWeiUserByOpenId(String openid) {
        return weiUserMapper.selectWeiUserByOpenId(openid);
    }

    @Override
    public int insertSelective(WeiUser record) {
        return weiUserMapper.insertSelective(record);
    }
}
