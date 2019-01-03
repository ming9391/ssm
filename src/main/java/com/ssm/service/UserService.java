package com.ssm.service;

import com.ssm.model.User;

public interface UserService {
	
	/**根据用户名查找用户信息*/
	public User findUserByUserName(String username);
	/**根据用户名查找用户角色信息*/
	public String findRoleByUserName(String userName);

}
