package com.senoops.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senoops.dao.OrderMapper;
import com.senoops.model.Order;
import com.senoops.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	
	public Order searchById(Integer id){
		return orderMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	public int insert(Order order) {
		return orderMapper.insert(order);
	}
	
	@Transactional
	public int update(Order order) {
		return orderMapper.updateByPrimaryKey(order);
	}
	
	@Transactional
	public int delete(Integer id){
		return orderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<Order> queryAll() {		
		return orderMapper.queryAll();
	}
	
	

}