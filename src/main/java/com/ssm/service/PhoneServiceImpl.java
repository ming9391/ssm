package com.ssm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.ssm.mapper.PhoneMapper;
import com.ssm.model.Phone;
import com.ssm.utils.PageUtil;

@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

	@Autowired
	private PhoneMapper phoneMapper;
	
	public PageUtil findPhoneList(PageUtil page,Phone phone) {
		Integer currentlyPage = 0;
		if(page.getCurrentlyPage()!=null && !"".equals(page.getCurrentlyPage())){
			currentlyPage = (Integer.parseInt(page.getCurrentlyPage())-1) * Integer.parseInt(page.getPageSize());
		}
		List<Phone> data = phoneMapper.findPhoneList(currentlyPage,page.getPageSize(),phone);
		String total = phoneMapper.findPhoneListTotal(phone);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		PageUtil result = new PageUtil();
		result.setCurrentlyPage(page.getCurrentlyPage());
		result.setTotal(total);
		result.setJson(json);
		result.setTotalPage(0, total, page.getPageSize());
		return result;
	}

	@Override
	public Phone findPhone(String id) {
		Phone data = phoneMapper.findPhone(id);
		return data;
	}

	@Override
	public void updatePhone(Phone phone) {
		phoneMapper.updatePhone(phone);
	}

	
	
}
