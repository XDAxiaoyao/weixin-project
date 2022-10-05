package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    @Select("select * from user")
    List<User> selectList();

    /**
     * 批量添加
     */
    public int insertBatch(List<User> list);
}
