package com.senoops.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.senoops.model.ERole;
import com.senoops.service.RoleService;
import com.senoops.utils.redis.HostUtil;

import cn.yunzhf.accounting.user.entity.UzUser;

@Controller
public class MainController {
	public final static Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
	@Autowired
	private RoleService roleService;	

	/**
	 * 检查用户信息/登陆	
	 * @param session
	 * @return
	 */
	
	public String check(HttpSession session) {
		UzUser user = (UzUser) session.getAttribute("user");
		if (user != null) {
			ERole role = roleService.queryByUserId(user.getId());
			logger.info("用户角色：1、会员；2、管理员 --- " + role.getRole());
			if (role != null&&!role.equals("")) {
				return role.getRole();
			} else {
				ERole newRole = new ERole()
						.setRole("1")						
						.setUserId(user.getId())
						.setUserCode(user.getCode())
						.setUserName(user.getName())
						.setUserPhone(user.getPhone());
				roleService.insert(newRole);
				logger.info("添加用户角色信息成功，用户{}",user.getCode());
				return role.getRole();
			}
		} else {
			logger.info("用户没有登陆");
			return HostUtil.host + "AccountingOnline/user/checkLogin?url=Onlineducation/getsign";
		}
	}
	
	public void checkAdmin(HttpSession session,HttpServletResponse response){
		String check = check(session);
		if (check.length()>2) {
			try {
				logger.info("没有登陆，重定向登陆页面");
				response.sendRedirect(check);
				return;
			} catch (IOException e) {
				logger.info("重定向异常");
				throw new RuntimeException(e);
			}
		} else if(check.equals("2")){
			logger.info("认证管理员通过");
		} else{
			logger.info("没有管理权限");
			throw new RuntimeException("没有管理权限");
		}		
	}
	
	public void checkUser(HttpSession session,HttpServletResponse response){
		String check = check(session);
		if (check.length()>2) {
			try {
				logger.info("没有登陆，重定向登陆页面");
				response.sendRedirect(check);
				return;
			} catch (IOException e) {
				logger.info("重定向异常");
				throw new RuntimeException(e);
			}
		}
	}
}
