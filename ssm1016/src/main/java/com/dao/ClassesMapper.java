package com.dao;

import com.bean.Classes;
import org.apache.poi.hssf.record.formula.MemAreaPtg;

import java.util.List;
import java.util.Map;

public interface ClassesMapper {
    int deleteByPrimaryKey(Integer classid);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<Classes> getall(Map map);

    List findall(Map map);

    List<Classes> getclassname(int mid);
}