package com.ssm.mapper;

import org.apache.ibatis.annotations.Param;
import com.ssm.model.User;

public interface UserMapper {
	
	/**根据用户名查找用户信息*/
	public User findUserByUserName(@Param("userName")String username);

	/**根据用户名查找用户角色信息*/
	public String findRoleByUserName(@Param("userName")String userName);

}
