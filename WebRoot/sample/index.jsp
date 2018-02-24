<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>样品登录</title>
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
			width:20%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:4px;
			padding: 8px;
			float:left;
			
		}
		#product
		{
			width:20%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:1px;
			padding: 2px;
			float:left;
		}
		#model
		{
			width:20%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:1px;
			padding: 2px;
			float:left;
		}
		
		#analysisItems
		{
			width:20%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:1px;
			padding: 2px;
			float:left;
		}
	</style>
	
  </head>
  
  <body>
  	<jsp:include page="header.jsp"></jsp:include>

  	<div id='main-panel'>
  		<div id='department'>
  			<table class='table'>
  				<thead>
  					<tr>
  						<th>部门编号</th>
  						<th>部门名称</th>
  					</tr>
  				</thead>
  				<tbody>  				
  				</tbody>
  			</table>
  		</div>
  		
  		<div id='product'>
  			<table class='table'>
  				<thead>
  					<tr>
  						<th>产品编号</th>
  						<th>产品名称</th>
  					</tr>
  				</thead>
  				<tbody>  				
  				</tbody>
  			</table>
  		</div>
  		
  		<div id='model'>
  			<table class='table'>
  				<thead>
  					<tr>
  						<th>牌号编号</th>
  						<th>牌号名称</th>
  					</tr>
  				</thead>
  				<tbody>  				
  				</tbody>
  			</table>
  		</div>
  		
		<div id='analysisItems'>
  			<table class='table'>
  				<thead>
  					<tr>
  					 	<th>是否选择</th>
  						<th>方法编号</th>
  						<th>方法名称</th>
 
  					</tr>
  				</thead>
  				<tbody>  				
  				</tbody>
  			</table>
  		</div>
  	</div>


  	<jsp:include page="dlg_details.jsp"></jsp:include>

  </body>
<script>

///////////////////analysisItems 分析项目////////////////
	var ANALY = {};
	ANALY.panel = $("#analysisItems");
	
  	//显示方法
  	ANALY.load = function(modelId)
  	{
  		var data = {};
  		data.modelId = modelId;
		var req = JSON.stringify(data);
		if(data.modelId != null)
		{
		  	var url = 'getModelAnalysisItemsList.do';
  			//此处要进行二次查询，ModelAnalysisItems中查询出需要的AnalysisItemsId，然后再从AnalysisItems中获得记录；

			$.post(url, req, function (ans, status) {
  		  		var resp = $.parseJSON(ans);	
  				if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}
				//AnalysisItemsId的集合；
				var res = resp.result;
				//遍历数组res，取出其中的analysisitemid；
				var analysisItemId = [];
				for(j = 0,len=res.length; j < len; j++) 
				{
					analysisItemId[j]=res[j].analysisitemid;
				}
			
				//二次查询：从AnalysisItems中获取数据，参数是id集合
				var data2 = {};
				data2.analysisItemId = analysisItemId;
				req = JSON.stringify(data2);
				//写到此处了，此处的后台还没写
				var url = 'getAnalysisItemsById.do';
				$.post(url, req, function (ans, status) {
  			  		var resp = $.parseJSON(ans);	
  					if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}

  						 ANALY.show_item_list(resp.result);
 
  					 });

			

  		 });


		}
		else
		{
			var url = 'getAnalysisItemsList.do';
			$.post(url, req, function (ans, status) {
  		  	var resp = $.parseJSON(ans);	
  			if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}

  			 ANALY.show_item_list(resp.result);
 
  		 });
		}


  	}

  	//列出给定的方法
  	ANALY.show_item_list = function(items)
  	{
  		var target = $("#analysisItems tbody");
  		target.html("");
  		
  		for(var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<tr class='item' id1='##1' onclick='ANALY.clicked(this)'>"
  				+ "<td> <input type='checkbox' name='checkbox' value='##2' /> </td>"
  				+ "<td>" + it.id + "</td>"
  				+ "<td>" + it.name + "</td>"
  				+ "</tr>";

  			str = str.replace(/##1/g,it.id).replace(/##2/g, it.id);			
  			target.append(str);
  		}
  	}
  	
  	ANALY.clicked = function(dom)
  	{
  		toastr.success("点击方法 成功");
  	}
  	
  	
  	
  	ANALY.panel.ready(function(){
		
	});



//////////////////model 牌号///////////////////////////
	var MODEL = {};
	MODEL.panel = $("#model");
	
  	//显示一个产品的所有牌号
  	MODEL.load = function(productId)
  	{
  		var data = {};
  		data.productId = productId;
		var req = JSON.stringify(data); 
  		var url = 'getProductModelList.do';
  		
  		$.post(url, req, function (ans, status) {
  		  	var resp = $.parseJSON(ans);	
  			console.trace(status);
  			if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}

  			MODEL.show_item_list(resp.result);
  		 });
  	}

  	//显示产品的所有牌号
  	MODEL.show_item_list = function(items)
  	{
  		var target = $("#model tbody");
  		target.html("");
  		
  		for(var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<tr class='item' id1='##1' onclick='MODEL.clicked(this)'>"
  				+ "<td>" + it.id + "</td>"
  				+ "<td>" + it.name + "</td>"
  				+ "</tr>";

  			str = str.replace(/##1/g,it.id); 			
  			target.append(str);
  		}
  	}
  	
  	MODEL.clicked = function(dom)
  	{
  		toastr.success("点击牌号 成功");
  		var modelId = $(dom).attr("id1");
  		DLG_DETAILS.show(modelId);

  	}
  	
  	
  	
  	MODEL.panel.ready(function(){
		
		//DEPA.load();
	});



//////////////////product///////////////////////////
	var PROD = {};
	PROD.panel = $("#product");
	
  	//显示一个部门的所有产品
  	PROD.load = function(departmentId)
  	{
  		var data = {};
  		data.departmentId = departmentId;
		var req = JSON.stringify(data); 
 		//使用$.post方式发起请求，返回json格式的数据较好解析，
  		url = 'getDepartProductList.do';
  		$.post(url, req, function (ans, status) {
  		  	var resp = $.parseJSON(ans);	//将字符串转换为json
  			console.trace(status);
  			if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}

  			PROD.show_item_list(resp.result);
  		 	//toastr.success("返回成功");
  		 });
  	}

  	//显示部门的产品
  	PROD.show_item_list = function(items)
  	{
  		var target = $("#product tbody");
  		target.html("");
  		
  		for(var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<tr class='item' id1='##1' onclick='PROD.clicked(this)'>"
  				+ "<td>" + it.id + "</td>"
  				+ "<td>" + it.name + "</td>"
  				+ "</tr>";

  			str = str.replace(/##1/g,it.id); 			
  			target.append(str);
  		}
  	}
  	
  	PROD.clicked = function(dom)
  	{
  		var productId = $(dom).attr("id1");
  		MODEL.load(productId);
  	}
  	
  	
  	
  	PROD.panel.ready(function(){

	});



///////////////////department//////////////////////////
	var DEPA = {};
	DEPA.panel = $("#deparment");
	
	
  	//显示所有部门
  	DEPA.load = function()
  	{
  		var data = {};
		var req = JSON.stringify(data); 
 		//使用$.post方式发起请求，返回json格式的数据较好解析，
  		url = 'getDepartmentList.do';
  		$.post(url, req, function (ans, status) {
  		  	var resp = $.parseJSON(ans);	//将字符串转换为json
  			console.trace(status);
  			if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}

  			DEPA.show_item_list(resp.result); 			
  		 	//toastr.success("返回成功");
  		 });
  	}
  	
  	//显示所有部门
  	DEPA.show_item_list = function(items)
  	{
  		var target = $("#department tbody");
  		target.html("");
  		
  		for(var i=0; i<items.length; i++)
  		{
  			var it = items[i];
  			var str = "<tr class='item' id1='##1' onclick='DEPA.clicked(this)'>"
  				+ "<td>" + it.id + "</td>"
  				+ "<td>" + it.name + "</td>"
  				+ "</tr>";
  				
  			str = str.replace(/##1/g,it.id); 			
  			target.append(str);
  		}
  	}
  	
  	DEPA.clicked = function(dom)
  	{
  		toastr.success("点击 成功");

  		var departmentId = $(dom).attr("id1");
  		PROD.load(departmentId);
  	}
  	
  	
  	
  	DEPA.panel.ready(function(){
		
		DEPA.load();
	});

/////////////////////////////////////////////

	var MAIN = {};
	MAIN.panel = $("#main-panel");
	
	//加载
	MAIN.load = function()
	{
		//当页面加载的时候，显示所有部门信息，在一个div中使用表格显示
		

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
