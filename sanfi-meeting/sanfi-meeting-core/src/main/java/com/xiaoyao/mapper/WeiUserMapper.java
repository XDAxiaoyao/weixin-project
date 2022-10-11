package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.WeiUser;
import org.apache.ibatis.annotations.Select;

public interface WeiUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeiUser record);

    int insertSelective(WeiUser record);

    WeiUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeiUser record);

    int updateByPrimaryKey(WeiUser record);

    /**
     * 根据openid查询wei_user对象
     */

    @Select("select * from wei_user where openid = #{openid}")
    WeiUser selectWeiUserByOpenId(String openid);
}













