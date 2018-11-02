package com.service;

import com.bean.Student;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StudentService {
    int deleteByPrimaryKey(Integer studentid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer studentid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);

    //查询学生列表
    public PageInfo getall(String stuname,String studentno,Integer stusex,String examsubject, int pageindex, int size);
}
