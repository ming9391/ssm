package com.ssm.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssm.mapper.UserMapper;
import com.ssm.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User findUserByUserName(String username) {
		System.out.println("认证...");
		User user = userMapper.findUserByUserName(username);
		return user;
	}

	@Override
	public String findRoleByUserName(String userName) {
		System.out.println("授权...");
		String role = userMapper.findRoleByUserName(userName);
		return role;
	}

}
