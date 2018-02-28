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
			width:15%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:4px;
			padding: 8px;
			float:left;
			
		}
		#product
		{
			display:none;
			width:15%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:1px;
			padding: 2px;
			float:left;
		}
		#model
		{
			display:none;
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
			display:none;
			width:20%;
			height:100%;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:1px;
			padding: 2px;
			float:left;
		}
		
		#sample
		{
			display:none;
			width:10%;
			height:30%;
			text-align: center;
			margin:auto auto auto auto;
			border:1px solid #CCC;
			border-radius:1px;
			padding: 2px;
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
  		
  		<div id='sample'>
		<br>
  		<button type="button" value="Button" onclick="SAMPLE.submit()">确认添加样品</button>
  		<br>
  		<br>
  		<button type="reset" value="Reset">放弃添加样品</button>
  		<br>
  		</div>	
  		
  	</div>
	


  	<jsp:include page="dlg_details.jsp"></jsp:include>
	<jsp:include page="dlg_isproduct.jsp"></jsp:include>
  </body>
<script>


///////////////////sample 样品////////////////
	var SAMPLE = {
	
		name : "",
		departmentId : 0,
		modelName : "",
		samplingTime : "",
		creater : "",
		
		//以下两个属性没有赋值，需要在提交的时候赋值；	
		status : "",
		creationTime : "",
		
		/*
		 *集成样品信息，突然想到，如果再刚开始的时候，就使用一个全局对象，把各个阶段的信息都存在这个对象中，是否可以呢；
		 *如果这么做，好像增加了耦合；后期我还打算把js代码封装到各自的js文件中呢；算了，开发阶段，不要想重构，把功能写出来最重要；
		 *另外一种思路，一步一步，递进式集成，每个对象都把他有的属性给出来，并传给下一个对象；对象之间交互的时候，直接传对象本身就好了，
		 *下一个对象根据自身需求还决定取上一个对象的属性；这种思路的缺点是浪费资源，优点是对象之间是独立的，没有耦合；
		 *两种思路我都想试一试，但是时间所限，就用第一种吧，这种思路代码量更少，运行时也节约资源；
		 */
		
		load : function()
		{
			//可以在此处检查sample的各属性值是否符合相应的数据格式；

		},
		
		//此函数获得用户选择的分析方法的id，返回id数组；
		getAnalysisItemsId : function()
		{
    		var checkboxList = $("[name='anaysisItemCheckbox']");
   	 		var analysisItemsId = [];
   	 		for(k in checkboxList)
   	 		{
        		if(checkboxList[k].checked)
        		{
        			analysisItemsId.push(checkboxList[k].value);
        		}
    		}
    		return analysisItemsId;
		},
		
		
		//分别在样品表和各个分析方法表中创建相应的记录；
		createSample : function(analysisItemsId)
		{
			var data = {};
  			data.analysisItemsId = analysisItemsId;
  			
  			data.name = this.name;
			data.departmentId = this.departmentId;
			data.modelName = this.modelName;
			data.samplingTime = this.samplingTime;
			data.creater = this.creater;

			data.status = 0;
			data.creationTime = new Date().getTime;
  			
			var req = JSON.stringify(data);
			
			var url = 'postSampleAndAnalysisItems.do';
			$.post(url, req, function (ans, status) {
  		  		var resp = $.parseJSON(ans);
  				if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}
						toastr.success("样品创建 success");
						
  				 });

		},
		
		
		id_tableNameMap : {},
		
		//从分析方法总表中获得各个分析方法分表的表名,参数是AnalysisItemsId[];返回map<id,tableName>；
		getAnalysisItemsTableName : function(analysisItemsId)
		{
			var data = {};
			
  			data.analysisItemsId = analysisItemsId;
			var req = JSON.stringify(data);
			var url = 'getAnalysisItemsTableName.do';
			$.post(url, req, function (ans, status) {
  		  		var resp = $.parseJSON(ans);
  				if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}
					
  				 	//ANALY.show_item_list(resp.result);
  				 	//toastr.success("获得分析项目表名 success");
  				 	var analysisItems = resp.result;
  				 	
  				 	for(var i = 0; i < analysisItems.length; i++)
  					{
  						var it = analysisItems[i];
  						var key = it.id.toString();
  						console.trace(typeof(key));
						SAMPLE.id_tableNameMap[key] = it.tablename;
  					}
  					
  					console.trace(analysisItems[0].tablename);

  				 });

		},
		
		getTableName : function()
		{
			
			var analysisItemsId = this.getAnalysisItemsId();
			this.getAnalysisItemsTableName(analysisItemsId);
			//迷之执行顺序，为何是先执行上述赋值语句，然后向下执行，最后才执行函数的调用（实际是函数调用中的post语句异步执行）？
			//时间原因，先不去搞清楚原因，先想解决办法；
			//可能是因为post和其他语句是异步执行的，如果想获得post返回的数据，只能在回调函数中处理；
			//确实是post异步的原因，以上注释就不改了，记录一下思维过程；
			
			for(var prop in this.id_tableNameMap)
			{
    			if(id_tableNameMap.hasOwnProperty(prop))
    			{
       				 console.trace('key is ' + prop +' and value is' + this.id_tableNameMap[prop]);
    			}
			}
		},
		
	
	
		submit : function()
		{
			var analysisItemsId = this.getAnalysisItemsId();

			this.createSample(analysisItemsId);

				
			console.trace("wancheng");
		},

		reset : function()
		{
	
		},
	
	
	
	
	
	
	};


///////////////////analysisItems 分析项目////////////////
	var ANALY = {};
	ANALY.panel = $("#analysisItems");
			
	
	//此处，data.modelId = null时，还需测试；
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
  				+ "<td> <input type='checkbox' name='anaysisItemCheckbox' value='##2' /> </td>"
  				+ "<td>" + it.id + "</td>"
  				+ "<td>" + it.name + "</td>"
  				+ "</tr>";

  			str = str.replace(/##1/g,it.id).replace(/##2/g, it.id);			
  			target.append(str);
  		}
  	}
  	
  	ANALY.clicked = function(dom)
  	{
  		//toastr.success("点击方法 成功");
  		//此处可以写为点击选择复选框；
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
  			var str = "<tr class='item' id1='##1' id2='##2' onclick='MODEL.clicked(this)'>"
  				+ "<td>" + it.id + "</td>"
  				+ "<td>" + it.name + "</td>"
  				+ "</tr>";

  			str = str.replace(/##1/g,it.id).replace(/##2/g,it.name); 			
  			target.append(str);
  		}
  	}
  	
  	MODEL.clicked = function(dom)
  	{
  		//toastr.success("点击牌号 成功");
  		$("#product").css('display','block');
  		$(dom).siblings().removeClass("item-selected");
  		$(dom).addClass("item-selected");
  		
  		var modelId = $(dom).attr("id1");
  		SAMPLE.modelName = $(dom).attr("id2");
  		
  		DLG_DETAILS.show(0, modelId);
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
  		$("#model").css('display','block');
  		$(dom).siblings().removeClass("item-selected");
  		$(dom).addClass("item-selected");
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
		//选择状态切换
		$(dom).siblings().removeClass("item-selected");
  		$(dom).addClass("item-selected");
	
  		var departmentId = $(dom).attr("id1");
  		
  		SAMPLE.departmentId = departmentId;

  		DLG_ISPRODUCT.show(departmentId);
  		//PROD.load(departmentId);
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
		//当页面加载的时候，创建
		

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
