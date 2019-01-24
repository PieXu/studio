<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
<header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top">
		<div class="container-fluid cl"> 
			<a class="logo navbar-logo f-l mr-10 hidden-xs" href="#">业务后台管理</a> 
			<span class="logo navbar-slogan f-l mr-10 hidden-xs">(Beta v1.0)</span> 
			<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
			<nav class="nav navbar-nav">
				<ul class="cl">
					<li <c:if test="${empty _currentRoot}">class="current"</c:if>>
						<a href="security/changeRootMenu.do">
							<i class="icon Hui-iconfont" style="font-size:20px">&#xe625;</i>&nbsp;<b>首页 </b>
						</a>
					</li>
					<!-- 一级菜单的循环显示  -->
					<c:if test="${!empty rootLinkList }">
						<c:forEach items="${rootLinkList }" var="res">
							<li  <c:if test="${!empty _currentRoot and _currentRoot eq res.id }">class="current"</c:if>>
								<a href="security/changeRootMenu.do?menuId=${res.id }">
									<i class="icon Hui-iconfont ${res.classStyle }" style="font-size:20px"></i> <b>${res.resName }</b>
								</a>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</nav>
			<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
				<ul class="cl">
					<!-- <li>超级管理员</li> -->
					<li class="dropDown dropDown_hover"> <a href="javascript:void(0);" class="dropDown_A">${loginUser.userName} <i class="Hui-iconfont">&#xe6d5;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<c:if test="${loginUser.loginName ne 'admin' }"><!-- admin 不显示 基本信息查看  -->
								<li><a href="javascript:;" onClick="myselfinfo()">基本信息</a></li>
							</c:if>
							<li><a href="javascript:;" onClick="changePass()">修改密码</a></li>
							<li><a href="javascript:void();"  onclick="logout();">退出系统</a></li>
						</ul>
					</li>
					<li id="Hui-msg"> <a href="security/loadMenuByCode.do?msgCode=MESSAGE_LIST" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
					<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="更換主題"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
						<ul class="dropDown-menu menu radius box-shadow">
							<li><a href="javascript:;" data-val="default" title="默认（蓝色）">默认（蓝色）</a></li>
							<li><a href="javascript:;" data-val="black" title="黑色">黑色</a></li>
							<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
							<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
							<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
							<li><a href="javascript:;" data-val="orange" title="橙色">橙色</a></li>
						</ul>
					</li>
				</ul>
			</nav>
		</div>
	</div>
</header>
<div id="user_info" style="display: none">
	<div class="cl pd-20" style=" background-color:#5bacb6">
	  <img class="avatar size-XL l" src="images/user_header.jpg">
	  <dl style="margin-left:80px; color:#fff">
	    <dt><span class="f-18">${loginUser.userName}</span></dt>
	    <dd class="pt-10 f-12" style="margin-left:0">
	    	<c:if test="${!empty loginUser.comments }">${loginUser.comments }</c:if>
	    	<c:if test="${empty loginUser.comments }">这家伙很懒，什么也没有留下</c:if>
    	</dd>
	  </dl>
	</div>
	<div class="pd-20">
	  <table class="table">
	    <tbody>
	      <tr>
	        <th class="text-r" width="80">性别：</th>
	        <td><page:idToName dicId="${loginUser.gender }"/> </td>
	      </tr>
	       <tr>
	        <th class="text-r" width="80">年龄：</th>
	        <td><page:idToName dicId="${loginUser.age }"/> </td>
	      </tr>
	      <tr>
	        <th class="text-r">用户类型：</th>
	        <td><page:idToName dicId="${loginUser.userType }"/> </td>
	      </tr>
	      <tr>
	        <th class="text-r">注册时间：</th>
	        <td><fmt:formatDate value="${loginUser.regTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	      </tr>
	      <tr>
	        <th class="text-r">最近登录：</th>
	        <td><fmt:formatDate value="${loginUser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
	      </tr>
	    </tbody>
	  </table>
	</div>	
</div>
<article class="cl pd-20" id="change_pass" style="display: none">
	<form action="user/changPass.do" method="post" class="form form-horizontal" id="form-changPass">
		<input type="hidden" name="id" value="${loginUser.id }"/>
		<input type="submit" id="sub_btn" value="提 交" style="display:none">
		<!-- -密码不能在编辑处修改 -->
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="password" class="input-text" autocomplete="off" value="" placeholder="输入新密码" id="password" name="password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="password" class="input-text" autocomplete="off" value=""  placeholder="确认新密码" id="password2" name="password2">
			</div>
		</div>
	</form>
</article>
<!-- 退出登录提交的form -->
<form action="logout.do" method="post" id="_form-logout" style="display: none"></form>
<script>
// 退出系统
function logout()
{
	layer.confirm('确定要退出系统吗？', {
		btn: ['确定','取消'] //按钮
		}, function(){
			$("#_form-logout").submit();
	});
}

/*个人信息*/
function myselfinfo(){
	layer.open({
		type: 1,
		area: ['450px','380px'],
		fix: false, //不固定
		maxmin: false,
		shade:0.5,
		title: '查看信息',
		content: $("#user_info")
	});
}

//修改密码
function changePass(){
	layer.open({
	  type: 1,
	  area: ['450px','240px'],
	  shade:0.4,
	  title: "修改密码", //不显示标题
	  content: $('#change_pass'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
	  btn: ['修改', '关闭']
	  ,yes: function(){
	   	$("#sub_btn").click();
	  }
	});
}
$("#form-changPass").validate({
	rules:{
		password:{
			required:true,
		},
		password2:{
			required:true,
			equalTo: "#password"
		},
	},
	onkeyup:false,
	focusCleanup:true,
	success:"valid",
	submitHandler:function(form){
		layer.confirm('确定要修改密码吗？', {
			btn: ['确定','取消'] //按钮
			}, function(){
				$.ajax({
				   type: "POST",
				   url: "user/changPass.do",
				   data: $(form).serialize() ,
				   success: function(data){
					  // data = $.parseJSON( data )
					   if( data.result == "success"){
						   layer.msg(data.message,{icon:1,time:1000});
						   layer.closeAll();
					   }else{
						   layer.msg(data.message,{icon:2,time:1000});
					   }
				   }
			});
		});
	}
});
</script>

