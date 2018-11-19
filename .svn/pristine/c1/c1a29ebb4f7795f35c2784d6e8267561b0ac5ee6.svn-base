package com.senoops.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.senoops.model.Order;

public interface OrderMapper {
	
    int deleteByPrimaryKey(@Param(value="id")Integer id);

    int insert(Order order);

    Order selectByPrimaryKey(@Param(value="id")Integer id);

    int updateByPrimaryKey(Order order);

	List<Order> queryAll();
	
}