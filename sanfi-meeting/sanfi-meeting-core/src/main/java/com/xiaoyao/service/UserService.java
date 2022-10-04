package com.xiaoyao.service;

import com.xiaoyao.entity.po.User;

import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/9/29 21:25
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
public interface UserService {
    int addUser(User user);

    List<User> queryUser();
}
