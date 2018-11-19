package com.senoops.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senoops.dao.CourseMapper;
import com.senoops.model.Course;
import com.senoops.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper courseMapper;

	
	public Course selectByPrimaryKey(Integer courseid) {
		Course course = courseMapper.selectByPrimaryKey(courseid);
		return course;
	}

	
	public int deleteByPrimaryKey(Integer courseid) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insert(Course record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int insertSelective(Course record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKeySelective(Course record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateByPrimaryKey(Course record) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public List<Course> selectAll() {
		return courseMapper.selectAll();
	}


	@Override
	public Course selectOrderById(int id) {		
		return courseMapper.selectOrderById(id);
	}

}
