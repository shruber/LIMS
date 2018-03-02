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
                <button type="button" class="btn btn-default" onclick="DLG_DETAILS.ok()"> 确定 </button>
            </div>
        </div>
    </div>
</div>

<script>
	var DLG_ADDDATA = 
	{
		dlg : $("#dlg-adddata"),
		
		modelId : {},
		departmentId : {},
		show : function()
		{
			this.dlg.modal('show');
		},
		
		hide : function()
		{
			this.dlg.modal('hide');
		},
		
		data : {},
		
		ok : function()
		{
			$("#analysisItems").css('display','block');
			SAMPLE.name = $(".name",this.dlg).val();
			SAMPLE.creater = $(".creater",this.dlg).val();
			SAMPLE.samplingTime = $(".samplingTime",this.dlg).val();
			DLG_DETAILS.hide();
			//console.trace(this.modelId);
			
			ANALY.load(this.modelId);
			
			$("#sample").css('display','block');

		},
		end_of_class: null
	};


</script>


