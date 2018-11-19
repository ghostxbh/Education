package com.senoops.controller.admin;
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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.senoops.controller.MainController;
import com.senoops.model.ECourse;
import com.senoops.model.EOrder;
import com.senoops.service.CourseService;
import com.senoops.service.OrderService;
import com.senoops.utils.JsonResult;
import com.senoops.utils.OrderUtil;
import com.senoops.utils.PageUtil;

@Controller
public class AdminOrderController extends MainController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "admin/order")
	public String goOrderHome(Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("获取前10条订单列表");
		PageUtil pageUtil = new PageUtil(0, 10);
		List<EOrder> orderList = orderService.queryAll(null, pageUtil);
		if (orderList != null) {
			map.put("orderList", orderList);
			logger.info("获取订单总数量");
			map.put("orderCount", orderList.size());
			logger.info("获取分页信息");
			pageUtil.setTotal(orderList.size());
			map.put("pageUtil", pageUtil);
		}
		logger.info("转到后台管理-订单页-ajax方式");
		return "admin/orderManagePage";
	}

	// 转到后台管理-订单详情页-ajax
	@RequestMapping(value = "admin/order/{oid}", method = RequestMethod.GET)
	public String goToDetailsPage(Map<String, Object> map, @PathVariable Integer oid,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("获取order_id为{}的订单信息", oid);
		EOrder order = orderService.searchById(oid);
		map.put("order", order);
		ECourse course = courseService.queryById(order.getCourseId());
		logger.info("获取购买的产品信息{}", course.toString());
		map.put("course", course);
		logger.info("转到后台管理-订单详情页-ajax方式");
		return "admin/include/orderDetails";
	}

	// 删除订单
	@ResponseBody
	@RequestMapping(value = "admin/order/delete/{arr}", method = RequestMethod.GET)
	public JsonResult deleteOrder(@PathVariable("arr") Integer[] order_id_list,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		logger.info("删除:用户id数组：" + order_id_list.toString());
		int deleteList = orderService.deleteList(order_id_list);
		return new JsonResult(deleteList);
	}

	// 按条件查询订单-ajax
	@ResponseBody
	@RequestMapping(value = "admin/order/{index}/{count}", method = RequestMethod.GET)
	public JsonResult getOrderBySearch(@RequestParam(required = false) String code, String status,
			@RequestParam(required = false) String orderBy,
			@RequestParam(required = false, defaultValue = "true") Boolean isDesc, @PathVariable Integer index,
			@PathVariable Integer count, Map<String, Object> map,HttpSession session,HttpServletResponse response) {
		logger.info("权限验证");
		checkAdmin(session,response);
		if (code != null) {
			code = code.equals("") ? null : code;
		}
		if (status != null) {
			status = status.equals("") ? null : status;
		}
		if (orderBy != null && orderBy.equals("")) {
			orderBy = null;
		}
		// 封装查询条件
		EOrder order = new EOrder().setCode(code).setStatus(status);
		OrderUtil orderUtil = null;
		if (orderBy != null) {
			logger.info("根据{}排序，是否倒序:{}", orderBy, isDesc);
			orderUtil = new OrderUtil(orderBy, isDesc);
		}
		logger.info("按条件获取第{}页的{}条订单", index + 1, count);
		PageUtil pageUtil = new PageUtil(index, count);
		List<EOrder> list = orderService.queryByParam(order, orderUtil, pageUtil);
		if (list != null) {
			map.put("orderList", JSONArray.parseArray(JSON.toJSONString(list)));
			logger.info("按条件获取订单总数量");
			map.put("productOrderCount", list.size());
			logger.info("获取分页信息");
			pageUtil.setTotal(list.size());
			map.put("totalPage", pageUtil.getTotalPage());
			map.put("pageUtil", pageUtil);
		}
		return new JsonResult(map);
	}

	// 更新订单信息-ajax
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "admin/order/{order_id}", method =
	 * RequestMethod.PUT, produces = "application/json;charset=UTF-8") public
	 * String updateOrder(@PathVariable("order_id") String order_id) {
	 * JSONObject jsonObject = new JSONObject(); logger.info("整合订单信息");
	 * ProductOrder productOrder = new ProductOrder()
	 * .setProductOrder_id(Integer.valueOf(order_id))
	 * .setProductOrder_status((byte) 2) .setProductOrder_delivery_date(new
	 * Date()); logger.info("更新订单信息，订单ID值为：{}", order_id); boolean yn =
	 * productOrderService.update(productOrder); if (yn) { logger.info("更新成功！");
	 * jsonObject.put("success", true); } else { logger.info("更新失败！事务回滚");
	 * jsonObject.put("success", false); throw new RuntimeException(); }
	 * jsonObject.put("order_id", order_id); return jsonObject.toJSONString(); }
	 */
}
