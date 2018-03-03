<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 单行文本框输入 -->
<div class="modal fade" id="dlg-adddata">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">录入样品数据：</h4>
            </div>
            <div id="dlgOption-body" class="modal-body">
              		<div class="addData">
              			<table class="table" id = "sampleDate">       			
  							<thead>
  							<tr>
  							<th>项目</th>
  							<th>数据</th>
  							<th>单位</th>
  							</tr>
  							</thead>
  							<tbody>
  							
  							</tbody>
              			</table>
              		
					</div>
					<br>
					

					
            </div>
  
            <div class="modal-footer" style="text-align: right;">
                <button type="button" class="btn btn-default" onclick="DLG_ADDDATA.ok()"> 确定 </button>
            </div>
        </div>
    </div>
</div>

<script>
	var DLG_ADDDATA = 
	{
		dlg : $("#dlg-adddata"),

		show : function()
		{
			this.dlg.modal('show');
		},
		
		hide : function()
		{
			this.dlg.modal('hide');
		},
		
		
		
		
		data : {},
		
		valueOnChange : function(dom)
		{
			//当data的input发生改变时，检查该分析项目的其他data是否有值，如果都有值，那么使用公式重新计算result的值；
			/*
			1.获得该分析项目的所有data，选择全部同级元素的话，可以用nextAll()和preAll()，若先获得其上一级元素，然后在获得上一级元素的所有子元素，
				不知能否实现，try it；写的时候发现不用的分析项目竟然是同级的，先给每个分析项目加一个div；加了div之后，样式变得很难看,调了一会，
				稍微能看一点了，凑活着看吧；
			  */
			var dataParent = $(dom).parent();
			var dataDom = $(".data", dataParent);
			var dataDomArray = dataDom.toArray();
	
			for(i in dataDomArray)
			{
 				if(dataDomArray[i].value == "")
 				{
 					return;
 				}
			}
			//如果data都有值，那么重新计算result
			var itemName = dataParent.attr("id");
			var itemFemale = SAMPLE.femaleMap[itemName] 
			var resultDom = $("[id='result']",dataParent);
			
			//把公式itemFemale中的（"data"+i）用data的value替换掉；
			for(i in dataDomArray)
			{
				var str1 = "data" + (parseInt(i) + 1);
				var str2 =  dataDomArray[i].value;
				itemFemale = itemFemale.replace(str1, str2);
			}
			console.log(itemFemale);
			resultDom.attr("value", eval(itemFemale));
			
		},
		
		
		ok : function()
		{
			//将数据保存到数据库中；
			
			//准备数据：
			var itemArray = $(".itemDiv").toArray();
			
			var data = {};
			for(i in itemArray)
			{
				var item = {};
				item.sampleId = SAMPLE.selectedId;
				
				dataDomArray = $(".data", itemArray[i]).toArray();
				resultDom = $("[id='result']",itemArray[i]);
				conditionDomArray = $(".condition", itemArray[i]).toArray();
				
				for(j in dataDomArray)
				{
					var str1 = "data" + (parseInt(j) + 1);
					var str2 = dataDomArray[j].value;
					item[str1] = str2;
				}
				//此处result不需要检查其是否被恶意更改，保存的时候，取的是经公式计算的值，我还没搞清楚原因，以后重点看一下；
				item.result = resultDom.attr("value");
				item.analyser = "";
				
				//添加分析条件的取值；
				for(k in conditionDomArray)
				{
					var str1 = "condition" + (parseInt(k) + 1) +"Value";
					var str2 =  conditionDomArray[k].value;
					item[str1] = str2;
				}
				
				//该分析项目的状态，如果有结果，则status为1，如果没有结果，status为0；
				if(item.result != "")
				{//等下测试一下，如果result为" ";是什么样子的；经测试：只有空格，在input中不算字符；
					item.status = 1;
				}
				else
				{
					item.status = 0;
				}
				
				var tableName = $(itemArray[i]).attr("id1");
				
				data[tableName] = item;
			}
			//这边没有考虑分析项目更新后，样品status的影响，先不考虑了；暂时没打算写完；
			var req = JSON.stringify(data);
		  	
		  	//这个请求的Controller也放在SampleAnalysisItemsController中，更新多个表，放哪都不好，就放在总表中吧；
	 		var url = 'patchSampleAnalysisItems.do';
			$.post(url, req, function (ans, status) {
  		  		var resp = $.parseJSON(ans);
  				if(resp.errorCode != 0){ toastr.error("错误：" + resp.reason); return;}
						toastr.success("样品分析数据更新 success");							
						//SAMPLE.show_item_list(resp.result);
  				}); 
				DLG_ADDDATA.hide();

		},
		end_of_class: null
	};


</script>


