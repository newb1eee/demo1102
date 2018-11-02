package com.service;

import com.bean.Classes;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ClassService {

    //查询全部学生
    public PageInfo getall(String name,String classnum,int pageindex,int size,int[] ids,String state);

    //查询对应年纪名称
    public List findall(int did,int mid);

    //mid
    List<Classes> getclassname(int mid);


    int deleteByPrimaryKey(Integer classid);

    int insert(Classes record);

    int insertSelective(Classes record);

    Classes selectByPrimaryKey(Integer classid);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

}
