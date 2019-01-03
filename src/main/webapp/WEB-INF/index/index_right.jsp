<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
  request.setAttribute("itemPath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>默认页面</title>

<!-- 公共js-css -->
<script type="text/javascript" src="<%=basePath%>resources/common/common.js"></script>
<!-- 定时任务 -->
<%-- <script type="text/javascript" src="<%=basePath %>resources/task.js"></script> --%>

</head>

<body>
	<img alt="请检查网络..." src="<%=basePath %>resources/images/index.jpg" width="100%" height="630px">
	
	<div class="modal fade" id="addModal">
	  <div style="width:1230px" class="modal-dialog">
	    <div class="modal-content">
	     
	      <div class="modal-header">
	        <h4 class="modal-title">添加</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	 
	      <div class="modal-body">
	      	table
	      </div>

	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary">添加</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	      </div>
	 
	    </div>
	  </div>
	</div>
	
</body>
</html>