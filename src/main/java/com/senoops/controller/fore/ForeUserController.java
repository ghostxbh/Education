package com.senoops.controller.fore;

import java.util.ArrayList;
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
import com.senoops.model.EOrder;
import com.senoops.service.CourseService;
import com.senoops.service.OrderService;
import com.senoops.utils.PageUtil;

import cn.yunzhf.accounting.user.entity.UzUser;

@Controller
@RequestMapping(value = "/u")
public class ForeUserController extends MainController {
	@Autowired
	private CourseService courseService;	
	@Autowired
	private OrderService orderService;
	

	@RequestMapping(value = "/my/{index}/{count}", method = RequestMethod.GET)	
	public String queryOrder(@PathVariable Integer index,@PathVariable Integer count,Map<String, Object> map, HttpSession session, HttpServletResponse response) {		
		logger.info("权限验证");
		checkUser(session, response);	
		UzUser user = (UzUser) session.getAttribute("user");
		logger.info("分页装配");
		PageUtil pageUtil = new PageUtil(index, count);
		List<EOrder> orderList = orderService.queryByUserId(user.getId(), null, pageUtil);
		if (orderList!=null && orderList.size()>0) {
			map.put("orderList", orderList);
			List<ECourse> courseList = new ArrayList<>();
			for (EOrder order : orderList) {
				ECourse course = courseService.queryById(order.getCourseId());				
				courseList.add(course);
			}
			logger.info("用户id-{}下共有{}课程",user.getId(),courseList.size());
			map.put("courseList", courseList);
			logger.info("按条件获取产品总数量");
			map.put("courseCount", courseList.size());
			logger.info("获取分页信息");
			pageUtil.setTotal(courseList.size());
			map.put("totalPage", pageUtil.getTotalPage());
			map.put("pageUtil", pageUtil);
		}		
		return "fore/mylearn";
	}
}
