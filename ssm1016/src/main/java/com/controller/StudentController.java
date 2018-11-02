package com.controller;

import com.bean.Student;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import com.service.DepartService;
import com.service.MajorService;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClassService classService;
    @Autowired
    private DepartService departService;
    @Autowired
    private MajorService majorService;

    //学生成绩查询
    @RequestMapping("/Educational/studentmanage")
    public String getall(String examsubject,
                       @RequestParam(value = "index",defaultValue = "1") int pageindex,
                       ModelMap map,
                       @RequestParam(value = "size",defaultValue = "5") int size){

        PageInfo pageInfo = studentService.getall(null,null,null,examsubject, pageindex, size);

        map.put("pi",pageInfo);
        map.put("examsubject",examsubject);
        map.put("size",size);

        return "/Educational/list";
    }


    //学生信息管理查询
    @RequestMapping("/Educational/student/getstudentlist")
    public String getallstudent(String stuname,String studentno,Integer stusex,
                                @RequestParam(value = "index",defaultValue = "1") int pageindex,
                                ModelMap map,
                                @RequestParam(value = "size",defaultValue = "5") int size){

        PageInfo pi = studentService.getall(stuname, studentno, stusex,null, pageindex, size);
        map.put("pi",pi);
        map.put("sname",stuname);
        map.put("sno",studentno);
        map.put("size",size);


        return "/Educational/student/list";
    }


    //查询全部院系
    @RequestMapping("/Educational/student/detDepts")
    public String getDepts(ModelMap map) {
        List list = departService.findall();
        map.put("dlist", list);

        return "/Educational/student/add";
    }

    //下拉框出来与院系对应的专业
    @RequestMapping("/Educational/student/getmajorbydid")
    @ResponseBody
    public List getmajor(int did) {

        List list = majorService.findbyid(did);

        return list;
    }

    //下拉框调出与专业对饮的班级名称
    @RequestMapping("/Educational/student/getclassname")
    @ResponseBody
    public List getclassname(int mid) {

        List list = classService.getclassname(mid);

        return list;
    }

    //新增学生
    @RequestMapping("/Educational/student/addstudent")
    public String addstudent(Student student){
        studentService.insert(student);
        return "redirect:/Educational/student/getstudentlist";
    }
}
