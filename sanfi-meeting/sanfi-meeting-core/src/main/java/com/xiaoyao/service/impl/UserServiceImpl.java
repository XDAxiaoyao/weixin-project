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

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return userMapper.deleteBatch(ids);
    }

    @Override
    public int changeStatusById(int status, int id) {
        return userMapper.updateStatusById(status,id);
    }

    @Override
    public User queryListById(int id) {
        return userMapper.selectListById(id);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<String> selectEmails() {
        return userMapper.selectEmails();
    }

    @Override
    public User selectUserByWid(int wid) {
        return userMapper.selectUserByWid(wid);
    }

    @Override
    public User selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    @Override
    public int updateUserWidByEmail(int wid, String email) {
        return userMapper.updateUserWidByEmail(wid,email);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
