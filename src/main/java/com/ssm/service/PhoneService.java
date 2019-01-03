package com.ssm.service;

import com.ssm.model.Phone;
import com.ssm.utils.PageUtil;

public interface PhoneService {

	/** 查找号码列表*/
	public PageUtil findPhoneList(PageUtil page,Phone phone);
	/** 查找单个号码信息-修改绑定*/
	public Phone findPhone(String id);
	/** 修改号码*/
	public void updatePhone(Phone phone);
}
