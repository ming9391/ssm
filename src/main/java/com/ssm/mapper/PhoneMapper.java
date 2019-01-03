package com.ssm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssm.model.Phone;

public interface PhoneMapper {

	/** 查找手机号码列表*/
	public List<Phone> findPhoneList(@Param("currentPage")Integer currentPage,@Param("pageSize")String pageSize,@Param("phone")Phone phone);
	/** 查找手机号码列表总数*/
	public String findPhoneListTotal(@Param("phone")Phone phone);
	/** 查找单个号码信息-修改绑定*/
	public Phone findPhone(@Param("phoneId")String id);
	/** 修改号码*/
	public void updatePhone(@Param("phone")Phone phone);
}
