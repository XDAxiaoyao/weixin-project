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

    @Select("select * from user where id =#{id}")
    User selectListById(int id);


    /**
     * 查询所有的邮件列表数据
     */
    @Select("select email from user")
    public List<String> selectEmails();


    /**
     * 根据wid（wei_user[id] 判断user是否存在）
     */
    @Select("select * from user where wid = #{wid}")
 public User selectUserByWid(int wid);


    /**
     * 根据email 查询user
     */
    @Select("select * from user where email = #{email}")
    public User selectUserByEmail(String email);


    /**
     * 更新user wid 根据email
     */
    @Update("update user set wid = #{wid} where email =#{email}")
    public int updateUserWidByEmail(int wid, String email);



}
