package com.service;

import com.bean.Classes;
import com.bean.Exam;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ExamService {
    int insert(Exam record);

    int insertSelective(Exam record);

    PageInfo getall(String examsubject, int pageindex, int size, String state);


    public List<Classes> getclassname(int mid);
}
