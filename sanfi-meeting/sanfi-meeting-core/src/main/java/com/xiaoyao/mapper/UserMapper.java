package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.User;

import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/9/29 21:14
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
public interface UserMapper {
    int insertUser(User user);
    List<User> selectUser();
}
