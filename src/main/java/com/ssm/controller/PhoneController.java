package com.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.ssm.model.Phone;
import com.ssm.service.PhoneService;
import com.ssm.utils.PageUtil;
import com.ssm.utils.ResponseUtil;

@Controller
@RequestMapping("phone/")
public class PhoneController {

	@Autowired
	private PhoneService phoneService;
	
	/** 号码管理*/
	@RequestMapping("findPhoneList.do")
	public void findPhoneList(HttpServletRequest request,HttpServletResponse response,PageUtil page,Phone phone){
		//省份下拉框-级联市-区、可输入文字索引。
		PageUtil data = phoneService.findPhoneList(page,phone);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		ResponseUtil.renderJson(response, json);
	}
	/** 查找单个号码信息-修改绑定*/
	@RequestMapping("findPhone.do")
	public void findPhone(HttpServletRequest request,HttpServletResponse response,String updatePhoneId){
		Phone data = phoneService.findPhone(updatePhoneId);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		ResponseUtil.renderJson(response, json);
	}
	/** 修改号码*/
	@RequestMapping("updatePhone.do")
	public void updatePhone(HttpServletRequest request,HttpServletResponse response,Phone phone){
		phoneService.updatePhone(phone);
		ResponseUtil.renderJson(response, "1");
	}
	
}
