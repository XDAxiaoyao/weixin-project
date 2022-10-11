package com.xiaoyao.mapper;

import com.xiaoyao.entity.po.MeetingType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MeetingTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MeetingType record);

    int insertSelective(MeetingType record);

    MeetingType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MeetingType record);

    int updateByPrimaryKey(MeetingType record);

    /**
     * TODO 微信前端列表加载 状态=1 排序字段升序
     */
@Select("select * from meeting_type where status = 1 order by sortnum")
    public List<MeetingType> selectMeetingTypeStatusAndSortNumAsc();
}
