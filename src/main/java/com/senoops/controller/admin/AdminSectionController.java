package com.senoops.controller.admin;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.senoops.controller.MainController;
import com.senoops.model.ECategory;
import com.senoops.model.ECourse;
import com.senoops.model.ESection;
import com.senoops.model.EVideo;
import com.senoops.service.CategoryService;
import com.senoops.service.CourseService;
import com.senoops.service.SectionService;
import com.senoops.service.VideoService;
import com.senoops.utils.JsonResult;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

@Controller

public class AdminSectionController extends MainController {
	@Autowired
	private VideoService videoService;
	@Autowired
	private SectionService sectionService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "admin/section", method = RequestMethod.GET)
	public String goSectionHome(Map<String, Object> map, HttpSession session, HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("获取产品分类列表");
		List<ECategory> categoryList = categoryService.queryAll(null, null);
		map.put("categoryList", JSONArray.parseArray(JSON.toJSONString(categoryList)));
		logger.info("获取课程集合");
		PageUtil pageUtil = new PageUtil(0, 15);
		List<ECourse> courseList = courseService.queryAll(null, pageUtil);
		map.put("courseList", JSONArray.parseArray(JSON.toJSONString(courseList)));
		logger.info("获取课程总数量");
		map.put("courseCount", courseList.size());
		logger.info("获取分页信息");
		pageUtil.setTotal(courseList.size());
		map.put("pageUtil", pageUtil);
		return "admin/sectionManagePage";
	}

	@RequestMapping(value = "admin/section/{course_id}", method = RequestMethod.GET)
	public String goSection(@PathVariable Integer course_id, Map<String, Object> map, HttpSession session,
			HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("session中放课程id");
		session.setAttribute("course_id", course_id);
		logger.info("获取课程信息");
		ECourse course = courseService.queryById(course_id);
		map.put("course", course);
		PageUtil pageUtil = new PageUtil(0, 15);
		logger.info("获取章节集合");
		List<ESection> sectionList = sectionService.selectByCourseId(course_id, null, pageUtil);
		if (sectionList != null) {
			map.put("sectionList", JSONArray.parseArray(JSON.toJSONString(sectionList)));
			logger.info("获取课程总数量");
			map.put("sectionCount", sectionList.size());
			logger.info("获取分页信息");
			pageUtil.setTotal(sectionList.size());
			map.put("pageUtil", pageUtil);
		}
		return "admin/include/sectionDetails";
	}

	@RequestMapping(value = "admin/section/addList", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addSectionList(@RequestBody List<ESection> section, Map<String, Object> map, HttpSession session,
			HttpServletResponse response) throws UnsupportedEncodingException {
		/*
		 * for (ESection eSection : section) { String sectionName =
		 * eSection.getSectionName(); if (sectionName != null) { sectionName =
		 * sectionName.equals("") ? null : URLDecoder.decode(sectionName,
		 * "UTF-8"); } eSection.setSectionName(sectionName); }
		 */
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("添加章节，一个添加{}个", section.size());
		int addList = sectionService.addList(section);
		logger.info("添加成功");
		return new JsonResult(addList);
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addSection(Integer course_id, String section_name, Map<String, Object> map, HttpSession session,
			HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("权限验证");
		checkAdmin(session,response);
		if (section_name != null) {
			section_name = section_name.equals("") ? null : URLDecoder.decode(section_name, "UTF-8");
		}
		logger.info("课程id为{}添加章节", course_id);
		logger.info("添加章节 - ", section_name);
		ESection section = new ESection().setCourseId(course_id).setSectionName(section_name);
		int addList = sectionService.add(section);
		logger.info("添加成功");
		return new JsonResult(addList);
	}

	@RequestMapping(value = "admin/section/update/{section_id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateSection(@PathVariable Integer section_id, Integer course_id, String section_name,
			Map<String, Object> map, HttpSession session, HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("章节id为{}修改信息", section_id);
		ESection section = new ESection().setId(section_id).setCourseId(course_id).setSectionName(section_name);
		logger.info("修改信息为：{}", section.toString());
		int update = sectionService.update(section);
		logger.info("修改信息成功");
		return new JsonResult(update);
	}

	@RequestMapping(value = "admin/section/delete/{section_id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteSection(@PathVariable Integer section_id, Map<String, Object> map, HttpSession session,
			HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("删除章节id为{}的信息", section_id);
		List<EVideo> list = videoService.selectBySectionId(section_id);
		if (list != null && list.size() > 0) {
			logger.warn("此章节下有视频，删除失败");
			throw new RuntimeException();
		} else {
			sectionService.deleteById(section_id);
			logger.info("章节删除成功");
		}
		return new JsonResult();
	}

	@RequestMapping(value = "admin/section/{index}/{count}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult querySection(Integer course_id, String orderBy, @RequestParam(required = false) boolean isDesc,
			@PathVariable Integer index, @PathVariable Integer count, Map<String, Object> map, HttpSession session,
			HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		OrderUtil orderUtil = null;
		if (orderBy != null) {
			logger.info("根据{}排序，是否倒序:{}", orderBy, isDesc);
			orderUtil = new OrderUtil(orderBy, isDesc);
		}
		logger.info("按条件获取第{}页的{}条产品", index + 1, count);
		PageUtil pageUtil = new PageUtil(index, count);
		List<ESection> sectionList = sectionService.selectByCourseId(course_id, orderUtil, pageUtil);
		if (sectionList != null) {
			logger.info("添加章节集合");
			map.put("sectionList", JSONArray.parseArray(JSON.toJSONString(sectionList)));
			logger.info("按条件获取产品总数量");
			map.put("courseCount", sectionList.size());
			logger.info("获取分页信息");
			pageUtil.setTotal(sectionList.size());
			map.put("totalPage", pageUtil.getTotalPage());
			map.put("pageUtil", pageUtil);
		}
		return new JsonResult(map);
	}
}
