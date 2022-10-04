package com.xiaoyao.controller;

import com.xiaoyao.entity.po.Demo;
import com.xiaoyao.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName DemoController
 * @Description TODO
 * @Author guoweixin
 * @Date 2022/9/28
 * @Version 1.0
 */
@Controller
@RequestMapping("demo")
@Slf4j
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("list")
    @ResponseBody
    public List<Demo> list(){

        return demoService.selectList();
    }
}
