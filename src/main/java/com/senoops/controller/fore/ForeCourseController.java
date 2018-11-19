package com.senoops.controller.fore;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.senoops.controller.MainController;
import com.senoops.model.ECourse;
import com.senoops.service.CourseService;


@Controller
@RequestMapping("/course")
public class ForeCourseController extends MainController {	
	@Autowired
	private CourseService courseService;
	

	@RequestMapping(value = "/search",method=RequestMethod.GET)
	public String search(String course_name,Map<String, Object> map) {
		map.put("searchName", course_name);
		logger.info("获取搜索{}内容",course_name);
		List<ECourse> courseList = courseService.queryByName(course_name);
		if (courseList!=null&&courseList.size()>0) {
			logger.info("搜索{}内容共有{}条数据",course_name,courseList.size());
			map.put("courseList", courseList);
		}
		return "fore/searchCourse";
	}
	
}
