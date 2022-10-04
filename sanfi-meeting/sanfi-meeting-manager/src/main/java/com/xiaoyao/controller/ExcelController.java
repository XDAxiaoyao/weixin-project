package com.xiaoyao.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.xiaoyao.entity.po.User;
import com.xiaoyao.service.UserService;
import com.xiaoyao.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/9/29 18:45
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */
@Controller
@Slf4j
@RequestMapping("excel")
public class ExcelController {
    @Autowired
    private UserService userService;
    /**
     * 导出功能
     */
    @RequestMapping("export")  //excel/export
    public  void exportExcel(HttpServletResponse response) throws IOException {

        List<User> users = userService.queryUser();
        List<User> list=new ArrayList<>();
        for (User user : users) {


            list.add(user);
        }

       // excel的描述，导出对象类型，导出数据集合
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信心列表", "用户信息"), User.class, list);
            //导出到指定位置
            workbook.write(new FileOutputStream("D:/upload/bb.xls"));
            workbook.close();
        System.out.println(list);

        }



    /**
     * 导入功能
     */
    @RequestMapping("upload")   // excel/upload
    public  String uploadExcel(@RequestParam("file") MultipartFile file){
        long startTime=System.currentTimeMillis();
        try {
            List<User> list= ExcelUtils.importExcel(file,User.class);
            System.out.println(list.toString());

            System.out.println("------>打印------>");
            for(int i=0;i<list.size();i++){
                System.out.println(list.get(i));
                int i1 = userService.addUser(list.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime=System.currentTimeMillis();
        System.out.println("用时："+(endTime-startTime));
        return "/index";
    }



}
