package com.senoops.controller.fore;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.senoops.controller.MainController;
import com.senoops.model.ECategory;
import com.senoops.model.ECourse;
import com.senoops.model.ESection;
import com.senoops.service.CategoryService;
import com.senoops.service.CourseService;
import com.senoops.service.SectionService;


@Controller
public class ForeHomeController extends MainController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SectionService sectionService;

	@RequestMapping(value = "/",method=RequestMethod.GET)
	public String goHome(Map<String, Object> map) {
		logger.info("获取所有的课程分类");
		List<ECategory> categoryList = categoryService.queryAll(null, null);
		if (categoryList!=null&&categoryList.size()>0) {
			map.put("categoryList", categoryList);	
			Map<Integer,List<ECourse>> courseMap = new HashMap<>();
			for (ECategory category : categoryList) {
				logger.info("获取-{}-下的所有课程",category.getName());
				List<ECourse> course = courseService.queryByCategoryId(category.getId(),null,null);
				if (course!=null&&course.size()>0) {
					logger.info("{} - 下的有{}节课程",category.getName(),course.size());
					courseMap.put(category.getId(), course);
				}
			}
			map.put("courseMap", courseMap);
		}		
		return "fore/home";
	}
	
	@RequestMapping(value = "/fore/{course_id}",method=RequestMethod.GET)
	public String goDetail(@PathVariable Integer course_id,Map<String, Object> map) {
		ECourse course = courseService.queryById(course_id);
		logger.info("获取课程信息{}",course.toString());
		map.put("course", course);
		List<ESection> sectionList = sectionService.selectByCourseId(course_id, null, null);
		map.put("sectionList", sectionList);
		return "fore/coursedetail";
	}	
	
}
