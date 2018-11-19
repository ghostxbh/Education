package com.senoops.controller.fore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.senoops.controller.MainController;
import com.senoops.model.ECourse;
import com.senoops.model.ESection;
import com.senoops.model.EVideo;
import com.senoops.service.CourseService;
import com.senoops.service.SectionService;
import com.senoops.service.VideoService;

@Controller
public class ForeVideoController extends MainController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "/fore/goLearn/{course_id}", method = RequestMethod.GET)
	private String goLearn(@PathVariable Integer course_id, Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkUser(session,response);
		
		ECourse course = courseService.queryById(course_id);
		map.put("course", course);
		List<ESection> sectionList = sectionService.selectByCourseId(course_id, null, null);
		if (sectionList != null && sectionList.size() > 0) {
			logger.info("获取章节列表，共{}条", sectionList.size());
			map.put("sectionList", sectionList);
			Map<Integer, List<EVideo>> videoMap = new HashMap<>();
			int videoCount = 0;			
			for (ESection section : sectionList) {				
				List<EVideo> videoList = videoService.selectBySectionId(section.getId());
				videoCount += videoList.size();
				logger.info("章节ID-{}下有视频列表{}条", section.getId(), videoList.size());
				logger.info("以章节id为key，放入视频集合");
				videoMap.put(section.getId(), videoList);
			}
			logger.info("课程为{}下共有{}个视频", course.getCourseName(), videoCount);
			map.put("firstUrl", videoMap.get(sectionList.get(0).getId()).get(0).getVideoUrl());
			map.put("firstName", videoMap.get(sectionList.get(0).getId()).get(0).getVideoName());
			map.put("videoMap", videoMap);
		}
		return "fore/videoPlay";
	}

}
