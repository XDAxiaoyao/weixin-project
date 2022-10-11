package com.xiaoyao.service;

import com.xiaoyao.entity.po.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据id删除信息
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * todo 批量删除 多个id值 会放在一个数组string[] list<integer>
     */
    public int deleteBatch(String[] ids);


    /**
     *TODO 状态修改
     */


    public int changeStatusById(int status,int id);

    /**
     * // TODO: 2022/10/6  查询单个user数据
     */

   User queryListById(int id);


    /**
     *     int updateByPrimaryKey(User record);
     */

    int updateByPrimaryKey(User user);


    /**
     * Todo 查询所有的邮件列表数据
     */
    public List<String> selectEmails();


    /**
     * TODO  根据wid（wei_user[id] 判断user是否存在）
     */
    public User selectUserByWid(int wid);


    /**
     * 根据email 查询user
     */
    public User selectUserByEmail(String email);


    /**
     * TODO 登录绑定 （wei_user） 和user 进行一对一关联关系，更新user wid 根据email
     */
    public int updateUserWidByEmail(int wid, String email);

    /**
     * TODO 更新用户的个人信息
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);
}
