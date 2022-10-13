package com.xiaoyao.controller;

import com.xiaoyao.entity.po.User;
import com.xiaoyao.mapper.UserMapper;
import com.xiaoyao.project.weixin.api.messageCustom.MessageCustomUtil;
import com.xiaoyao.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program:
 * @ClassName:
 * @Date: 2022/10/10 23:55
 * @Auther: 潇遙
 * @Project_Name: weixin-project
 * @Description:
 */

@RequestMapping("user")
@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 微信客服发消息接口
     */
    @Autowired
    private MessageCustomUtil messageCustomUtil;


    /**
     * 会议抢单 进入登录页面
     * /user/userLoginToPage
     */
    @GetMapping("userLoginToPage")
public String userLoginToPage(HttpServletRequest request){
        String wid = request.getParameter("wid");
        String openid = request.getParameter("openid");
        // 如果不存在，进入登录页面。
        //将wid放入request作用域中 wid为weiuser的主键
        request.setAttribute("wid",wid);
        request.setAttribute("openid",openid);
        return "user/login";
}

/**
 * 无权限页面
 */

@GetMapping("unauth")
public String unauth(){
    return "unauth";
}




    /**
     *  TODO 微信个人中心的登录功能
     *  1、进入登录页面
     * 2、用户在前端输入邮箱
     *   2.1 判断用户输入的邮箱是否存在
     *     2.1.1如果不存在，--》提示：数据需要管理员申请
     *     2.1.2如果存在--》
     *              判断邮箱 wid是否为空，如果不为空：
     *                提示：您的邮箱已被其它用户绑定，如有疑问，请联系管理员。
     *              如果wid为空：
     *                  如果邮箱未被绑定：进行绑定功能
     *                  更新 user 根据email 更新wid值。
     */
    @ResponseBody
    @PostMapping("login")
    public String login(
            @RequestParam("email")final String email,
            @RequestParam("wid") final int wid,
            @RequestParam("openid")final String openid
    ){
        User user = userService.selectUserByEmail(email);
        if (user == null){
            //提示：您输入的邮箱不存在，请联系地区经理需要提交申请
            return "3";
        }else {
            // 判断邮箱对应的对象 wid是否为空
            if (user.getWid() != null){
                //提示：您的邮箱已被其它用户绑定，如有疑问，请联系管理员。
                return "2";
            }else {
                //绑定的业务逻辑 更新 user 根据email 更新wid值。
                int num = userService.updateUserWidByEmail(wid, email);
                log.info("{}和{}绑定成功{}",email,wid,num);
                //微信服务器给用户发送消息
                String rname = user.getRid()==1?"发单组":"抢单组";
                String content = "尊敬的用户您好:" + user.getName() + "。 您是:" + rname;
                messageCustomUtil.sendMessageText(openid,content);

                return "1";
            }
        }
    }

    /**
     * 调试 PC端
     */
    @RequestMapping("test/login")
    public String loginTest(HttpServletRequest request){
        request.setAttribute("wid",1);
        return "user/login";
    }

    /**
     * TODO 微信 个人中心 更新个人信息
     * 路径：/user/updateUser
     */

    @ResponseBody
    @PostMapping("updateUser")
    public Object updateUser(User user){
        int num = 0;
        try {
            //一般不会报错 除非存入的名字带有表情
            num = userService.updateByPrimaryKeySelective(user);
        } catch (Exception ex) {
            log.error("更新不要添加表情{}",ex.getMessage());
        }
        return num;

    }
}
