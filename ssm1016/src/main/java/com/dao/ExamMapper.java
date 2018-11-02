package com.dao;

import com.bean.Exam;

import java.util.List;
import java.util.Map;

public interface ExamMapper {
    int insert(Exam record);

    int insertSelective(Exam record);

    List getall(Map map);

}