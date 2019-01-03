package com.ssm.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
/**
 * 自定义角色验证过滤器
 * 1、验证授权时，只要满足其中一个角色就可以访问
 */
public class UserRolesAuthorizationFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		String[] rolesArray = (String[]) mappedValue;
		// 如果为空，则代表没配置授权信息（不需要验证授权）
		if (rolesArray == null || rolesArray.length == 0) {
			return true;
		}
		//判断角色，只要有任何一个都可以访问
		for(int i=0;i<rolesArray.length;i++) {
			if(subject.hasRole(rolesArray[i])) {
				return true;
			}
		}
		return false;
	}

}
