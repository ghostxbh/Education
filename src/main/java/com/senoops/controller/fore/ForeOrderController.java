package com.senoops.controller.fore;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.senoops.controller.MainController;
import com.senoops.model.EInvoice;
import com.senoops.model.EOrder;
import com.senoops.service.InvoiceService;
import com.senoops.service.LastIDService;
import com.senoops.service.OrderService;
import com.senoops.utils.JsonResult;

@Controller
@RequestMapping(value = "/order")
public class ForeOrderController extends MainController {	
	@Autowired
	private InvoiceService invoiceService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private LastIDService lastIDService;

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addOrder(EOrder order,EInvoice invoice,Map<String, Object> map, HttpSession session, HttpServletResponse response) {
		logger.info("权限验证");
		checkUser(session, response);		
		logger.info("订单信息{}",order.toString());
		int insert = orderService.insert(order);
		if (insert>0) {
			logger.info("订单添加成功");
		}else{
			logger.info("订单添加异常");
			throw new RuntimeException();
		}
		if (invoice!=null&&invoice.equals("")) {
			int lastID = lastIDService.selectLastID();
			logger.info("获取订单id-{}",lastID);
			invoice.setOrderId(lastID);
			logger.info("获取发票信息{}",invoice.toString());
			int add = invoiceService.add(invoice);
			if (add>0) {
				logger.info("订单添加成功");
			}else{
				logger.info("订单添加异常");
				throw new RuntimeException();
			}
		}		
		return new JsonResult();
	}	
}
