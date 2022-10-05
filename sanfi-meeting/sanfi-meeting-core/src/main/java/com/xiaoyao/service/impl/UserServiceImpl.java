package com.xiaoyao.service.impl;

import com.xiaoyao.entity.po.User;
import com.xiaoyao.mapper.UserMapper;
import com.xiaoyao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> queryList() {
        return userMapper.selectList();
    }

    @Override
    public int addBatch(List<User> list) {
        return userMapper.insertBatch(list);
    }
}
