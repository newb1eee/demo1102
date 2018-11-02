package com.service;

import com.bean.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface MenuService {
    int deleteByPrimaryKey(Integer menuid);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer menuid);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    //根据角色ID查询菜单集合
    public List<Menu> findbyroleid(int roleid);

    public List<Menu> getallmenu();

    PageInfo getall2(int index,int size,int upmenid);

    List getall3();
    int deletebyids(String[] ids) throws Exception;

}
