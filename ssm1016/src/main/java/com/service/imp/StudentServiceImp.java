package com.service.imp;

import com.bean.Student;
import com.dao.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class StudentServiceImp implements StudentService {


    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int deleteByPrimaryKey(Integer studentid) {
        return 0;
    }

    @Override
    public int insert(Student record) {
        return studentMapper.insert(record);
    }

    @Override
    public int insertSelective(Student record) {
        return 0;
    }

    @Override
    public Student selectByPrimaryKey(Integer studentid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Student record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return 0;
    }

    @Override
    public PageInfo getall(String stuname,String studentno,Integer stusex,String examsubject, int pageindex, int size) {
        Map map = new HashMap();
        map.put("sname",stuname);
        map.put("sno",studentno);
        map.put("ssex",stusex);
        map.put("examsubject",examsubject);

        PageHelper.startPage(pageindex,size);

        List list = studentMapper.getallstudent(map);

        PageInfo pi = new PageInfo(list);

        return pi;
    }
}
