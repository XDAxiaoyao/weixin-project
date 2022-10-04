package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.Demo;
import com.xiaoyao.entity.po.Demo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DemoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Demo record);

    int insertSelective(Demo record);

    Demo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Demo record);

    int updateByPrimaryKey(Demo record);


    /**
     * 查找所有的信息
     */
    @Select("select * from demo")
    List<Demo> selectList();
}
