package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.MeetingPub;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeetingPubMapper {
    int deleteByPrimaryKey(String id);

    int insert(MeetingPub record);

    //修改sql语句
    int insertSelective(MeetingPub record);

    MeetingPub selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MeetingPub record);

    int updateByPrimaryKey(MeetingPub record);

    /**
     * 会议编号生成规则
     *
     */

    @Select("select max(pcode) from meeting_pub where LEFT(pcode,8) =#{time}")
    public String selectMeetingPubMaxPcodeByTime(String time);


    /**
     * 我得会议列表（发布）
     * 创建sql语句查询列表数据
     */
    public List<MeetingPub> selectMeetingPubListByUid(int uid);
}
