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
		
		
		.analysisItem
		{
			margin-top:5px;
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
	<jsp:include page="dlg_adddata.jsp"></jsp:include>
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
	
	SAMPLE.femaleMap = {};
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

		//从sampleAnalysisItems表中获取分析项目表名，然后在defineAnalysisItems表中查询其分析项目的左值，在分析项目分表中使用sampleId查找其记录的右值
		//都返回map，由前端对左值、右值进行一一对应；
		
		var data = {};
  		data.sampleId = SAMPLE.selectedId;
		var req = JSON.stringify(data);

		var url = 'getAnalysisItemsBySampleId.do';
		$.post(url, req, function (ans, status) {
  		  	var resp = $.parseJSON(ans);
  			if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}
					toastr.success("分析项目查询 success");					
					//SAMPLE.show_item_list(resp.result);
					var result = resp.result;
					var analysisItemsDetailMap = result.analysisItemsDetailMap;
					var analysisItemsValueMap = result.analysisItemsValueMap;
					SAMPLE.show_analysisItemsDetail_list(analysisItemsDetailMap, analysisItemsValueMap);
					DLG_ADDDATA.show();
	
  			});
		toastr.success("success");
	}
	
	SAMPLE.show_analysisItemsDetail_list = function(analysisItemsDetailMap, analysisItemsValueMap)
	{
		//这些信息显示在录入样品数据的弹出框中
 		var target = $("tbody", DLG_ADDDATA.dlg);
  		target.html("");
  		
		for(var i in analysisItemsDetailMap)
		{
			var itemsStr = "";
			console.log(i,":",analysisItemsDetailMap[i]);
			console.log(i,":",analysisItemsValueMap[i]);
			//取value时，先判断有没有该属性，再取，如果该属性没有赋值的话，该属性是取不出来的；
			
			var items = analysisItemsDetailMap[i];
			var itemsValue = analysisItemsValueMap[i];
			var itemName = items.name;
			SAMPLE.femaleMap[itemName] = items.female;
			/*显示分为三列，条目，数值，单位；
			对于不同的分析方法，显示的条目也不一样；每个条目，加一个判断；
			显示的条目：方法名（name)，分析条件（conditions），分析条件单位（conditionUnits），数据（dataNumber），
					数据单位（dataUnits），结果；
			先检查有没有分析条件，然后再显示；数据单位也要检查有没有；数据也要检查有没有（有可能没有数据，只有一个结果）；
			每个条目的显示，做成一个字符串，如果有则加上这个字符串，如果没有，就不加；
			*/
			
			//给每个分析项目加一个div，这样使用的时候也方便；
			{
				var str = "<div  class='itemDiv' id='##1' id1='##2'>";
				str = str.replace(/##1/g, items.name).replace(/##2/g, items.tableName);
				console.log(str);
				itemsStr = itemsStr + str;
			}


			//显示分析方法的名称；
			{
				var str = "<tr class='analysisItem' id1='##1' id2='##2' style='width: 100%;margin: 10px 0px;' >"
						+ "<td>" +  items.name + "</td>"
						+ "<td>" + "" + "</td>"
						+ "<td>" + "" + "</td>"
  						+ "</tr>"
  						+ "<br>";
				str = str.replace(/##1/g, items.name).replace(/##2/g, items.name);
				//console.log(str);
				itemsStr = itemsStr + str;
			}

			//判断items中是否有conditions字段；默认是有conditions就有conditionUnits，定义方法的时候，即使没有单位，也要保存一个空字符串
			console.log(items.conditions != undefined);
			if(items.conditions != undefined)
			{
				//如果该条目有分析条件，那么，解析出该分析条件；
				var conditions = items.conditions;
				var conditionUnits = items.conditionUnits;
				var conditionArray = conditions.split("#");
				var conditionUnitArray = conditionUnits.split("#");
				for(var j = 0;j < conditionArray.length; j++)
				{
					//解析出分析条件后，写出这一行的HTML；id1值为分析方法的name（items.name），id2为自身（condition + j）
					var str = "<tr class='analysisItem' id1='##1' id2='##2' onclick='' >"
						+ "<td>" + conditionArray[j] + "</td>"
						+ "<td>" + "<input type='text' class='condition' id='##3' value ='##4' style='margin: 10px;'/>" + "</td>"
						+ "<td>" + conditionUnitArray[j] + "</td>"
  						+ "</tr>"
						+ "<br>";
					str = str.replace(/##1/g, items.name).replace(/##2/g, "condition" + j);
					//##3需要用conditionValue替换掉；
					var valueStr = "condition" + (j+1) + "Value";	//以后数据库任何编号从0开始；
					str = str.replace(/##3/g, valueStr)
					if(itemsValue[valueStr] != undefined)
					{
						str = str.replace(/##4/g, itemsValue[valueStr]);
					}
					
					console.log(str);
					itemsStr = itemsStr + str;
				}
			}
			
			//判断是否有data字段；
			console.log(items.dataNumber > 0);
			if(items.dataNumber > 0)
			{
				//如果数据个数大于0，那么有几个就显示几个；
				var dataNumber = items.dataNumber;
				for(var j = 0;j < dataNumber; j++)
				{
					var str = "<tr class='analysisItem' id1='##1' id2='##2' onclick='' >"
						+ "<td>" + "数据" + (j+1) + "</td>"
						+ "<td>" + "<input type='text' class='data' name='input' id='##3' value='##4' " + 
							"onchange='DLG_ADDDATA.valueOnChange(this)' style='margin: 10px;'/>" + "</td>"						
						+ "<td>" + "##unit" + "</td>"
  						+ "</tr>"
  						+ "<br>";
					str = str.replace(/##1/g, items.name).replace(/##2/g, "data" + j);
					if(items.dataUnit == undefined)
					{
						str = str.replace(/##unit/g, "");
					}
					else
					{
						str = str.replace(/##unit/g, items.dataUnit);
					}
					
					//##3 ,默认值为dataValue；
					var valueStr = "data" + (j+1);	//以后数据库任何编号从0开始；
					str = str.replace(/##3/g, valueStr)
					if(itemsValue[valueStr] != undefined)
					{
						str = str.replace(/##4/g, itemsValue[valueStr]);
					}
					else
					{
						str = str.replace(/##4/g, "");
					}
					
					console.log(str);
					itemsStr = itemsStr + str;
				}
			}
			
			//加上后续的一些显示，比如结果,div的后段；加个大括号，是为了所谓的变量作用域，还有上下文看起来很规整；
			{
				var str = "<tr class='analysisItem' id1='##1' id2='result' onclick='' >"
						+ "<td>" + "结果" + "</td>"
						+ "<td>" + "<input type='text' name='input' id='##3' value='##4' style='margin: 10px;'/>"
							 + "</td>"
						+ "<td>" + "##unit" + "</td>"
  						+ "</tr>"
  						+ "<br>";
				str = str.replace(/##1/g, items.name);
				if(items.dataUnit == undefined)
				{
					str = str.replace(/##unit/g, "");
				}
				else
				{
					str = str.replace(/##unit/g, items.dataUnit);
				}
				//##3用一个input替代，初始值为resultValue，给data的input加一个change事件，如果数据发生改变（且data都有值），那么，result重新计算；
				var valueStr = "result";
				str = str.replace(/##3/g, valueStr)
				if(itemsValue[valueStr] != undefined)
				{
						str = str.replace(/##4/g, itemsValue[valueStr]);
				}
				else
				{
					str = str.replace(/##4/g, "");
				}
						
				console.log(str);
				itemsStr = itemsStr + str;
			}
			
			//div的后段
			{
				var str = "</div>";
				itemsStr = itemsStr + str;
			}
			
			
			
			console.log(itemsStr);
			target.append(itemsStr);

		}

	};

	
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
