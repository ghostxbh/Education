package com.senoops.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import com.senoops.service.CategoryService;
import com.senoops.service.CourseService;
import com.senoops.service.LastIDService;
import com.senoops.utils.JsonResult;
import com.senoops.utils.PageUtil;

@Controller
public class AdminCategoryController extends MainController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LastIDService lastIDService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "admin/category", method = RequestMethod.GET)
	public String home(Map<String, Object> map, HttpSession session, HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session, response);
		logger.info("获取前10条分类列表");
		PageUtil pageUtil = new PageUtil(0, 10);
		List<ECategory> categoryList = categoryService.queryAll(null, pageUtil);
		map.put("categoryList", categoryList);
		logger.info("获取分类总数量");
		map.put("categoryCount", categoryList.size());
		logger.info("获取分页信息");
		pageUtil.setTotal(categoryList.size());
		map.put("pageUtil", pageUtil);
		return "admin/categoryManagePage";
	}

	// 转到后台管理-分类详情页-ajax
	@RequestMapping(value = "admin/category/{cid}", method = RequestMethod.GET)
	public String goToDetailsPage(Map<String, Object> map, @PathVariable Integer cid, HttpSession session,
			HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session, response);
		logger.info("获取category_id为{}的分类信息", cid);
		ECategory category = categoryService.query(cid);
		map.put("category", category);
		logger.info("转到后台管理-分类详情页-ajax方式");
		return "admin/include/categoryDetails";
	}

	// 转到后台管理-分类添加页-ajax
	@RequestMapping(value = "admin/category/new", method = RequestMethod.GET)
	public String goToAddPage(HttpSession session, HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session, response);
		logger.info("转到后台管理-分类添加页-ajax方式");
		return "admin/include/categoryDetails";
	}

	// 添加分类信息-ajax
	@ResponseBody
	@RequestMapping(value = "admin/category", method = RequestMethod.POST)
	public JsonResult addCategory(String category_name, Map<String, Object> map, HttpSession session,
			HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session, response);
		logger.info("整合分类信息");
		ECategory category = new ECategory().setName(category_name);
		logger.info("添加分类信息");
		int add = categoryService.add(category);
		if (add > 0) {
			int category_id = lastIDService.selectLastID();
			logger.info("添加成功！,新增分类的ID值为：{}", category_id);
			map.put("category_id", category_id);
		} else {
			logger.warn("添加失败！事务回滚");
			throw new RuntimeException();
		}
		return new JsonResult(map);
	}

	// 更新分类信息-ajax
	@ResponseBody
	@RequestMapping(value = "admin/category/{category_id}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public JsonResult updateCategory(String category_name, @PathVariable("category_id") Integer category_id,
			Map<String, Object> map, HttpSession session, HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session, response);
		logger.info("整合分类信息");
		ECategory category = new ECategory().setId(category_id).setName(category_name);
		logger.info("更新分类信息，分类ID值为：{}", category_id);
		int update = categoryService.update(category);
		if (update > 0) {
			logger.info("更新成功！");
			map.put("category_id", category_id);
		} else {
			logger.info("更新失败！事务回滚");
			throw new RuntimeException();
		}
		return new JsonResult(map);
	}

	// 删除分类
	@ResponseBody
	@RequestMapping(value = "admin/category/delete/{arr}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResult deleteCategory(@PathVariable("arr") Integer[] category_id_list, HttpSession session,
			HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session, response);
		JsonResult result = new JsonResult();
		logger.info("删除:用户id数组：" + category_id_list.toString());
		for (int i = 0; i < category_id_list.length; i++) {
			List<ECourse> list = courseService.queryByCategoryId(category_id_list[i], null, null);
			if (list.size() != 0) {
				result.setMessage("请先删除分类下的课程");
				throw new RuntimeException();
			} else {
				int delete = categoryService.delete(category_id_list[i]);
				if (delete > 0) {
					logger.info("成功删除分类：id-" + category_id_list[i]);
				} else {
					logger.warn("删除分类异常");
					throw new RuntimeException();
				}
			}
		}
		return result;
	}

	// 按条件查询分类-ajax
	@ResponseBody
	@RequestMapping(value = "admin/category/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public JsonResult getCategoryBySearch(@RequestParam(required = false) String category_name,
			@PathVariable Integer index, @PathVariable Integer count, Map<String, Object> map, HttpSession session,
			HttpServletResponse response) throws UnsupportedEncodingException {
		logger.info("权限验证");
		checkAdmin(session, response);
		// 如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
		if (category_name != null) {
			category_name = category_name.equals("") ? null : URLDecoder.decode(category_name, "UTF-8");
		}
		logger.info("按条件获取第{}页的{}条分类", index + 1, count);
		PageUtil pageUtil = new PageUtil(index, count);
		List<ECategory> categoryList = categoryService.queryAll(category_name, pageUtil);
		map.put("categoryList", JSONArray.parseArray(JSON.toJSONString(categoryList)));
		logger.info("按条件获取分类总数量");
		map.put("categoryCount", categoryList.size());
		logger.info("获取分页信息");
		pageUtil.setTotal(categoryList.size());
		map.put("totalPage", pageUtil.getTotalPage());
		map.put("pageUtil", pageUtil);
		return new JsonResult(map);
	}

	// 上传分类图片-ajax
	@ResponseBody
	@RequestMapping(value = "admin/uploadCategoryImage", method = RequestMethod.POST)
	public JsonResult uploadCategoryImage(@RequestParam MultipartFile file, HttpSession session,
			Map<String, Object> map) {
		String originalFileName = file.getOriginalFilename();
		logger.info("获取图片原始文件名:  {}", originalFileName);
		String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
		String fileName = UUID.randomUUID() + extension;
		String realPath = "D:\\idea\\IDEA_workspace\\bookstore\\src\\main\\webapp\\";
		String filePath = realPath + "res/images/item/categoryPicture/" + fileName;

		logger.info("文件上传路径：{}", filePath);
		try {
			logger.info("文件上传中...");
			file.transferTo(new File(filePath));
			logger.info("文件上传完成");
			map.put("fileName", fileName);
		} catch (IOException e) {
			logger.warn("文件上传失败!");
			e.printStackTrace();
		}
		return new JsonResult(map);
	}
}
