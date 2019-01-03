<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>员工管理</title>
	<!-- request.setAttribute("data", data); return "phone/phone_manage.jsp"; //no-ajax  -->
     <!-- 公共js-css -->
	<script type="text/javascript" src="<%=basePath%>resources/common/common.js"></script>
    
	<script type="text/javascript">
	    //分页
	    function getPage(pageNo){
	    	if(pageNo==0){
	    		pageNo=1;
	    	}
	    	if(pageNo==-1){
	    		pageNo=parseInt($("#_currentPage").text())-1;
	    	}
	    	if(pageNo==-2){
	    		pageNo=parseInt($("#_currentPage").text())+1;
	    	}
	    	if(pageNo==-3){
	    		pageNo=parseInt($("#_totalPage").text());
	    	}
	    	$("#pageNo").val(pageNo);
	    	$("#findForm").submit();
	    }
	    //有数据才显示分页按钮
	    $(function(){
	    	if($("#_showPager").text()!=""){
	    		$("#pager").css('display','block'); 
	    	}
	    });
	</script>
</head>

<body>
	<table class="table table-bordered">
		 <fieldset><legend>号码管理</legend></fieldset>
	     <form action="<%=basePath%>findPhoneList.do" id="findForm" method="post">
			<button type="submit" class="btn btn-primary btn-sm">查找</button>&nbsp;
	     </form>
	  
		<!-- 查询数据列表开始 -->
		<thead>
			<tr>
				<th>ID</th>
				<th>号码</th>
			</tr>
		</thead>
		<tbody>
		  <c:forEach items="${data}" var="da" varStatus="status">  
    		  <tr>
				<td>${da.phoneId}</td>
				<td>${da.phone}</td>
			  </tr>
          </c:forEach>
		</tbody>
		<!-- 查询数据列表结束 -->
   </table>
    <!-- 列表分页 -->    
    <ul class="pager" id="pager" style="text-align: right;display: none;">
		<li><a href="javascript:void(0)" onclick="getPage('0')">首页</a></li>
		<li><a href="javascript:void(0)" onclick="getPage('-1')">上一页</a></li>
		<li><a href="javascript:void(0)" onclick="getPage('-2')">下一页</a></li>
		<li><a href="javascript:void(0)" onclick="getPage('-3')">尾页</a></li>
		<li><a href="#">${pageData.currentPage}/${pageData.totalPage} 页</a></li>
    </ul>
    <div id="_currentPage" style="display: none;">${pageData.currentPage}</div>
    <div id="_totalPage" style="display: none;">${pageData.totalPage}</div>
    <div id="_showPager" style="display: none;">${data}</div>
    
</body>
</html>