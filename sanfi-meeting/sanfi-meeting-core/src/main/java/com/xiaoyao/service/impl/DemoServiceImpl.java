package com.xiaoyao.service.impl;

import com.xiaoyao.entity.po.Demo;
import com.xiaoyao.mapper.DemoMapper;
import com.xiaoyao.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DemoServiceImpl
 * @Description TODO
 * @Author guoweixin
 * @Date 2022/9/28
 * @Version 1.0
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;
    /**
     * Demo列表
     */
    @Override
    public List<Demo> selectList() {
        return demoMapper.selectList();
    }
}
