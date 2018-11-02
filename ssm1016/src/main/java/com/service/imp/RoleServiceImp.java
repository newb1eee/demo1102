package com.service.imp;

import com.bean.Role;
import com.dao.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Integer roleid) {
        return 0;
    }

    @Override
    public int insert(Role record) {
        return 0;
    }

    @Override
    public int insertSelective(Role record) {
        return 0;
    }

    @Override
    public Role selectByPrimaryKey(Integer roleid) {
        return roleMapper.selectByPrimaryKey(roleid);
    }

    @Override
    public int updateByPrimaryKeySelective(Role record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Role record) {
        return 0;
    }

    @Override
    public PageInfo getall(int pageindex, int size) {
        PageHelper.startPage(pageindex,size);
        List list = roleMapper.getall();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public int addrole(Role r, int[] menus) {
        //1.新增到角色
        int k= roleMapper.insert(r);
        System.out.println("新增数据的id="+r.getRoleid());
        //2.新增中间表
        Map map=new HashMap();
        map.put("roleid",r.getRoleid());
        map.put("menuids",menus);
        int k2=roleMapper.insertMiddle(map);
        return k2;
    }

    @Override
    public int update(Role role, int[] ids) {
        int k=0;
        try {
            //修改角色表
            roleMapper.updateByPrimaryKey(role);
            //修改中间表
            roleMapper.deletemiddlebyrid(role.getRoleid());

            Map map = new HashMap();
            map.put("roleid",role.getRoleid());
            map.put("menuids",ids);
            roleMapper.insertMiddle(map);
            k=1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return k;
    }

    @Override
    public int deleterole(int roleid) {
        //先判断该角色下是否有用户
        int count = roleMapper.findusercountbyroleid(roleid);
        if(count>0){
            return 0;
        }
        //先删除中间表，在删除角色表
        roleMapper.deletemiddlebyrid(roleid);
        //删除角色
        roleMapper.deleteByPrimaryKey(roleid);

        return 1;
    }
}
