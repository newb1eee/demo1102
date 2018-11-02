package com.controller;

import com.bean.UserTb;
import com.github.pagehelper.PageInfo;
import com.service.UserTbService;
import com.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes({"u1","logintime"})  //Attribute多个数值传入用集合
public class UserController {

    @Autowired
    private UserTbService userTbService;



    //登录
    @RequestMapping("/login")
    public void login(HttpServletResponse resp, UserTb userTb, String DropExpiration,
                      ModelMap map,String yanzheng) {
        System.out.println(userTb.getUserName() + "..." + userTb.getUserPs());
        String yz = ImageUtil.getCode();
        resp.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = resp.getWriter();
            if (yz.equals(yanzheng)) {
                UserTb u = userTbService.login(userTb);

                if (u == null) {
                    out.print("<script>alert('用户名或密码不正确');location.href='login.jsp'</script>");
                } else {
                    map.put("u1", u);
                    Cookie c = new Cookie("uname", userTb.getUserName());
                    if (DropExpiration.equals("Day")) {
                        c.setMaxAge(24 * 60 * 60);
                    } else if (DropExpiration.equals("Month")) {
                        c.setMaxAge(24 * 60 * 60 * 31);
                    } else if (DropExpiration.equals("Year")) {
                        c.setMaxAge(24 * 60 * 60 * 365);
                    }
                    resp.addCookie(c);
                    //登录时间
                    Date date = new Date();
                    map.put("logintime", date);
                    out.print("<script>alert('登录成功');location.href='index.jsp'</script>");
                }

            } else {
                out.print("<script>alert('验证码错误');location.href='login.jsp'</script>");
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        }

    //修改信息
    @RequestMapping("/user/updateuser")
    public void updateuser(UserTb userTb,HttpServletResponse response,ModelMap map){
        response.setContentType("text/html;charset=utf-8");

        int k = userTbService.updateByPrimaryKeySelective(userTb);
        try {
            PrintWriter out = response.getWriter();
            if(k>0){
                UserTb userTb1 = userTbService.selectByPrimaryKey(userTb.getUserId());
                map.put("u1",userTb1);//更新session
                out.write("<script>alert('修改成功');top.location.href='/index.jsp'</script>");
            }else{
                out.write("<script>alert('修改失败');top.location.href='MyUser.jsp'</script>");
                                                                    //myuser不需要 / 因为就在当前文件夹下面

                //top.location.href 是跳出框架,重新进入
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //验证密码
    @RequestMapping("/user/checkpass")
    public  void checkpass(String upass,ModelMap map,HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        UserTb userTb = (UserTb) map.get("u1");
        try {
            PrintWriter out= response.getWriter();
            if(userTb.getUserPs().equals(upass)){
                out.print(true);
            }else {
                out.print(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //修改密码
    @RequestMapping("/user/updatepass")
    public void updatepass(UserTb userTb, HttpServletResponse response, SessionStatus status){
        response.setContentType("text/html;charset=utf-8");
        int k = userTbService.updateByPrimaryKeySelective(userTb);
        try {
            PrintWriter out = response.getWriter();
            if (k>0){
                status.setComplete();
                out.print("<script >alert('修改成功，请重新登录');top.location.href='/login.jsp'</script>");
            }else {
                out.print("<script >alert('修改失败');location.href='password.jsp'</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
