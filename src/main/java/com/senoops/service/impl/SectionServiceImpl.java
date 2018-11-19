package com.senoops.service.impl;

import com.senoops.dao.ESectionMapper;
import com.senoops.model.ESection;
import com.senoops.service.SectionService;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private ESectionMapper dao;
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int deleteById(Integer id) {
        return dao.deleteByPrimaryKey(id);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int delete(Integer[] sectionIdList) {
        return dao.delete(sectionIdList);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int add(ESection record) {
        return dao.insertSelective(record);
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int addList(List<ESection> list) {
        return dao.insertList(list);
    }

    @Override
    public ESection selectById(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(ESection record) {
        return dao.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<ESection> selectByCourseId(Integer courseId,OrderUtil orderUtil,PageUtil pageUtil) {
        return dao.selectByCourseId(courseId,orderUtil,pageUtil);
    }
}
