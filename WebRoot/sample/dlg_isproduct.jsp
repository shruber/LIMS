<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 单行文本框输入 -->
<div class="modal fade" id="dlg-isproduct">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">是否选择产品：</h4>
            </div>
            <div id="dlgOption-body" class="modal-body">
				<button type="button" class="btn btn-default" onclick="DLG_ISPRODUCT.ok1()"> 选择产品 </button>
                <button type="button" class="btn btn-default" onclick="DLG_ISPRODUCT.ok2()"> 不选择产品，直接填写样品详情 </button>
            </div>
  
            <div class="modal-footer" style="text-align: right;">
                
            </div>
        </div>
    </div>
</div>

<script>
	var DLG_ISPRODUCT = 
	{
		dlg: $("#dlg-isproduct"),
		
		departmentId : {},
		
		show : function(departmentId)
		{
			this.departmentId = departmentId;
			
			this.dlg.modal('show');
		},
		
		hide : function()
		{
			this.dlg.modal('hide');
		},
		
		
		ok1 : function()
		{
			this.hide();
			//显示产品列表
			$("#product").css('display','block');
			PROD.load(this.departmentId);

		},
		
		ok2 : function()
		{
			this.hide();
			
			DLG_DETAILS.show(1,this.departmentId);

		},
		
		end_of_class: null
	};


</script>


