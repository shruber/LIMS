<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>实验室数据管理系统-首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<jsp:include page="include.jsp" />
	
	<style>	
		#main-panel
		{
			width:80%;
			margin:10px auto auto auto;
			border:1px solid #CCC;
			border-radius:4px;
			padding: 8px;
			min-height:360px;
		}
		
		#head
		{
			display:inline-block;
			margin:10px auto auto auto;
			width:100%;
			border-radius: 8px;
			padding: 4px;
			text-align:center;
		}
		
		#head a
		{
			font-size:180%;
		}
	
	</style>
	
  </head>
  
  <body>
  <div id='head'>

  	</div>
  	<div id='main-panel'>
		<a onclick = "MAIN.clicked()">登录</a>
  	</div>

  </body>
<script>
	var MAIN = {};
	MAIN.panel = $("#main-panel");
	
	//加载
	MAIN.load = function()
	{

	}
	
	MAIN.clicked = function(){
	
	toastr.error("error"); 
	
	
	}
	
	//初始化
	MAIN.panel.ready(function(){
		
		MAIN.load();
	});
</script>

</html>
