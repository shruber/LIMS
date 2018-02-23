<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<style>
	#header
	{
		width: 80%;
		margin: auto auto;
		pading: 10px
	}
	
	#header .logo
	{
		font-size: 30px;
	}
	
	#header .userinfo
	{
		float: right;
		right： 40px;
		top: 40px;
	}

	#header .logout
	{
		float: right;
		right： 40px;
		top: 40px;
	}

</style>


<div id = 'header'>

	<label> 实验室数据管理系统 </label>
	
	<div class='logout' >
		<a onclick="" > &nbsp 退出 </a>
	</div>
	
	<div class='userinfo'>
		未登录
	</div>


</div>

<script>
	var HEADER = {};
	
	//此处添加获取登录信息，可参考作业管理系统的header

	HEADER.div = $("#header");
	

	
	HEADER.logout = function()
	{
	
	};
	
	//初始化
	HEADER.div.ready(function(){
	
	});
</script>







