package com.senoops.controller.admin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.senoops.controller.MainController;
import com.senoops.model.ECategory;
import com.senoops.model.ECourse;
import com.senoops.model.ESection;
import com.senoops.service.CategoryService;
import com.senoops.service.CourseService;
import com.senoops.service.LastIDService;
import com.senoops.service.SectionService;
import com.senoops.utils.JsonResult;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;
import com.senoops.utils.qiniuyun.QiniuUtil;
import com.senoops.utils.qiniuyun.UpResult;

@Controller
public class AdminCourseController extends MainController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private LastIDService lastIDService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SectionService sectionService;

	@RequestMapping(value = "admin/course", method = RequestMethod.GET)
	public String goCourseHome(Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("获取产品分类列表");
		PageUtil pageUtil = new PageUtil(0, 15);
		List<ECategory> categoryList = categoryService.queryAll(null, pageUtil);
		map.put("categoryList", categoryList);
		logger.info("获取课程集合");
		List<ECourse> courseList = courseService.queryAll(null, null);
		map.put("courseList", courseList);
		logger.info("获取课程总数量");
		map.put("courseCount", courseList.size());
		logger.info("获取分页信息");
		pageUtil.setTotal(courseList.size());
		map.put("pageUtil", pageUtil);
		return "admin/courseManagePage";
	}

	// 转到后台管理-产品详情页-ajax
	@RequestMapping(value = "admin/course/{pid}", method = RequestMethod.GET)
	public String goToDetailsPage(Map<String, Object> map, @PathVariable Integer pid,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("获取course_id为{}的课程信息", pid);
		ECourse course = courseService.queryById(pid);
		map.put("course", course);
		logger.info("获取分类列表");
		List<ECategory> categoryList = categoryService.queryAll(null, null);
		map.put("categoryList", categoryList);
		logger.info("转到后台管理-产品详情页-ajax方式");
		return "admin/include/courseDetails";
	}

	// 按条件查询产品-ajax
	@ResponseBody
	@RequestMapping(value = "admin/course/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public JsonResult getProductBySearch(@RequestParam(required = false) String course_name, Integer category_id,
			@RequestParam(required = false) Double hight_price, @RequestParam(required = false) Double lowest_price,
			String course_status, String orderBy, String course_free, @RequestParam(required = false) String lecturer,
			@RequestParam(required = false) boolean isDesc, @PathVariable Integer index, @PathVariable Integer count,
			Map<String, Object> map,HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("权限验证");
		checkAdmin(session,response);
		// 移除不必要条件
		// 如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
		if (category_id != null && category_id == 0) {
			category_id = null;
		}
		if (course_name != null) {
			course_name = course_name.equals("") ? null : URLDecoder.decode(course_name, "UTF-8");
		}
		if (lecturer != null) {
			lecturer = lecturer.equals("") ? null : URLDecoder.decode(lecturer, "UTF-8");
		}
		if (orderBy != null && orderBy.equals("")) {
			orderBy = null;
		}
		// 封装查询条件
		ECourse course = new ECourse().setCourseName(course_name).setCategoryId(category_id).setLecturer(lecturer)
				.setStatus(course_status).setFree(course_free);
		logger.info("查询课程条件：{}", course.toString());
		OrderUtil orderUtil = null;
		if (orderBy != null) {
			logger.info("根据{}排序，是否倒序:{}", orderBy, isDesc);
			orderUtil = new OrderUtil(orderBy, isDesc);
		}

		logger.info("按条件获取第{}页的{}条产品", index + 1, count);
		PageUtil pageUtil = new PageUtil(index, count);
		List<ECourse> courseList = courseService.queryAllByParam(course, hight_price, lowest_price, pageUtil,
				orderUtil);
		map.put("courseList", JSONArray.parseArray(JSON.toJSONString(courseList)));
		logger.info("按条件获取产品总数量");
		map.put("courseCount", courseList.size());
		logger.info("获取分页信息");
		pageUtil.setTotal(courseList.size());
		map.put("totalPage", pageUtil.getTotalPage());
		map.put("pageUtil", pageUtil);
		return new JsonResult(map);
	}

	// 转到后台管理-产品添加页-ajax
	@RequestMapping(value = "admin/course/new", method = RequestMethod.GET)
	public String goToAddPage(Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("获取分类列表");
		List<ECategory> categoryList = categoryService.queryAll(null, null);
		map.put("categoryList", categoryList);
		logger.info("转到后台管理-产品添加页-ajax方式");
		return "admin/include/courseDetails";
	}

	// 添加产品信息-ajax
	@ResponseBody
	@RequestMapping(value = "admin/course", method = RequestMethod.POST)
	public JsonResult addProduct(String course_name, String course_info, Integer category_id, Double course_price,
			String course_status, String course_free, String period, String lecturer, Integer sales_volume,
			@RequestParam(required = false) String thum_photo_url, @RequestParam(required = false) String photo_url,
			@RequestParam(required = false) String lecturer_photo_url, Map<String, Object> map,HttpSession session,HttpServletResponse response)
			throws UnsupportedEncodingException {
		logger.info("权限验证");
		checkAdmin(session,response);
		// 如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
		if (course_name != null) {
			course_name = course_name.equals("") ? null : URLDecoder.decode(course_name, "UTF-8");
		}
		if (course_info != null) {
			course_info = course_info.equals("") ? null : URLDecoder.decode(course_info, "UTF-8");
		}
		if (lecturer != null) {
			lecturer = lecturer.equals("") ? null : URLDecoder.decode(lecturer, "UTF-8");
		}
		if (period != null) {
			period = period.equals("") ? null : URLDecoder.decode(period, "UTF-8");
		}
		ECourse course = new ECourse().setCourseName(course_name).setCourseInfo(course_info).setCategoryId(category_id)
				.setStatus(course_status).setFree(course_free).setPrice(course_price).setCreatePerson("管理员")
				.setPeriod(period).setLecturer(lecturer).setSalesVolume(sales_volume).setThumPhotoUrl(thum_photo_url)
				.setPhotoUrl(photo_url).setLecturerPortraitUrl(lecturer_photo_url);
		logger.info("整合产品信息{}", course.toString());
		int add = courseService.add(course);
		if (add > 0) {
			int lastID = lastIDService.selectLastID();
			logger.info("添加成功，课程id:{}", lastID);
			map.put("course", courseService.queryById(lastID));
		} else {
			logger.warn("课程添加异常");
			throw new RuntimeException();
		}
		return new JsonResult(map);
	}

	// 更新产品信息-ajax
	@ResponseBody
	@RequestMapping(value = "admin/course/{course_id}", method = RequestMethod.POST)
	public JsonResult updateProduct(String course_name, String course_info, Integer category_id, Double course_price,
			String course_status, String course_free, String period, String lecturer, Integer sales_volume,
			@RequestParam(required = false) String thum_photo_url, @RequestParam(required = false) String photo_url,
			@RequestParam(required = false) String lecturer_photo_url, Map<String, Object> map,
			@PathVariable Integer course_id,HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("权限验证");
		checkAdmin(session,response);
		// 如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
		if (course_name != null) {
			course_name = course_name.equals("") ? null : URLDecoder.decode(course_name, "UTF-8");
		}
		if (course_info != null) {
			course_info = course_info.equals("") ? null : URLDecoder.decode(course_info, "UTF-8");
		}
		if (lecturer != null) {
			lecturer = lecturer.equals("") ? null : URLDecoder.decode(lecturer, "UTF-8");
		}
		if (period != null) {
			period = period.equals("") ? null : URLDecoder.decode(period, "UTF-8");
		}
		/*BigDecimal price = new BigDecimal(course_price);*/
		
		ECourse course = new ECourse().setId(course_id).setCourseName(course_name).setCourseInfo(course_info)
				.setCategoryId(category_id).setStatus(course_status).setFree(course_free).setPrice(course_price)
				.setCreatePerson("管理员").setPeriod(period).setLecturer(lecturer).setSalesVolume(sales_volume)
				.setThumPhotoUrl(thum_photo_url).setPhotoUrl(photo_url).setLecturerPortraitUrl(lecturer_photo_url);
		logger.info("整合产品信息{}", course.toString());
		int update = courseService.update(course);
		if (update > 0) {
			logger.info("课程id：{}-信息修改成功", course.getId());
		} else {
			logger.warn("课程信息修改异常，事务回滚");
			throw new RuntimeException();
		}
		return new JsonResult();
	}

	// 删除商品
	@ResponseBody
	@RequestMapping(value = "admin/course/delete/{arr}", method = RequestMethod.POST)
	public JsonResult deleteProduct(@PathVariable("arr") Integer[] course_id_list,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		JsonResult result = new JsonResult();
		int delete = 0;
		logger.info("删除:用户id数组：{}", course_id_list.toString());
		for (int i = 0; i < course_id_list.length; i++) {
			List<ESection> sections = sectionService.selectByCourseId(course_id_list[i],null,null);
			if (sections != null&&sections.size()>0) {
				logger.warn("此课程id：{} - 下有章节/视频，删除后即可删除课程", course_id_list[i]);
				result.setMessage("此课程id：{" + course_id_list[i] + "} - 下有章节/视频，删除后即可删除课程");
				throw new RuntimeException();
			} else {
				ECourse course = courseService.queryById(course_id_list[i]);
				if (course.getThumPhotoUrl()!=null&&course.getThumPhotoUrl()!="") {
					courseService.deleteQiniuImage(course.getThumPhotoUrl());
				}
				if (course.getPhotoUrl()!=null&&course.getPhotoUrl()!="") {
					courseService.deleteQiniuImage(course.getPhotoUrl());
				}
				if (course.getLecturerPortraitUrl()!=null&&course.getLecturerPortraitUrl()!="") {
					courseService.deleteQiniuImage(course.getLecturerPortraitUrl());
				}
				delete += courseService.delete(course_id_list[i]);
			}
		}
		result.setData(delete);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "admin/deletePhoto", method = RequestMethod.GET)
	public JsonResult deletePhoto(String photo_url,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		courseService.deleteQiniuImage(photo_url);
		return new JsonResult();
	}

	// 按ID删除产品图片并返回最新结果-ajax
	@ResponseBody
	@RequestMapping(value = "admin/deletePhotoById", method = RequestMethod.DELETE)
	public JsonResult deleteProductImageById(Integer course_id, String image_type,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("获取course_id为{}的产品图片信息", course_id);
		ECourse course = courseService.queryById(course_id);
		logger.info("删除产品图片");
		switch (image_type) {
		case "thum":
			courseService.deleteQiniuImage(course.getThumPhotoUrl());
			course.setThumPhotoUrl("");
			break;
		case "detail":
			courseService.deleteQiniuImage(course.getPhotoUrl());
			course.setPhotoUrl("");
			break;
		case "lecturer":
			courseService.deleteQiniuImage(course.getLecturerPortraitUrl());
			course.setLecturerPortraitUrl("");
			break;
		}
		int update = courseService.update(course);
		if (update > 0) {
			logger.info("删除图片成功！");
		} else {
			logger.warn("删除图片失败！事务回滚");
			throw new RuntimeException();
		}
		return new JsonResult();
	}

	// 上传产品图片-ajax
	@ResponseBody
	@RequestMapping(value = "admin/uploadCourseImage", method = RequestMethod.POST)
	public JsonResult uploadProductImage(MultipartFile file, Map<String, Object> map,HttpSession session,HttpServletResponse response) throws IOException {
		/*
		 * String originalFileName = file.getOriginalFilename();
		 * logger.info("获取图片原始文件名：{}", originalFileName); String extension =
		 * originalFileName.substring(originalFileName.lastIndexOf('.')); String
		 * filePath; String fileName = UUID.randomUUID() + extension; String
		 * realPath =
		 * "D:\\idea\\IDEA_workspace\\bookstore\\src\\main\\webapp\\"; if
		 * (imageType.equals("single")) { filePath = realPath +
		 * "res/images/item/productSinglePicture/" + fileName; } else { filePath
		 * = realPath + "res/images/item/productDetailsPicture/" + fileName; }
		 * JSONObject object = new JSONObject();
		 */
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("文件上传中...");
		UpResult upload = QiniuUtil.upload(file.getInputStream(), file.getOriginalFilename(), QiniuUtil.COLLEGE_IMAGES);
		if (upload != null) {
			logger.info("七牛云路径：", upload.zoneName + upload.fileName);
			logger.info("文件上传完成");
			String fileUrl = QiniuUtil.getFileUrl(upload.fileName, QiniuUtil.COLLEGE_IMAGES_DOMAIN);
			map.put("fileUrl", fileUrl);
		} else {
			throw new RuntimeException("文件上传失败");
		}

		return new JsonResult(map);
	}
}
