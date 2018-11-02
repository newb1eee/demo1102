package com.service.imp;

import com.bean.Classes;
import com.bean.Exam;
import com.dao.ClassesMapper;
import com.dao.ExamMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ExamServiceImp implements ExamService {

    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public int insert(Exam record) {
        return examMapper.insert(record);
    }

    @Override
    public int insertSelective(Exam record) {
        return 0;
    }

    @Override
    public PageInfo getall(String examsubject,int pageindex, int size,String state) {

        Map map = new HashMap();
        map.put("esub",examsubject);
        PageHelper.startPage(pageindex,size);
        List list = examMapper.getall(map);
        PageInfo pi = new PageInfo(list);
        return pi;
    }


    @Override
    public List<Classes> getclassname(int mid) {
        return classesMapper.getclassname(mid);
    }
}
