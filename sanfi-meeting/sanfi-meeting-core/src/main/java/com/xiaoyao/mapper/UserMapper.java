package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 批量删除 多个id值 会放在一个数组string[] list<integer>
     */
    public int deleteBatch(String[] ids);


    /**
     * 状态修改
     */

    @Update("update user set status=#{status} where id = #{id}")
    public int updateStatusById(int status,int id);


}
