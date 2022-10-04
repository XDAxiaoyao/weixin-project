package com.xiaoyao;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.xiaoyao.entity.po.User;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SanfiMeetingManagerApplicationTests {

    // public List<User> getUser(){
    //     ArrayList<User> users = new ArrayList<User>();
    //     for (int i = 0;i<10;i++){
    //         User user = new User();
    //         user.setId(i);
    //         user.setName("小明");
    //
    //         if (i%2==0){
    //             user.setStatus("1");
    //         }else {
    //             user.setStatus("0");
    //         }
    //         users.add(user);
    //     }
    //     return users;
    // }
    //导出excel
    // @Test
    // public void setExcel() throws IOException {
    //     //获取数据
    //     List<User> user = getUser();
    //     //导出excel
    //     //excel的描述，导出对象类型，导出数据集合
    //     Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信心列表", "用户信息"), User.class, user);
    //     //导出到指定位置
    //     workbook.write(new FileOutputStream("D:/upload/cc.xlsx"));
    //     workbook.close();
    // }






}
