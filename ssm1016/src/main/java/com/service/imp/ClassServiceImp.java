package com.service.imp;

import com.bean.Classes;
import com.dao.ClassesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassServiceImp implements ClassService {
    @Autowired
    private ClassesMapper mapper;
    @Override
    public PageInfo getall(String name, String classnum, int pageindex, int size,int[] ids,String state) {
            //ids用来多选  //string用来模糊差 //int 用来分页
        //业务层，封装模糊差条件
        Map map = new HashMap();
        map.put("cname",name);
        map.put("clanum",classnum);
        map.put("ids",ids);
        map.put("state",state);
        PageHelper.startPage(pageindex,size);
        List list = mapper.getall(map);
        PageInfo pi = new PageInfo(list);

        return pi;
    }

    @Override
    public List findall(int did, int mid) {
        Map map = new HashMap();
        map.put("did",did);
        map.put("mid",mid);

        return mapper.findall(map);
    }

    @Override
    public List<Classes> getclassname(int mid) {
        return mapper.getclassname(mid);
    }

    @Override
    public int deleteByPrimaryKey(Integer classid) {
        return 0;
    }

    @Override
    public int insert(Classes record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Classes record) {
        return 0;
    }

    @Override
    public Classes selectByPrimaryKey(Integer classid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Classes record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Classes record) {
        return 0;
    }
}
