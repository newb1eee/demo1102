package com.service.imp;

import com.bean.Menu;
import com.dao.MenuMapper;
import com.dao.UserTbMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.MenuService;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.ArrayList;
import java.util.List;
@Service
public class MenuServiceImp implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private UserTbMapper userTbMapper;
    @Override
    public int deleteByPrimaryKey(Integer menuid) {
        return menuMapper.deleteByPrimaryKey(menuid);
    }

    @Override
    public int insert(Menu record) {
        return menuMapper.insert(record);
    }

    @Override
    public int insertSelective(Menu record) {
        return 0;
    }

    @Override
    public Menu selectByPrimaryKey(Integer menuid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Menu record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Menu record) {
        return 0;
    }

    @Override
    public List<Menu> findbyroleid(int roleid) {
        return menuMapper.findbyroleid(roleid);
    }

    @Override
    public List<Menu> getallmenu() {
        //调取dao层查询方法
        List<Menu> list = menuMapper.getallmenu();
        //分级
        List newlist = new ArrayList();
        for (Menu menu:list) {
            if(menu.getUpmenuid()==-1){
                List menus = new ArrayList();
                for (Menu menu2:list) {
                    if(menu2.getUpmenuid()==menu.getMenuid()){
                        menus.add(menu2);

                    }
                }
                menu.setSeconds(menus);
                newlist.add(menu);
            }
        }
        return newlist;
    }

    @Override
    public PageInfo getall2(int index,int size,int upmenid) {
        PageHelper.startPage(index,size);
        List<Menu> list = menuMapper.getallmenu();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List getall3() {
        return menuMapper.getall3(-1);
    }


    @Autowired
    private DataSourceTransactionManager tx;


    @Override
    @Transactional //事务加在service层，成功都成功，失败都失败。切面方式管理实务。
    public int deletebyids(String[] ids) throws Exception {

        DefaultTransactionDefinition ddf = new DefaultTransactionDefinition();
        TransactionStatus status = tx.getTransaction(ddf);


        try {
            //1.将删除的菜单分类
            List<Integer> first = new ArrayList();//一级目录
            List second = new ArrayList();//二级目录
            for (String id: ids) {
                int index = id.indexOf("-");//1-2 2-(-1) 3-(-1)
                int upid = Integer.parseInt(id.substring(index+1));
                int mid = Integer.parseInt(id.substring(0,index));
                if(upid==-1){
                    first.add(mid);
                }else{
                    second.add(mid);
                }
            }
            //2.先删除2级菜单再判断1级菜单
            if(first.size()==ids.length){//全是一级菜单
                //对应的一级菜单中二级菜单全都不存在，可以删除
                for (Integer id: first){
                    List list = menuMapper.getall3(id);
                    if(list.size()>0){
                        //不能删除

                        throw new RuntimeException("aaa");//抛出运行异常
                    }else {
                        //删除
                        menuMapper.deleteByPrimaryKey(id);
                    }
                }
            }else {
                //包含二级菜单
                if(second.size()==ids.length){
                    //全是二级菜单
                    menuMapper.deletebyids(second);
                }else {
                    //有一级菜单有二级菜单
                    //先删除2级菜单再判断1级菜单
                    menuMapper.deletebyids(second);
                    //继续判断一级菜单是否有子菜单
                    for (Integer mid : first) {
                       List list =  menuMapper.getall3(mid);
                       if(list.size()>0){
                           //有子菜单不能删除
                           throw new RuntimeException("aaa");
                       }else {
                           menuMapper.deleteByPrimaryKey(mid);
                       }

                    }
                }
            }
            tx.commit(status);
        } catch (RuntimeException e) {
            //e.printStackTrace();
            tx.rollback(status);
        }
        return 0;
    }


}
