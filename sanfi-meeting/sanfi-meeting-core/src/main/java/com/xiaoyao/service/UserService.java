package com.xiaoyao.service;

import com.xiaoyao.entity.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserService {

    /**
     * TODO 查询用户列表全部数据
     * @return
     */
    List<User> queryList();

    /**
     * TODO 批量添加
     */
    public int addBatch(List<User> list);
}
