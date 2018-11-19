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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.senoops.controller.MainController;

import com.senoops.model.ESection;
import com.senoops.model.EVideo;
import com.senoops.service.LastIDService;
import com.senoops.service.SectionService;
import com.senoops.service.VideoService;
import com.senoops.utils.JsonResult;
import com.senoops.utils.PageUtil;


@Controller
public class AdminVideoController extends MainController {
	@Autowired
	private SectionService sectionService;
	@Autowired
	private VideoService videoService;
	@Autowired
	private LastIDService lastIDService;

	@RequestMapping(value = "admin/video/{section_id}", method = RequestMethod.GET)
	public String goSection(@PathVariable Integer section_id,Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		
		logger.info("获取session的课程id");
		Integer course_id = (Integer)session.getAttribute("course_id");
		map.put("course_id",course_id);
		logger.info("获取课程信息");
		ESection section = sectionService.selectById(section_id);
		map.put("section", section);		
		PageUtil pageUtil = new PageUtil(0, 15);
		logger.info("获取章节集合");
		List<EVideo> list = videoService.selectBySectionId(section_id);
		if (list!=null) {
			map.put("videoList", JSONArray.parseArray(JSON.toJSONString(list)));
			logger.info("获取课程总数量");
			map.put("videoCount", list.size());
			logger.info("获取分页信息");
			pageUtil.setTotal(list.size());
			map.put("pageUtil", pageUtil);
		}		
		return "admin/include/videoDetails";
	}
	
	@RequestMapping(value = "admin/video/addList", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addSectionList(@RequestBody List<EVideo> video, Map<String, Object> map,HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException {		
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("添加章节，一个添加{}个", video.size());
		int addList = videoService.addList(video);
		logger.info("添加成功");
		return new JsonResult(addList);
	}
	
	@RequestMapping(value = "admin/video/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addSectionList(Integer section_id, String video_name, String video_url, Map<String, Object> map,HttpSession session,HttpServletResponse response)
			throws UnsupportedEncodingException {
		logger.info("权限验证");
		checkAdmin(session,response);
		if (video_name != null) {
			video_name = video_name.equals("") ? null : URLDecoder.decode(video_name, "UTF-8");
		}
		EVideo video = new EVideo().setSectionId(section_id).setVideoName(video_name).setVideoUrl(video_url);
		logger.info("视频信息{}", video.toString());
		videoService.add(video);
		logger.info("添加成功");
		int selectLastID = lastIDService.selectLastID();
		EVideo video2 = videoService.selectById(selectLastID);
		return new JsonResult(video2);
	}

	@RequestMapping(value = "admin/video/update/{video_id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateSection(@PathVariable Integer video_id, Integer section_id, String video_name,
			String video_url, Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("章节id为{}修改信息", section_id);
		EVideo video = new EVideo().setId(video_id).setSectionId(section_id).setVideoName(video_name).setVideoUrl(video_url);
		logger.info("修改信息为：{}", video.toString());
		videoService.update(video);
		logger.info("修改信息成功");
		return new JsonResult();
	}


	@RequestMapping(value = "admin/video/delete/{video_id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult deleteSection(@PathVariable Integer video_id, Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("删除章节id为{}的信息", video_id);		
		videoService.deleteById(video_id);
		logger.info("视频删除成功");		
		return new JsonResult();
	}

	/*@RequestMapping(value = "{index}/{count}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult querySection(Integer course_id, String orderBy, @RequestParam(required = false) boolean isDesc,
			@PathVariable Integer index, @PathVariable Integer count, Map<String, Object> map) {
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
	}*/
}
