package com.xiaoyao.controller;


import com.xiaoyao.common.utils.redis.RedisRepeatUtils;
import com.xiaoyao.entity.po.User;
import com.xiaoyao.service.UserService;
import com.xiaoyao.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *重复提交的最终代码优化解决方案 redis token
     */
@Autowired
private RedisRepeatUtils redisRepeatUtils;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

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
    public String importToPage(HttpServletRequest request){
        /**
         * 1.进入添加页面
         * 2.生成redis token唯一标识值
         * 3.存到redis库中
         * 4.存到request作用域中
         */

        // //利用uuid工具类生成一个随机的uuid当作唯一标识
        // String uuid = UUID.randomUUID().toString();
        // //redis中设置key和value 存到redis库中
        // redisTemplate.opsForValue().set("cjh:importExcel:"+uuid,uuid);
        // //请求域中存储uuid的值
        // request.setAttribute("token",uuid);

        /**
         * 对上面代码的优化
         */
redisRepeatUtils.repeatedGenerateToken(request,"cjh:importExcel:");
        return "user/importExcel";
    }

    /**
     * TODO excel数据导入
     * 接受前端excel文件进行数据导入
     */
    @PostMapping("importExcel")
    @ResponseBody
    public Object importExcel(@RequestParam("file123")MultipartFile file,HttpServletRequest request){
        /**
         * 5.token校验
         * （1） 获取后台redis key ,立刻删除key
         * （2） 获取表单前端给的值（token）
         * 对两个值进行等于判断，如果相等，执行下方的逻辑添加
         * 如果不相等，则不执行下方的业务逻辑
         */
        // String token = request.getParameter("token");
        // String key = "cjh:importExcel:" + token;
        // String redisValue = (String) redisTemplate.opsForValue().get(key);
        // redisTemplate.delete(key);
        // if (!token.equals(redisValue)){
        //     log.error("此次请求是重复提交");
        //     log.info("失败redisValue="+ redisValue);
        //     return -1;
        // }
      if (false == redisRepeatUtils.repeatedCheck(request,"cjh:importExcel:")){
            return -1;
      }

        int i = 0;
        try {
            List<User> list = ExcelUtils.importExcel(file, User.class);
            log.info("打印的结果集：{}" + list);
            List<User> listExcel = this.repeatEmail(list);
            i = userService.addBatch(listExcel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return i;
    }

    /**
     * TODO 去重功能（email）
     * 1.查询mysql所有的邮件数据
     * 2.excel导入列表数据
     */

    //List<User> listExcel 表示user表中所有的数据
    public List<User> repeatEmail(List<User> listExcel){
        //查询出user表中邮箱字段的数据
        List<String> listDB = userService.selectEmails();
        //做for循环 循环长度 是excel的长度
        for (int i = 0; i < listExcel.size(); i++) {
            User u = listExcel.get(i);
            if (listDB.contains(u.getEmail())){
                listExcel.remove(u);
                i--;
            }
        }
        return listExcel;
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



    /**
     * TODO 用户状态修改
     * @param status 状态0 无效 1 有效
     * @param id 主键唯一标识
     */

    @PostMapping("updateStatusById")
    @ResponseBody
    public int updateStatusById(
            @RequestParam("status") int status,
            @RequestParam("id") int id
    ){
        return userService.changeStatusById(status,id);
    }



//     @RequestMapping("add")
// public String userAddPage(){
//         return "user/add";
//     }
//
//
//     @RequestMapping("addToPage")
//     @ResponseBody
//     public User userAdd( @RequestParam("id") int id){
//         User user = userService.queryListById(id);
//         log.info("id="+id);
//
//         return user;
//     }

    @RequestMapping("/add")
    public String userAddPage(HttpServletRequest request,@RequestParam("id") Integer id){
        User user = userService.queryListById(id);
        // 存进request
        request.setAttribute("user",user);
        log.info("id="+id);
        return "user/add";
    }


    @RequestMapping("updateUserById")
    @ResponseBody
    public int updateUserById(
            @RequestParam("id")int id,
            @RequestParam("name")String name,
            @RequestParam("email")String email,
            @RequestParam("telephone")String telephone,
            @RequestParam("province")String province,
            @RequestParam("city")String city,
            @RequestParam("zone")String zone
    ){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setTelephone(telephone);
        user.setProvince(province);
        user.setCity(city);
        user.setZone(zone);
        user.setId(id);
        int i = userService.updateByPrimaryKey(user);
        return i;
    }

}
