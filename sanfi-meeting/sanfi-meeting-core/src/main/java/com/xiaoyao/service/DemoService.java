package com.xiaoyao.service;

import com.xiaoyao.entity.po.Demo;

import java.util.List;

public interface DemoService {

    /**
     * Demo列表
     */
    public List<Demo> selectList();
}
