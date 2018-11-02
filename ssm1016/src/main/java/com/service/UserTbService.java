package com.service;

import com.bean.UserTb;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface UserTbService {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserTb record);

    int insertSelective(UserTb record);

    UserTb selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserTb record);

    int updateByPrimaryKey(UserTb record);

    UserTb login(UserTb userTb);

    List findall(int did,int mid,String rolename);

    PageInfo getall(int index, int size);


}
