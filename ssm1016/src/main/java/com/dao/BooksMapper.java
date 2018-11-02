package com.dao;

import com.bean.Books;

import java.util.List;
import java.util.Map;

public interface BooksMapper {
    int deleteByPrimaryKey(Integer bookid);

    int insert(Books record);

    int insertSelective(Books record);

    Books selectByPrimaryKey(Integer bookid);

    int updateByPrimaryKeySelective(Books record);

    int updateByPrimaryKey(Books record);

}