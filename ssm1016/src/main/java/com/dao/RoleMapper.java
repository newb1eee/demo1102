package com.dao;

import com.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List getall();

    public int insertMiddle(Map map);

    //根据id删除角色表
    int deletemiddlebyrid(int rid);

    int findusercountbyroleid(int rid);
}