package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.MeetingGrab;

public interface MeetingGrabMapper {
    int deleteByPrimaryKey(String id);

    int insert(MeetingGrab record);

    int insertSelective(MeetingGrab record);

    MeetingGrab selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingGrab record);

    int updateByPrimaryKey(MeetingGrab record);
}