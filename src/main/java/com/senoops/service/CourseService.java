package com.senoops.service;

import java.util.List;

import com.senoops.model.ECourse;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

public interface CourseService {
	int delete(Integer courseId);

	int deleteList(Integer[] courseIdList);

	int add(ECourse course);

	int addList(List<ECourse> list);

	ECourse queryById(Integer courseId);

	int update(ECourse record);

	List<ECourse> queryAll(OrderUtil orderUtil, PageUtil pageUtil);

	List<ECourse> queryByCategoryId(Integer categoryId, OrderUtil orderUtil, PageUtil pageUtil);

	List<ECourse> query(ECourse course, OrderUtil orderUtil, PageUtil pageUtil);

	List<ECourse> queryAllByParam(ECourse course, Double hightPrice, Double lowestPrice, PageUtil pageUtil,
			OrderUtil orderUtil);

	void deleteQiniuImage(String url);
	
	List<ECourse> queryByName(String courseName);
}
