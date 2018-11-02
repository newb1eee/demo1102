package com.service.imp;

import com.bean.Information;
import com.bean.Infotype;
import com.dao.InformationMapper;
import com.dao.InfotypeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.InformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InformationServiceImp implements InformationService {

    @Autowired
    private InformationMapper informationMapper;
    @Autowired
    private InfotypeMapper infotypeMapper;

    @Override
    public int deleteByPrimaryKey(Integer informationid) {
        return 0;
    }

    @Override
    public int insert(Information record) {
        return informationMapper.insert(record);
    }

    @Override
    public int insertSelective(Information record) {
        return 0;
    }

    @Override
    public Information selectByPrimaryKey(Integer informationid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Information record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Information record) {
        return 0;
    }

    @Override
    public PageInfo getallinfo(Integer typeid, String informationname, int pageindex, int size) {
        Map map = new HashMap();
        map.put("typeid",typeid);
        map.put("iname",informationname); //iname书名，模糊查对象
        PageHelper.startPage(pageindex,size);
        List list = informationMapper.getallinfo(map);
        PageInfo pi = new PageInfo(list);
        return pi;
    }

    @Override
    public List<Infotype> getinformationname(Integer typeid) {
        return infotypeMapper.getinformationname(typeid);
    }
}
