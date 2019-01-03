package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/** 初始化主页内容--定时器等 */
	@RequestMapping("index_right.do")
	public String index_right(){
		return "index/index_right.jsp";
	}
	/** 号码管理页面 */
	@RequestMapping("phone_manage.do")
	public String phone_manage(){
		return "phone/phone_manage.jsp";
	}
}
