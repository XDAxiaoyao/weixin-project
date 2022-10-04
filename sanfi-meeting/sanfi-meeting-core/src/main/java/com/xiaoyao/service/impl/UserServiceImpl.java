package com.xiaoyao.service.impl;

import com.xiaoyao.entity.po.User;
import com.xiaoyao.mapper.UserMapper;
import com.xiaoyao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/9/29 21:26
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper  userMapper;
    @Override
    public int addUser(User user) {
        int i = userMapper.insertUser(user);
        return i;
    }

    @Override
    public List<User> queryUser() {
        List<User> users = userMapper.selectUser();
        return users;
    }
}
