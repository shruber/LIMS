<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 单行文本框输入 -->
<div class="modal fade" id="dlg-details">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">填写样品详细信息：</h4>
            </div>
            <div id="dlgOption-body" class="modal-body">

              		<div class="form-group">
						<label>样品名：</label> <input type="text" class="m-input name"
							name="name" placeholder="...">
					</div>
					<br>
					<div class="form-group">
						<label>采样人：</label> <input type="text" class="m-input creater"
							name="creater" placeholder="...">
					</div>
			
					<br>
					<div class="form-group">
						<label>采样时间：</label> <input type="text" class="m-input samplingTime"
							name="samplingTime" placeholder="2017-10-1 14:11:12">
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
	var DLG_DETAILS = 
	{
		dlg : $("#dlg-details"),
		
		modelId : {},
		departmentId : {},
		show : function(mode,id)
		{
			if(mode = 0)
			{
				this.modelId = id;
			}else
			{
				if(mode = 1)
				{
					this.modelId = null;
					this.departmentId = id;
				}
			}
			
			$(".name",this.dlg).val("");
		    $(".creater",this.dlg).val("");
			$(".samplingTime",this.dlg).val("");
			//此处，采样时间应该初始化为当前时间，以后在改
			
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
			this.data.name = $(".name",this.dlg).val();
			this.data.creater = $(".creater",this.dlg).val();
			this.data.samplingTime = $(".samplingTime",this.dlg).val();
			DLG_DETAILS.hide();
			//console.trace(this.modelId);
			
			ANALY.load(this.modelId);
		},
		end_of_class: null
	};


</script>


