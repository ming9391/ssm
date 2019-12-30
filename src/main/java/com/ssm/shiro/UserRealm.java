package com.ssm.shiro;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssm.mapper.UserMapper;
import com.ssm.model.User;
import com.ssm.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Resource
	private UserService userService;

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		try {
			// 角色
			String roleName = userService.findRoleByUserName(userName);
			authorizationInfo.addRole(roleName);
			// 权限
			/*
			 * Set<Permission> permissions =
			 * permissionDao.findByRole(userDao.findOneByUserName(userName).getRole());
			 * Set<String> strs = new HashSet<String>(); for (Permission permission :
			 * permissions) { strs.add(permission.getPermissionName()); }
			 * authorizationInfo.addStringPermissions(strs);
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authorizationInfo;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 从token中 获取用户身份信息
		String username = (String) token.getPrincipal();
		User user = userService.findUserByUserName(username);
		if (user == null) {
			throw new UnknownAccountException("用户名不存在!");
		}
		// 密码加盐-采取用户名和密码加密的方式
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);

		// 将Hash转成String
		String password = new String((char[]) token.getCredentials());
		String md5Pwd = new Md5Hash(password, username).toHex();

		// 开始认证
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, md5Pwd,
				credentialsSalt, getName());
		return simpleAuthenticationInfo;
	}

}
