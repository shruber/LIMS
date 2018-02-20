<%@ page contentType="text/html;charset=UTF-8" language="java" %> 

	<!-- jquery 和 bootstrap 支持 -->
	<script src="jquery/jquery.js"></script>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <script src="bootstrap/js/bootstrap.min.js"></script>    
    <link href="bootstrap/css/toastr.min.css" rel="stylesheet" /> 
    <script src="bootstrap/js/toastr.min.js"></script> 

    <script src="js/AfUtility.js"></script>
    <link href="css/common.css" rel="stylesheet" /> 
    
    <script>
        //toastr初始化参数设置，若用默认值可以省略以下面代
	    toastr.options = {
	            "closeButton": false, //是否显示关闭按钮
	            "debug": false, //是否使用debug模式
	            "positionClass": "toast-top-center", //弹出窗的位置
	            "showDuration": "200", //显示的动画时间
	            "hideDuration": "1000", //消失的动画时间
	            "timeOut": "2000", //展现时间
	            "showEasing": "swing", //显示时的动画缓冲方式
	            "hideEasing": "linear", //消失时的动画缓冲方式
	            "showMethod": "fadeIn", //显示时的动画方式
	            "hideMethod": "fadeOut" //消失时的动画方式
	    }; 

    </script>
     
