<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>OAuth 2.0认证失败页面</title>
	<style type="text/css">
	html,body,div,p,form,label,ul,li,img,em,h3{margin:0;padding:0;border:0;list-style:none;font-style:normal;font-weight:normal;}
	a{text-decoration:none;}
	body{font-family:Microsoft YaHei,Verdana,Arial,SimSun;color:#666;font-size:14px;background:#fff; overflow:hidden}
	.block, #block{display:block;}
	.none, #none{display:none;}
	.login{width:100%;}
	.login .headerTop{width:100%;height: 80px;background:#52b7ed;}
	.login .headerTop .logo{width:1000px;margin:0 auto;}
	.login .headerTop .logo img{margin-top:20px;width: 150px;margin-left:180px}
	.main{width:1000px;margin:0 auto;overflow: hidden;height: auto;clear: both;}
	.main .mainRight .a{display: block;padding:10px 0;width:20%;text-align:center;font-size:18px;color:#fff;background: #88ce2f;border-radius: 3px;margin:20px 0 0 0;}
	.main .mainRight{float: right;width:100%;margin-top:30px;}
	.mainRight p, .mainRight ul li{width:100%;padding: 10px 0;border-bottom: 1px dotted #ccc;font-size:14px;color: #666; }
	.mainRight p a{color:#52b7ed;}
	.mainRight p a:hover{text-decoration:underline;}
	.mainRight p input, .mainRight ul li input{margin-right:10px;cursor:not-allowed;}
	.mainRight ul li{border:none;}
	.mainRight .agreement{margin-top:10px;border:none;}
	.mainRight .code{text-align: center;}
	.mainRight img{margin:10px auto;width: 150px;}
	</style>
</head>

<body>
	<div class="login">
		<div class="headerTop">
			<div class="logo">
				<img src="images/logo.png" />
			</div>
		</div>
		<div class="main">
			
			<div class="mainRight">
				<p style="font-size:18px">认证提示</p>
				<p>您当前在小海鱼数据中心数据认证失败，请联系 <a target="_blank" href="http://www.baidu.com"> 系统管理员 </a>或者稍后再试</p>
				<ul>
					<li>(error:${OAuth2FailedCode}) <b>${OAuth2FailedMessage }</b></li>
				</ul>
				<a class="a" href="javascript:history.back();">返 回</a>
				<p class="agreement">授权后表明你已同意<a href="#">小海鱼授权登录服务协议</a></p>
				<div class="code">
					<img src="images/code.jpg">
				</div>
			</div>
		</div>
	</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
<p>版权所有：CopyRight @IvanHsu&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;技术支持：（QQ）3582941697 </p>
</div>
</body>
</html>