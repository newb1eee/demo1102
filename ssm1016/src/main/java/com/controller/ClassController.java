package com.controller;

import com.bean.Classes;
import com.dao.UserTbMapper;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import com.service.DepartService;
import com.service.MajorService;
import com.service.UserTbService;
import com.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ClassController {

    @Autowired
    private ClassService service;
    @Autowired
    private DepartService departService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private UserTbService userTbService;

    @RequestMapping("/Educational/class/getclasslist")
    public String getall(String classname, String classnum,
                         @RequestParam(value = "index", defaultValue = "1") int pageindex, ModelMap map,
                         @RequestParam(value = "size", defaultValue = "5") int size) {
        PageInfo pageInfo = service.getall(classname, classnum, pageindex, size, null, null);
        map.put("pi", pageInfo);
        map.put("cname", classname);
        map.put("cnum", classnum);
        map.put("size", size);

        return "/Educational/class/list";
    }

    //查询全部院系
    @RequestMapping("/Educational/class/detDepts")
    public String getDepts(ModelMap map) {
        List list = departService.findall();
        map.put("dlist", list);

        return "/Educational/class/add";
    }

    //下拉框出来与院系对应的专业
    @RequestMapping("/Educational/class/getmajorbydid")
    @ResponseBody
    public List getmajor(int did) {

        List list = majorService.findbyid(did);

        return list;
    }

    //下拉框调出与专业对饮的班主任
    @RequestMapping("/Educational/class/getteacher")
    @ResponseBody
    public List getteacher(int did, int mid) {

        List list = userTbService.findall(did, mid, "班主任");

        return list;
    }

    //新增班级
    @RequestMapping("/Educational/class/addclass")
    public String add(Classes classes) {

        classes.setClassstate("未审核");

        service.insert(classes);

        return "redirect:/Educational/class/getclasslist";
    }

    //提交审核
    @RequestMapping("/Educational/class/updateclassstate")
    public String updatestate(Classes classes) {
        classes.setClassstate("审核中");
        classes.setAuditcount(classes.getAuditcount()+1);
        service.updateByPrimaryKeySelective(classes);
        return "redirect:/Educational/class/getclasslist";
    }

    @RequestMapping("/Educational/updatestate")
    public String update(Classes classes){
        service.updateByPrimaryKeySelective(classes);
        return "redirect:/Educational/getclasses";//审核结果提交后，重新刷新
    }


    //导出班级信息
    @RequestMapping("/Educational/class/daochu")
    public void daochu(int[] single, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        PageInfo pg = service.getall(null, null, 0, 0, single, null);
        List<Classes> list = pg.getList();
        ExcelUtil.headers = new String[]{"院系", "班级编号", "编辑名称", "班主任老师", "人数", "班级状态"};
        ExcelUtil.createhead(6);
        ExcelUtil.createother(list);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = simpleDateFormat.format(new Date());
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\java培训\\3阶段\\20181016 SSM项目" + date + ".xls");
            ExcelUtil.export(out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        PrintWriter out2 = null;
        try {
            out2 = response.getWriter();
            out2.print("<script>alert('导出成功');location.href='/Educational/class/getclasslist'</script>");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //审核-查询班级
    @RequestMapping("/Educational/getclasses")
    public String getclass(String classname, String classnum,
                           @RequestParam(value = "index", defaultValue = "1") int pageindex,
                           ModelMap map,
                           @RequestParam(value = "size", defaultValue = "5") int size) {


        PageInfo pi = service.getall(classname, classnum, pageindex, size, null, "审核中");
        map.put("pi",pi);
        map.put("cname",classname);
        map.put("cnum",classnum);
        map.put("size",size);
        return "/Educational/Auditing";
    }



}


