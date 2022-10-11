package com.xiaoyao.service;

import com.xiaoyao.entity.po.MeetingType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 19:18
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
public interface MeetingTypeService {

    /**
     * TODO 微信前端列表加载 状态=1 排序字段升序 加载有效的课题类表数据
     */
    public List<MeetingType> selectMeetingTypeStatusAndSortNumAsc();
}
