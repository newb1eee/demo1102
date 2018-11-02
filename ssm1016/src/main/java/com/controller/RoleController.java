package com.controller;

import com.bean.Role;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    //查询出当前管理员
    @RequestMapping("/power/role/getall")
    public String getallroles(@RequestParam(defaultValue = "1") int index,/*Control层的pageindex和size名字尽量和FENYEUTIL中名字相同*/
                              @RequestParam(defaultValue = "5") int size,
                              ModelMap map){
        PageInfo pi = roleService.getall(index, size);
        map.put("pi",pi);
        return "/power/role/list";
    }

    //查询所有菜单
    @RequestMapping("/power/role/getallmenus")
    public String getmenus(ModelMap map){
        List list = menuService.getallmenu();
        map.put("menulist",list);
        return "/power/role/add";
    }

    //新增角色
    @RequestMapping("/power/role/addrole")
    public String addrole(Role role,int[] menu){//参数列表要和jsp文件中的name=""值要相同
        System.out.println(menu.length);
        roleService.addrole(role,menu);
        return "redirect:/power/role/getall";
    }

    //根据角色id查询信息
    @RequestMapping("/power/role/findbyrid")
    public String getrolebyid(int rid,ModelMap map){ //此处rid需要与list文件中的？rid=${}中的rid相同，不然读不到数
        Role role = roleService.selectByPrimaryKey(rid);
        //查询所有菜单列表
        List list = menuService.getallmenu();
        map.put("role",role);
        map.put("menulist",list);
        return "/power/role/edit";
    }

    //修改角色
    @RequestMapping("/power/role/updaterole")
    public String updaterole(Role role,int[] menu){
        roleService.update(role,menu);

        return "redirect:/power/role/getall";
    }

    //删除角色
    @RequestMapping("/power/role/deleterole")
    public void deleterole(int rid, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            int k = roleService.deleterole(rid);
            if(k>0){
                out.print("<script>alert('删除成功');location.href='/power/role/getall'</script>");
            }else {
                out.print("<script>alert('删除失败，请先删除角色下的用户');location.href='/power/role/getall';</script>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
