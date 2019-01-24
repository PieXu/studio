<%@page contentType="text/html;charset=utf-8" language="java"%>
<!doctype html>
<html lang="en">
<head>
<title>登录页面</title>
<base href="${rc.contextPath}/">
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta name="MobileOptimized" content="320">
<link href="css/login/css.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/3.0/layer.js"></script>
<script type="text/javascript">
$(function(){
	//得到焦点
	$("#password").focus(function(){
		$("#left_hand").animate({
			left: "150",
			top: " -38"
		},{step: function(){
			if(parseInt($("#left_hand").css("left"))>140){
				$("#left_hand").attr("class","left_hand");
			}
		}}, 2000);
		$("#right_hand").animate({
			right: "-64",
			top: "-38px"
		},{step: function(){
			if(parseInt($("#right_hand").css("right"))> -70){
				$("#right_hand").attr("class","right_hand");
			}
		}}, 2000);
	});
	//失去焦点
	$("#password").blur(function(){
		$("#left_hand").attr("class","initial_left_hand");
		$("#left_hand").attr("style","left:100px;top:-12px;");
		$("#right_hand").attr("class","initial_right_hand");
		$("#right_hand").attr("style","right:-112px;top:-12px");
	});
});
</script>
</head>
<body>
	<div class="top_div"></div>
		<form id="loginForm" class="login-form"  action="#" method="post">
			<div class="content_div">
				<div class="owl_div">
					<div class="tou"></div>
					<div class="initial_left_hand" id="left_hand"></div>
					<div class="initial_right_hand" id="right_hand"></div>
				</div>
				<p class="u_input">
					<span class="u_logo"></span>         
					<input class="ipt" type="text" id="loginName" name="loginName" placeholder="请输入用户名或邮箱" value="${user.loginName }"> 
			    </p>
				<p style="position: relative;">
					<span class="p_logo"></span>         
					<input class="ipt" id="password" name="password" type="password" placeholder="请输入密码" value="">   
			    </p>
				<div class="btn_div">
					<p>
						<!-- <span style="float: left;vertical-align: middle;color: rgb(204, 204, 204); "><input type="checkbox" name="rememberMe" id="rememberMe" value="true"/>&nbsp;记住密码</span> --> 
				        <span style="float: right;">
				        	<a style="color: rgb(204, 204, 204); margin-right: 10px;"href="#">注册</a>  
				        	<button class="btn_blue"  type="button" id="login">登录</button> 
				        </span> 
				    </p>
			    </div>
		    </div>
    	</form>
    <br/>
	<div class="copyright_div">
		<a style="color:#666;" href="javascript:void(0)">CopyRight @IvanHsu&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术支持：（QQ）3582941697 </a>
	</div>
<script type="text/javascript">
//登录操作
   $('#login').click(function(){
       var username = $('#loginName').val();
       var password = $('#password').val();
       if(username == '') {
       	layer.msg('用户名不能为空', function(){
       		$('#loginName').focus();
      		});
           return false;
       }
       if(password == '') {
       	layer.msg('请输入登录密码', function(){
       		$('#password').focus();
      		});
           return false;
       }
       var load = layer.load(0,{shade:0.5});
       $.ajax({
		   type: "POST",
		   url: "submitLogin.do",
		   data: $("#loginForm").serialize() ,
		   success: function(data){
			  // //data = $.parseJSON( data );
			   if( data.result == "success"){
				   window.location.href= "main.do";
			   }else{
				   layer.close(load);
				   layer.msg(data.message);
			   }
		   }
	  });
   });
</script>
</body>
</html>

