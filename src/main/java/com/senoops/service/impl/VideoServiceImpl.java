package com.senoops.service.impl;

import com.senoops.dao.EVideoMapper;
import com.senoops.model.EVideo;
import com.senoops.service.VideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private EVideoMapper dao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int deleteById(Integer id) {
		return dao.deleteByPrimaryKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int delete(Integer[] videoIdList) {
		return dao.delete(videoIdList);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int add(EVideo record) {
		return dao.insertSelective(record);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int addList(List<EVideo> video) {
		return dao.insertList(video);
	}

	@Override
	public EVideo selectById(Integer id) {
		return dao.selectByPrimaryKey(id);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int update(EVideo record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<EVideo> selectBySectionId(Integer sectionId) {
		return dao.selectBySectionId(sectionId);
	}

}
