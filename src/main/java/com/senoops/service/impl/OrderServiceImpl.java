package com.senoops.service.impl;

import com.senoops.dao.EOrderMapper;
import com.senoops.model.EOrder;
import com.senoops.service.OrderService;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private EOrderMapper dao;
	
    @Override
    public EOrder searchById(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int insert(EOrder order) {
        return dao.insertSelective(order);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(EOrder order) {
        return dao.updateByPrimaryKeySelective(order);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteList(Integer[] idList) {
        return dao.delete(idList);
    }

    @Override
    public List<EOrder> queryByUserId(Integer userId, OrderUtil orderUtil, PageUtil pageUtil) {
        return dao.selectByUserId(userId, orderUtil, pageUtil);
    }

    @Override
    public List<EOrder> queryAll(OrderUtil orderUtil, PageUtil pageUtil) {
        return dao.selectAll(orderUtil, pageUtil);
    }

	@Override
	public List<EOrder> queryByParam(EOrder order, OrderUtil orderUtil, PageUtil pageUtil) {		
		return dao.selectByParam(order, orderUtil, pageUtil);
	}
}