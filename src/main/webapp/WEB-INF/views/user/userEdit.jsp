<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<article class="cl pd-20">
	<form action="user/saveUser.do" method="post" class="form form-horizontal" id="form-admin-add">
	<input type="hidden" name="id" value="${user.id }"/>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>用户名：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${user.userName }" placeholder="请填写用户名" id="userName" name="userName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>登录名：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${user.loginName }" placeholder="请填写登录名" id="loginName" name="loginName">
			</div>
		</div>
		<c:if test="${empty user.id }">
			<!-- -密码不能在编辑处修改 -->
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>初始密码：</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="password" class="input-text" autocomplete="off" value="${user.password }" placeholder="密码" id="password" name="password">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>确认密码：</label>
				<div class="formControls col-xs-9 col-sm-10">
					<input type="password" class="input-text" autocomplete="off" value="${user.password }"  placeholder="确认新密码" id="password2" name="password2">
				</div>
			</div>
		</c:if>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>性别：</label>
			<div class="formControls col-xs-9 col-sm-10 skin-minimal" style="display: inline-block;">
				<page:radio name="gender" dicList="${genderList }" selectedVal="${user.gender }"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">年龄：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${user.age }" placeholder="请填写年龄" id="age" name="age">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>用户类型：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<span class="select-box" style="width:150px;">
					<page:select name="userType" dicList="${userTypeList}" styleClass="select" selectedVal="${user.userType }" />
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">用户状态：</label>
			<div class="formControls col-xs-9 col-sm-10 skin-minimal" style="display: inline-block;">
				<page:radio name="status" dicList="${userStateList }" selectedVal="${user.status }"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">备注：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<textarea name="comments" cols="" rows="" class="textarea valid"  placeholder="说点什么...100个字符以内" dragonfly="true">${user.comments }</textarea>
			</div>
		</div>
		<input type="submit" id="sub_btn" value="提 交" style="display:none">
	</form>
</article>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-admin-add").validate({
		rules:{
			userName:{
				required:true,
				minlength:2,
				maxlength:10
			},
			loginName:{
				required:true,
				minlength:4,
				maxlength:20
			},
			password:{
				required:true,
			},
			password2:{
				required:true,
				equalTo: "#password"
			},
			gender:{
				required:true,
			},
			age:{
				required:true,
				number:true,
			},
			status:{
				required:true,
			},
			comments:{
				maxlength:100
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "user/saveUser.do",
			   data: $(form).serialize() ,
			   success: function(data){
				   //data =  $.parseJSON( data )
				   if( data.result == "success"){
					    var index = parent.layer.getFrameIndex(window.name);
						parent.$('#btn-refresh').click();
						parent.layer.close(index);
				   }else{
					   layer.msg(data.message,{icon:2,time:1000});
				   }
			   }
			});
		}
	});
});
</script>