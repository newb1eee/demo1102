package com.controller;

import com.bean.Menu;
import com.bean.UserTb;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.service.UserTbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private UserTbService userTbService;

    @RequestMapping("/power/menu/getmenus")
    public String getmenus(@RequestParam(defaultValue = "1") int index,
                           @RequestParam(defaultValue = "5") int size,
                           ModelMap map){

        PageInfo pageInfo = menuService.getall2(index,size,-10);
        map.put("pi",pageInfo);
        return "/power/menu/list";

    }
    //查询一级菜单
    @RequestMapping("/power/menu/getupmenu")
    public String getup(ModelMap map){
        List list = menuService.getall3();
        map.put("uplist",list);
        return "/power/menu/add";
    }
    @RequestMapping("/power/menu/addmenu")
    public String add(Menu menu){
        menuService.insert(menu);
        return "redirect:/power/menu/getmenus";
    }


    //删除
    @RequestMapping("/power/menu/deletemenu")
    private String delete(int mid){
        menuService.deleteByPrimaryKey(mid);
        return "redirect:/power/menu/getmenus";
    }


    //批量删除
    @RequestMapping("/power/menu/deleteall")
    private String deleteall(String[] menuids){
        //从menuids中挑出一级菜单的iD
        try {
            menuService.deletebyids(menuids);
        } catch (Exception e) {
            //回滚代码
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "redirect:/power/menu/getmenus";
    }


    //权限管理中查询用户管理
    @RequestMapping("/power/user/getall")
    public String getall(@RequestParam(value = "index",defaultValue = "1")int index,ModelMap map,
                         @RequestParam(value = "size",defaultValue = "5")int size){

        PageInfo getall = userTbService.getall(index, size);
        map.put("pi",getall);
        map.put("size",size);
        return "/power/user/list";

    }

    //用户管理新增
    @RequestMapping("/power/user/add")
    public String add(UserTb userTb){



        return "/power/user/size";
    }
}
