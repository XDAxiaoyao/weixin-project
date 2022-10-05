package com.xiaoyao.controller;


import com.xiaoyao.entity.po.User;
import com.xiaoyao.service.UserService;
import com.xiaoyao.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    /**
     * TODO 用户管理页面
     */

    @GetMapping("list")
    public String userListToPage(HttpServletRequest request){
        List<User> list = userService.queryList();
        request.setAttribute("list",list);
        return "user/list";
    }


    /**
     * TODO 进入excel的导入页面
     *
     */
        @GetMapping("importToPage")
    public String importToPage(){
        return "user/importExcel";
    }

    /**
     * TODO excel数据导入
     * 接受前端excel文件进行数据导入
     */
    @PostMapping("importExcel")
    @ResponseBody
    public Object importExcel(@RequestParam("file123")MultipartFile file){
        int i = 0;
        try {
            List<User> list = ExcelUtils.importExcel(file, User.class);
            log.info("打印的结果集：{}" + list);
            i = userService.addBatch(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return i;
    }


    /**
     * todo 根据id删除信息
     */

    @ResponseBody
    @PostMapping("deleteUserById")
    public int deleteUserById(@RequestParam("id") final  int id ){

        return userService.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @PostMapping("deleteBatch")
    public int deleteBatchByIds(@RequestParam("idsStr") String idsStr){
        //使用,做ids的分隔拆分
        String[] ids = idsStr.split(",");
        int i = userService.deleteBatch(ids);
        log.info("批量删除{}",i);
        return i;
    }
}
