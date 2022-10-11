package com.xiaoyao.service.impl;

import com.xiaoyao.entity.po.MeetingType;
import com.xiaoyao.mapper.MeetingTypeMapper;
import com.xiaoyao.service.MeetingTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/11 19:19
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@Service
@Slf4j
public class MeetingTypeServiceImpl implements MeetingTypeService {
    @Autowired
    private MeetingTypeMapper meetingTypeMapper;

    @Override
    public List<MeetingType> selectMeetingTypeStatusAndSortNumAsc() {
        return meetingTypeMapper.selectMeetingTypeStatusAndSortNumAsc();
    }
}
