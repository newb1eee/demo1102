package com.controller;

import com.bean.Exam;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import com.service.DepartService;
import com.service.ExamService;
import com.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ExamController {
    @Autowired
    private ExamService examService;
    @Autowired
    private ClassService classService;
    @Autowired
    private DepartService departService;
    @Autowired
    private MajorService majorService;


    @RequestMapping("/Educational/exam/getexamlist")
    public String getexamlist(String examsubject,
                              @RequestParam(value = "index", defaultValue = "1") int pageindex, ModelMap map,
                              @RequestParam(value = "size", defaultValue = "5") int size){
        PageInfo getall = examService.getall(examsubject, pageindex, size, null);
        map.put("pi",getall);
        map.put("esub",examsubject);
        map.put("size",size);


        return "/Educational/exam/exam";
    }

    @RequestMapping("/Educational/exam/addexam")
    public String addexam(Exam exam){
            exam.setExamstate("准备中");
            examService.insert(exam);

        return "redirect:/Educational/exam/getexamlist";
    }



    //查询全部院系
    @RequestMapping("/Educational/exam/detDepts")
    public String getDepts(ModelMap map) {
        List list = departService.findall();
        map.put("dlist", list);

        return "/Educational/exam/add";
    }

    //下拉框出来与院系对应的专业
    @RequestMapping("/Educational/exam/getmajorbydid")
    @ResponseBody
    public List getmajor(int did) {

        List list = majorService.findbyid(did);

        return list;
    }

    //下拉框调出与专业对饮的班级名称
    @RequestMapping("/Educational/exam/getclassname")
    @ResponseBody
    public List getclassname(int mid) {

        List list = examService.getclassname(mid);

        return list;
    }
}
