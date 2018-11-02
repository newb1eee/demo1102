package com.service;

import com.bean.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface RoleService {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    PageInfo getall(int pageindex,int size);
//新增
    public int addrole(Role r,int[] menus);
    //修改
    public int update(Role role,int[] ids);
    //删除
    public int deleterole(int roleid);
}
