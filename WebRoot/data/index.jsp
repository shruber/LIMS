<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分析数据录入</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<jsp:include page="../include.jsp" />
	
	<style>	
		#main-panel
		{
			width:100%;
			height:90%;
			margin:10px auto auto auto;
			border:1px solid #CCC;
			border-radius:4px;
			padding: 8px;
			min-height:360px;
		}

		#department
		{
			width:15%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:4px;
			padding: 8px;
			float:left;
			
		}
		
		.item-selected
		{
			background-color: #C0C0C0;
		}
		
		
	</style>
	
  </head>
  
  <body>
  	<jsp:include page="header.jsp"></jsp:include>

  	<div id='main-panel'>
	<!-- 数据的录入，第一步是显示样品，显示某一个部门的样品，当选中某一个样品时，点击上方的数据录入按钮;之前的步骤，比如数据录入按钮，可以放在首页中 -->	
		<div id='sample' >
			<table class='table'>
  				<thead>
  					<tr>
  						<th>样品编号</th>
  						<th>样品名称</th>
  						<th>采样人</th>
  						<th>采样时间</th>
  					</tr>
  				</thead>
  				<tbody>
  				</tbody>
  			</table>

		</div>
		<div id='sample' >
			<br>
  			<button type="button" value="Button" onclick="SAMPLE.addData()">录入样品数据</button>

		</div>
  	</div>

  </body>
<script>
///////////////////sample 样品//////////////////////////
	var SAMPLE = {};
	SAMPLE.panel = $("#sample");
	
	SAMPLE.departmentId = 1;
	
	//加载
	SAMPLE.load = function()
	{
		//当页面加载的时候，查询以选择的部门的数据未二审的样品；
		var data = {};
  		data.departmentId =	 SAMPLE.departmentId;
  		data.status = 0;	//数据没有录入完成的，sample.satus = 0;
		var req = JSON.stringify(data);
				   
		var url = 'getSampleByDeparmentIdAndStatus.do';
		$.post(url, req, function (ans, status) {
  		  	var resp = $.parseJSON(ans);
  			if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}
					toastr.success("样品查询 success");							
					SAMPLE.show_item_list(resp.result);
  			});
	}

	//显示给定的样品记录
	SAMPLE.show_item_list = function(items)
  	{
  		var target = $("#sample tbody");
  		target.html("");
  		
  		for(var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<tr class='item' id1='##1' onclick='SAMPLE.clicked(this)'>"
  				+ "<td>" + it.id + "</td>"
  				+ "<td>" + it.name + "</td>"
  				+ "<td>" + it.creater + "</td>"
  				+ "<td>" + it.samplingtime + "</td>"
  				+ "</tr>";

  			str = str.replace(/##1/g,it.id);
  			target.append(str);
  		}
  	}
	
	
	
	
	SAMPLE.selectedId = null;
	SAMPLE.clicked = function(dom)
	{
		//选中状态切换
		$(dom).siblings().removeClass("item-selected");
  		$(dom).addClass("item-selected");
		SAMPLE.selectedId = $(dom).attr('id1');
	}
	
	SAMPLE.addData = function()
	{
		//当点击录入数据按钮时，获得选中的样品id，查询其分析项目；
		//此处有一个问题，怎么查该样品的分析项目，如果把所有分析项目的表都遍历一遍，那也太浪费时间和资源了，如果不遍历，又有什么办法呢？
		//在样品基本信息中，应该有一个字段，存储其分析项目表名的列表，但是我在设计数据库的时候没有想到这一点，也没有预留字段（以后要记得预留字段）；
		//现在我不想在原表中添加新的字段了，我重新建一个表，保存样品id和分析项目的映射关系；
		//这么做的优点是不用更改已经写好的代码，缺点是数据库的完整性不行，虽然我也不知道数据库有没有完整性这个评价；
		
		if(SAMPLE.selectedId == null)
		{
			toastr.error("错误，未选择样品！请选择样品后重试！");
			return;
		}
		
		//从sampleAnalysisItems表中获取分析项目，然后在分析项目表中使用sampleId查找其记录，并返回
		var data = {};
  		data.sampleId = SAMPLE.selectedId;
		var req = JSON.stringify(data);
		  
		var url = 'getAnalysisItemsBySampleId.do';
		$.post(url, req, function (ans, status) {
  		  	var resp = $.parseJSON(ans);
  			if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}
					toastr.success("分析项目查询 success");							
					//SAMPLE.show_item_list(resp.result);
  			});
	
		
		
		
		
		toastr.success("success");
		
		
		
		
		
	
	
	}
	
	
	//初始化
	 SAMPLE.panel.ready(function()
	{
		
		 SAMPLE.load();
	});
/////////////////////////////////////////////

	var MAIN = {};
	MAIN.panel = $("#main-panel");
	
		
	//加载
	MAIN.load = function()
	{
		
	}
	
	
	
	
	
	
	MAIN.clicked = function()
	{
	
	
	
	//toastr.error("error"); 
	
	
	}
	
	//初始化
	MAIN.panel.ready(function()
	{
		
		//MAIN.load();
	});
</script>

</html>
