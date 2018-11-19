package com.senoops.service.impl;

import com.senoops.dao.ERoleMapper;
import com.senoops.model.ERole;
import com.senoops.service.RoleService;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private ERoleMapper dao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int insert(ERole role) {
		return dao.insertSelective(role);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int update(ERole role) {
		return dao.updateByPrimaryKeySelective(role);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int delete(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public ERole queryByUserId(Integer userId) {
		return dao.selectByUserId(userId);
	}

	@Override
	public ERole queryById(Integer id) {		
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<ERole> queryByParam(ERole role, OrderUtil orderUtil, PageUtil pageUtil) {		
		return dao.select(role, orderUtil, pageUtil);
	}
}
