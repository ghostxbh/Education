package com.senoops.service.impl;

import com.senoops.dao.ECategoryMapper;
import com.senoops.model.ECategory;
import com.senoops.service.CategoryService;
import com.senoops.utils.PageUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    final static Logger logger = LogManager.getLogger(CategoryServiceImpl.class);
    @Autowired
    private ECategoryMapper dao;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int add(ECategory category) {
        return dao.insertSelective(category);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int delete(Integer categoryId) {
        return dao.deleteByPrimaryKey(categoryId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public int update(ECategory category) {
        return dao.updateByPrimaryKeySelective(category);
    }

    @Override
    public ECategory query(Integer categoryId) {
        return dao.selectByPrimaryKey(categoryId);
    }

    @Override
    public List<ECategory> queryAll(String name, PageUtil pageUtil) {
        return dao.selectAll(name, pageUtil);
    }
}
