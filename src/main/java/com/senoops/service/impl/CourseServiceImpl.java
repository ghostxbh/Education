package com.senoops.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qiniu.common.QiniuException;
import com.senoops.dao.ECourseMapper;
import com.senoops.model.ECourse;
import com.senoops.service.CourseService;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;
import com.senoops.utils.qiniuyun.ImageSub;
import com.senoops.utils.qiniuyun.QiniuUtil;

@Service
public class CourseServiceImpl implements CourseService {
	final static Logger logger = LogManager.getLogger(CourseServiceImpl.class);
	@Autowired
	private ECourseMapper dao;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int delete(Integer courseId) {
		return dao.deleteByPrimaryKey(courseId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int deleteList(Integer[] courseIdList) {
		return dao.delete(courseIdList);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int add(ECourse course) {
		return dao.insertSelective(course);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int addList(List<ECourse> list) {
		return 0;
	}

	@Override
	public List<ECourse> queryByName(String courseName) {		
		return dao.selectByName(courseName);
	}
	
	@Override
	public ECourse queryById(Integer courseId) {
		return dao.selectByPrimaryKey(courseId);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public int update(ECourse record) {
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<ECourse> queryAll(OrderUtil orderUtil, PageUtil pageUtil) {
		return dao.selectAll(orderUtil, pageUtil);
	}

	@Override
	public List<ECourse> queryByCategoryId(Integer categoryId, OrderUtil orderUtil, PageUtil pageUtil) {
		return dao.selectByCategoryId(categoryId, orderUtil, pageUtil);
	}

	@Override
	public List<ECourse> query(ECourse course, OrderUtil orderUtil, PageUtil pageUtil) {
		return dao.select(course, orderUtil, pageUtil);
	}

	@Override
	public List<ECourse> queryAllByParam(ECourse course, Double hightPrice, Double lowestPrice, PageUtil pageUtil,
			OrderUtil orderUtil) {
		return dao.selectByParam(course, hightPrice, lowestPrice, orderUtil, pageUtil);
	}

	@Override
	public void deleteQiniuImage(String url) {
		try {
			QiniuUtil.delete(ImageSub.subImage(url), QiniuUtil.COLLEGE_IMAGES);
			logger.info("七牛图片url为{}删除成功", url);
		} catch (QiniuException e) {
			logger.warn("七牛图片删除异常");
			throw new RuntimeException(e);
		}
	}	
}
