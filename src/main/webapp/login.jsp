<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<!-- 公共js-css -->
<script type="text/javascript" src="<%=basePath%>resources/common/common.js"></script>
</head>
<body>
	<div style="margin: 300px">
	 	<form action="<%=basePath%>findLoginUser.do" id="findForm" method="post">
	 		用户名：<input type="text" name="userName">
	 		用户名：<input type="password" name="userPassword">
	       <input type="submit" class="btn btn-info btn-xs" value="登录">
	       <input type="reset" class="btn btn-info btn-xs" value="重置">
		</form>
	</div>
</body>
</html>