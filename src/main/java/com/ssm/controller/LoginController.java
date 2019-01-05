package com.ssm.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.eclipse.jdt.internal.compiler.batch.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ssm.model.User;

@Controller
public class LoginController {
	//日志
	private static final Logger logs = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 登录 
	 */
	//@RequiresRoles(value = { "admin" })
	@RequestMapping("findLoginUser.do")
	public String findLoginUser(HttpServletRequest request,HttpServletResponse response,User user) throws IOException{
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(user.getUserName(),user.getUserPassword());
		try {
			subject.login(token);
			if(subject.isAuthenticated()) {
				request.getSession().setAttribute("user", user);
			}
			//subject.checkRole("admin");//检查是否包含该权限
			User user1 = (User) request.getSession().getAttribute("user");
			System.out.println("认证："+subject.isAuthenticated());
		} catch (Exception e) {
			System.out.println("登陆失败："+e.getMessage());
			logs.error(e.getMessage());
			response.sendRedirect("login.jsp");
		}
		return "index/index.jsp";
	}
}
