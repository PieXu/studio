<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>OAuth 2.0授权登录页面</title>
	<meta name="description" content="OAuth 2.0授权登录页面">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
	<style type="text/css">
		html,body,div,p,form,label,ul,li,img,em,h3{margin:0;padding:0;border:0;list-style:none;font-style:normal;font-weight:normal;}
		a{text-decoration:none;}
		body{font-family:Microsoft YaHei,Verdana,Arial,SimSun;color:#666;font-size:14px;background:#fff; overflow:hidden}
		.block, #block{display:block;}
		.none, #none{display:none;}
		.login{width:100%;}
		.login .headerTop{width:100%;height: 80px;background:#52b7ed;}
		.login .headerTop .logo{width:1000px;margin:0 auto;}
		.login .headerTop .logo img{margin-top:20px;margin-left:180px;width: 150px;}
		.main{width:1000px;margin:0 auto;overflow: hidden;height: auto;clear: both;}
		.main .mainLeft{float: left;width:50%;border-right:1px dotted #ccc;margin-top:30px;padding-bottom:100px;}
		.main .mainLeft h3{width:70%;border-bottom:2px solid #ccc;padding:0 10%;font-family: 600;}
		.main .mainLeft h3 span{display:inline-block;width:6.5em;font-size:18px;color: #333;line-height:50px;border-bottom:4px solid #666;margin-bottom: -2px;font-family: 600;text-align: center;}
		.main .mainLeft input{width:50%;padding:10px;font-size:16px;border:1px solid #ccc;border-radius: 3px;margin:20px 0 0 50px;}
		.main .mainLeft input[type="text"]{margin-top:60px;}
		.main .mainLeft .a{display: block;padding:10px 15px;width:20%;text-align:center;font-size:18px;color:#fff;background: #88ce2f;border-radius: 3px;margin:20px 0 0 50px;text-decoration: none}
		.main .mainRight{float: right;width:45%;margin-top:30px;}
		.mainRight p, .mainRight ul li{width:100%;padding: 10px 0;border-bottom: 1px dotted #ccc;font-size:14px;color: #666; }
		.mainRight p a{color:#52b7ed;}
		.mainRight p a:hover{text-decoration:none;}
		.mainRight p input, .mainRight ul li input{margin-right:10px;cursor:not-allowed;}
		.mainRight ul li{border:none;}
		.mainRight .agreement{margin-top:10px;border:none;}
		.mainRight .code{text-align: center;}
		.mainRight img{margin:10px auto;width: 150px;}
		.delete  { display: none; position: absolute; width: 16px; height: 16px; margin: 73px 0 0 -25px; background: url(data:image/jpeg;base64,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AAAAAAAAAABUb3AgVW50RiNSbHQAAAAAAAAAAAAAAABTY2wgVW50RiNQcmNAWQAAAAAAAAAAABBjcm9wV2hlblByaW50aW5nYm9vbAAAAAAOY3JvcFJlY3RCb3R0b21sb25nAAAAAAAAAAxjcm9wUmVjdExlZnRsb25nAAAAAAAAAA1jcm9wUmVjdFJpZ2h0bG9uZwAAAAAAAAALY3JvcFJlY3RUb3Bsb25nAAAAAAA4QklNA+0AAAAAABAAkAAAAAEAAgCQAAAAAQACOEJJTQQmAAAAAAAOAAAAAAAAAAAAAD+AAAA4QklNA/IAAAAAAAoAAP///////wAAOEJJTQQNAAAAAAAEAAAAHjhCSU0EGQAAAAAABAAAAB44QklNA/MAAAAAAAkAAAAAAAAAAAEAOEJJTScQAAAAAAAKAAEAAAAAAAAAAjhCSU0D9QAAAAAASAAvZmYAAQBsZmYABgAAAAAAAQAvZmYAAQChmZoABgAAAAAAAQAyAAAAAQBaAAAABgAAAAAAAQA1AAAAAQAtAAAABgAAAAAAAThCSU0D+AAAAAAAcAAA/////////////////////////////wPoAAAAAP////////////////////////////8D6AAAAAD/////////////////////////////A+gAAAAA/////////////////////////////wPoAAA4QklNBAgAAAAAABAAAAABAAACQAAAAkAAAAAAOEJJTQQeAAAAAAAEAAAAADhCSU0EGgAAAAADPwAAAAYAAAAAAAAAAAAAACgAAAAoAAAABQBsAG8AZwBpAG4AAAABAAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAACgAAAAoAAAAAAAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAEAAAAAEAAAAAAABudWxsAAAAAgAAAAZib3VuZHNPYmpjAAAAAQAAAAAAAFJjdDEAAAAEAAAAAFRvcCBsb25nAAAAAAAAAABMZWZ0bG9uZwAAAAAAAAAAQnRvbWxvbmcAAAAoAAAAAFJnaHRsb25nAAAAKAAAAAZzbGljZXNWbExzAAAAAU9iamMAAAABAAAAAAAFc2xpY2UAAAASAAAAB3NsaWNlSURsb25nAAAAAAAAAAdncm91cElEbG9uZwAAAAAAAAAG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YWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiIHhtbG5zOmRjPSJodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLyIgeG1sbnM6cGhvdG9zaG9wPSJodHRwOi8vbnMuYWRvYmUuY29tL3Bob3Rvc2hvcC8xLjAvIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9IjAxOTZFRTdEN0ZFNEM3ODk1Q0JFMzkzNzBDNDc5MjgzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjI1MjM2OUU4RTExN0U2MTFCMjRCQTg3RTczQkEyNDBEIiB4bXBNTTpPcmlnaW5hbERvY3VtZW50SUQ9IjAxOTZFRTdEN0ZFNEM3ODk1Q0JFMzkzNzBDNDc5MjgzIiBkYzpmb3JtYXQ9ImltYWdlL2pwZWciIHBob3Rvc2hvcDpMZWdhY3lJUFRDRGlnZXN0PSI1NzUzQzFEMTkyNzAyNEREOUI2Q0MzNEZGRDQxRTcwNiIgcGhvdG9zaG9wOkNvbG9yTW9kZT0iMyIgeG1wOkNyZWF0ZURhdGU9IjIwMTYtMDUtMTFUMTU6NTc6MzcrMDg6MDAiIHhtcDpNb2RpZnlEYXRlPSIyMDE2LTA1LTEyVDEwOjM1OjM3KzA4OjAwIiB4bXA6TWV0YWRhdGFEYXRlPSIyMDE2LTA1LTEyVDEwOjM1OjM3KzA4OjAwIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIj4gPHhtcE1NOkhpc3Rvcnk+IDxyZGY6U2VxPiA8cmRmOmxpIHN0RXZ0OmFjdGlvbj0ic2F2ZWQiIHN0RXZ0Omluc3RhbmNlSUQ9InhtcC5paWQ6NjcyMDVGQUM0RTE3RTYxMUE0NEJFMTNDQjE0MDM0QUMiIHN0RXZ0OndoZW49IjIwMTYtMDUtMTFUMTY6MDY6MjIrMDg6MDAiIHN0RXZ0OnNvZnR3YXJlQWdlbnQ9IkFkb2JlIFBob3Rvc2hvcCBDUzYgKFdpbmRvd3MpIiBzdEV2dDpjaGFuZ2VkPSIvIi8+IDxyZGY6bGkgc3RFdnQ6YWN0aW9uPSJzYXZlZCIgc3RFdnQ6aW5zdGFuY2VJRD0ieG1wLmlpZDoyNTIzNjlFOEUxMTdFNjExQjI0QkE4N0U3M0JBMjQwRCIgc3RFdnQ6d2hlbj0iMjAxNi0wNS0xMlQxMDozNTozNyswODowMCIgc3RFdnQ6c29mdHdhcmVBZ2VudD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHN0RXZ0OmNoYW5nZWQ9Ii8iLz4gPC9yZGY6U2VxPiA8L3htcE1NOkhpc3Rvcnk+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIDw/eHBhY2tldCBlbmQ9InciPz7/7gAhQWRvYmUAZEAAAAABAwAQAwIDBgAAAAAAAAAAAAAAAP/bAIQAAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQEBAQICAgICAgICAgICAwMDAwMDAwMDAwEBAQEBAQEBAQEBAgIBAgIDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwMD/8IAEQgAKAAoAwERAAIRAQMRAf/EAIEAAAMBAQAAAAAAAAAAAAAAAAUGCAQKAQEAAAAAAAAAAAAAAAAAAAAAEAACAwEAAgMAAAAAAAAAAAAFBgIDBAEAQBEhEhEAAgEDAwIFAwUAAAAAAAAAAQIDERIEACETMSJBYXEUBVFCUoGx0TIjEgEAAAAAAAAAAAAAAAAAAABA/9oADAMBAQIRAxEAAADvVJ4F8ZSgCejSDRgA4FN5TJN4FD5uBwbF4o4nsWBhKEP/2gAIAQIAAQUA9n//2gAIAQMAAQUA9n//2gAIAQEAAQUAYGDECxG2osd7HRdPwM2lhFq2yY2DI5kpFWFUW8p+epbJZyzMsZQY5WKahxoh3sty9MnWVhGXa2qslUYy/epwDTCmVFmyAI3NZawu2tOBkGLgnYUNsS7lP4zykSA3fifwKVzBfqytZAGT/9oACAECAgY/AE//2gAIAQMCBj8AT//aAAgBAQEGPwCbJyJQWoY4cdY5HkealfsVyB3DcinnpIJ8ngxYm5uASSRyVuYAdgAIoBpiJJpEuqI/dzJaBtUFioPTw0nFMxxEa+aBneUzAgAxB2raQE6nt366M8FIZEYh8Vq8kYCRsT0CsKv1Wo1lTNGQcSmKlZnCNxvJJW1CRU8vXWUi53tMsBmGKsbS8gtWkgdxb3GopX7dJ8U8M0k7kNG4MC/4NI6KWCyUHcjdd/LWJJ7lJPkZLXkgBBsjOwqwqlQynxrrDkhcxrNOkErFwU42Ba0qGJJIVt/LWWSasZ3u9a/xrGf4q9ckOneuyutd4mNQBStd9t9RSTGCD5aSIAkrDI4YVoAKsaVqa9NTn5RpZGEpsusCcVzWWmMlBVgdj3agKnjsyoZRuSdo51P9a03dfPf11kIysIco+6ifqlsjyRgFlqqmsXQ76yDNirLMzPJDKyk0LRooUUBbYrWvTfQ+X5nMiMFjiuPGIg7MFobfzOsNfaiHOjlDSsF7pHAFQWCjt8yaawo8TGLxLOr5dJIQsaLS4lnkCtt4Lvo406qDcWV6dw22APr+mmWVBJjUvikE2OW4yzKF4lk5drPx0Da1D0qpH7jSpjYjccklj5PNjgRrRGItaQPWjV2HjpYUjDThi0kxAuZmSNSPrQWDX//Z) no-repeat;background-size:100%; cursor: pointer;}
		.mainLeft input[type="text"]::-ms-delete { display: none; }
		.main .mainLeft input[type="text"]:valid + .delete  { display: inline; }
	</style>
</head>
<body>
	<div class="login">
		<div class="headerTop">
			<div class="logo">
				<img src="images/logo.png" />
			</div>
		</div>
		<form action="http://localhost:8088/studio/oauth2_server/authorize.do" method="post" id="auth_from">
			<!-- 隐藏表单参数 -->
			<input type="hidden" id="grant_type" name="grant_type" value="${grant_type }" />
			<input type="hidden" id="client_id" name="client_id" value="${client_id}">
			<input type="hidden" id="state" name="state" value="${state }"/>		          
	  		<input type="hidden" name="redirect_uri" value="${redirect_uri}"/>
			<div class="main">
				<div class="mainLeft">
					<h3><span>账号密码登录</span></h3>
					<input class="text" type="text" placeholder="请输入用户名" required name="username" value="">
					<a class="delete" id="delete" href="javascript:void(0);"></a>
					<input type="password" name="password" required placeholder="请输入密码">
					<a class="a" href="javascript:void(0);" id="submit_a">授权并登录</a>
				</div>
				<div class="mainRight">
					<p>${clientName }已有超过千万用户使用小海鱼账号登录</p>
					<p><a href="javascript:void(0);">${clientName }</a> 将获得以下权限</p>
					<p><input type="checkbox" >全选</p>
					<ul>
						<li><input type="checkbox" checked="checked" readonly name="scope" value="userinfo">获得您的昵称，头像，性别</li>
						<li><input type="checkbox"  name="scope" value="share">分享内容到QQ空间</li>
						<li><input type="checkbox"  name="scope" value="write">读取您在小海鱼网站的更多信息</li>
					</ul>
					<p class="agreement">授权后表明你已同意<a href="javascript:void(0);">小海鱼授权登录服务协议</a></p>
					<div class="code">
						<img src="images/code.jpg">
					</div>
				</div>
			</form>
		</div>
	</div>
	
<script>
$(document).ready(function(){
  $("#delete").click(function(){
    $('.text').val('');
  });
  
  // 提交人阿正
  $("#submit_a").click(function(){
	  if($("#auth_from").valid()){
		 var index = layer.confirm('确定要授权登录该网站吗？', {
			  btn: ['确定','取消'] //按钮
			}, function(){
				$("#auth_from").submit();
		});
	  }
  });
  
});



</script>
</body>
</html>