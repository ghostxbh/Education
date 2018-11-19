package com.senoops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senoops.dao.VedioMapper;
import com.senoops.model.Section;
import com.senoops.service.VedioService;

@Service
public class VedioServiceImpl implements VedioService {

	@Autowired
	private VedioMapper vedioMapper;
	
	@Override
	public List<Section> getSections(Integer id) {
		List<Section> list = vedioMapper.getSections(id);
		return list;
	}

	
	
}
