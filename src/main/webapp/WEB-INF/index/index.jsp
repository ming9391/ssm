<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro"  uri="http://shiro.apache.org/tags" %>
<%
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
  request.setAttribute("itemPath", basePath);
%>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>人事管理系统</title>
	<!-- 公共js-css -->
	<script type="text/javascript" src="<%=basePath%>resources/common/common.js"></script>
	
	<!-- 菜单伸缩事件 -->
	<script type="text/javascript" src="<%=basePath %>resources/index/nav.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/index/htmleaf-demo.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>resources/index/nav.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>resources/index/fonts/iconfont.css">
    
	<script type="text/javascript">
		//默认菜单栏合起
		$(function(){  
			$('.nav').addClass('nav-mini');
		})
	</script>
</head>

<body>
	<!-- 左边菜单栏 -->
	<div class="nav">
        <div class="nav-top">
            <div id="mini" style="border-bottom:1px solid rgba(255,255,255,.1)"><img src="<%=basePath%>resources/images/mini.png"></div>
        </div>
        <ul>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_1"></i><span>phone</span><i class="my-icon nav-more"></i></a>
                <ul>
                   <shiro:hasAnyRoles name="admin,admin1">
                   		 <li><a href="<%=basePath%>phone_manage.do" target="content"><span>号码管理</span></a></li>
  				   </shiro:hasAnyRoles>
                </ul>
            </li>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_2"></i><span>二</span><i class="my-icon nav-more"></i></a>
            </li>
            <li class="nav-item">
                <a href="javascript:;"><i class="my-icon nav-icon icon_3"></i><span>三</span><i class="my-icon nav-more"></i></a>
            </li>
        </ul>
    </div>
	 <!-- 右边列表 -->
	 <div class="htmleaf-container">
		<header class="htmleaf-header">
			<h2>hello world</h2>
			<!-- <div class="htmleaf-links">
				<a class="htmleaf-icon icon-htmleaf-home-outline" href="http://www.htmleaf.com/" title="jQuery之家" target="_blank"><span> jQuery之家</span></a>
				<a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://www.htmleaf.com/jQuery/Menu-Navigation/201710244793.html" title="返回下载页" target="_blank"><span> 返回下载页</span></a>
			</div> -->
		</header>
		<!-- 列表加载start -->
		<div class="related">
          <iframe id="content" name="content" src="<%=basePath%>index_right.do" style="overflow:visible; margin-left:60px" scrolling="yes" frameborder="no" 
             width="100%" height="630px"; float:left">
          </iframe>
		</div>
		<!-- 页面加载end -->
	</div>
	
</body>
</html>