<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<article class="cl pd-20">
	<form action="group/saveUserGroup" method="post" class="form form-horizontal" id="form-admin-add">
	<input type="hidden" name="id" value="${userGroup.id }"/>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${userGroup.name }" placeholder="请填写用户组名称" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>编码：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${userGroup.code }" placeholder="请填写用户组编码" id="code" name="code">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">备注：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<textarea name="comments" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true">${userGroup.comments }</textarea>
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
			name:{
				required:true,
				minlength:2,
				maxlength:30
			},
			code:{
				required:true,
				minlength:4,
				maxlength:20
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
			   url: "group/saveUserGroup.do",
			   data: $(form).serialize() ,
			   success: function(data){
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