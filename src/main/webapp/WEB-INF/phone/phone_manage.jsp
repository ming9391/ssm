<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
  request.setAttribute("itemPath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,height=device-height,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>员工管理</title>
     <!-- 公共js-css -->
	<script type="text/javascript" src="<%=basePath%>resources/common/common.js"></script>
    <style type="text/css">
      body{
      	margin: 0px 50px 0px 5px;
      }
    </style>
    
	<script type="text/javascript">
		//初始化
	    $(function(){
	    	
	    });
	    //分页
	    function getPage(currentlyPage){
	    	if(currentlyPage==-1){
	    		currentlyPage=parseInt($("#_currentlyPage").val())-1;
	    	}
	    	if(currentlyPage==-2){
	    		currentlyPage=parseInt($("#_currentlyPage").val())+1;
	    	}
	    	if(currentlyPage==-3){
	    		currentlyPage=parseInt($("#_totalPage").val());
	    	}
	    	//首页和首页上一页
	    	if(currentlyPage<=0){
	    		currentlyPage=1;
	    	}
	    	//尾页和尾页下一页
	    	if(currentlyPage>parseInt($("#_totalPage").val())){
	    		currentlyPage=parseInt($("#_totalPage").val());
	    	}
	    	$("#currentlyPage").val(currentlyPage);
	    	findBtn();
	    }
	    //查找数据列表
	    function findBtn(){
	    	$.ajax({
	    		type : "post",
	    		url : "${itemPath}phone/findPhoneList.do",
	    		dataType : "json",
	    		data : $("#findForm").serialize(),
	    		success : function(msg) {
	    			var tds ="";
	    			//必须转换成json格式，虽然后台返回是json，但是js误认为是string
	    			var json = eval('('+msg.json+')');
					$.each(json, function(i, v) {
						tds += '<tr>';
						tds += '<td>' + ++i + '</td>';
						tds += '<td>' + v.phoneId +'</td>';
						tds += '<td>' + v.phone +'</td>';
						tds += '<td>' + 
						'<button onclick="updateBtn('+v.phoneId+')" data-toggle="modal" data-target="#updateModal">修改</button>'
						+ '</td>';
						tds += '</tr>';
					});
					$('#data_list').html(tds);//表格绑定数据
					//有数据则显示分页按钮
					if(msg!="" && msg!=null){
						$("#pager").css('display','block');
						//显示当前页与尾页数
						var _page = '<a href="#">'+msg.currentlyPage+'/'+msg.totalPage+'</a> 页';
						$("#_page").html(_page);
						//总条数
						var _page = '共：<a href="#">'+msg.total+'</a> 条';
						$("#_total").html(_page);
						//保存当前页码
						$("#_currentlyPage").val(msg.currentlyPage);
						//保存总页码
						$("#_totalPage").val(msg.totalPage);
					}
	    		}
	    		
	    	});
	    }
	    //修改-绑定
	    function updateBtn(id){
	    	$.ajax({
	    		type : "post",
	    		url : "${itemPath}findPhone.do?updatePhoneId="+id,
	    		dataType : "json",
	    		success : function(msg) {
	    			$("#updatePhoneId").val(msg.phoneId);
	    			$("#updatePhone").val(msg.phone);
	    		}
	    	});
	    }
	    //修改-绑定
	    function updateConfirmBtn(){
	    	$.ajax({
	    		type : "post",
	    		url : "${itemPath}updatePhone.do",
	    		dataType : "json",
	    		data : $("#updateForm").serialize(),
	    		success : function(msg) {
	    			alert("修改成功...");
	    			$("#updateModal").modal("hide");//关掉模态框
	    			findBtn();
	    		}
	    	});
	    }
	</script>
</head>

<body>
	 <fieldset><legend>号码管理</legend></fieldset>
     <form action="#" id="findForm" method="post">
     	<input type="hidden" id="currentlyPage" name="currentlyPage" value="1">
     	手机号：<input type="text" name="phone">&nbsp;
		<button type="button" class="btn btn-primary btn-sm" onclick="findBtn()">查找</button>
     </form>
  
	<!-- 查询数据列表开始 -->
	<table class="table table-bordered">
	 <thead>
		<tr>
			<th>#</th>
			<th>ID</th>
			<th>手机</th>
			<th>编辑</th>
		</tr>
	 </thead>
	 <tbody id="data_list">
	 </tbody>
   </table>
    <!-- 列表分页 -->    
    <ul class="pager" id="pager" style="text-align: right;display: none;margin-right: 20px">
		<li><a href="javascript:void(0)" onclick="getPage('0')">首页</a></li>
		<li><a href="javascript:void(0)" onclick="getPage('-1')">上一页</a></li>
		<li><a href="javascript:void(0)" onclick="getPage('-2')">下一页</a></li>
		<li><a href="javascript:void(0)" onclick="getPage('-3')">尾页</a></li>
		<li><span id="_page"></span></li>
		<li><span id="_total"></span></li>
    </ul>
    <!-- 保存当前页码 -->
    <input type="hidden" id="_currentlyPage">
    <!-- 保存尾页码 -->
    <input type="hidden" id="_totalPage">
    <!-- 修改 -->
    <div class="modal fade" id="updateModal">
	  <div style="width:1230px" class="modal-dialog">
	    <div class="modal-content">
	     
	      <div class="modal-header">
	        <h4 class="modal-title">修改</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	 
	      <div class="modal-body">
	      	<form action="#" id="updateForm" method="post">
	      		<input type="hidden" name="phoneId" id="updatePhoneId">
	      		手机号：<input type="text" name="phone" id="updatePhone">
	      	</form>
	      </div>

	      <div class="modal-footer">
	        <button type="button" class="btn btn-primary" onclick="updateConfirmBtn()">确定</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
	      </div>
	 
	    </div>
	  </div>
	</div>
	
</body>
</html>