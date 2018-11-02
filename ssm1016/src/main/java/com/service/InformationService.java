package com.service;

import com.bean.Information;
import com.bean.Infotype;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface InformationService {
    int deleteByPrimaryKey(Integer informationid);

    int insert(Information record);

    int insertSelective(Information record);

    Information selectByPrimaryKey(Integer informationid);

    int updateByPrimaryKeySelective(Information record);

    int updateByPrimaryKey(Information record);

    PageInfo getallinfo(Integer typeid, String informationname, int pageindex, int size);

    List<Infotype> getinformationname(Integer typeid);

}
