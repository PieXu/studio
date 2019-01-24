<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<article class="cl pd-20">
	<form action="res/saveResource.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="id" value="${res.id }" /> 
		<input type="hidden" name="parent" id="parent" value="${res.parent }" /> 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>菜单名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${res.resName }" placeholder="菜单名称" id="resName" name="resName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">菜单类型：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<span class="select-box" style="width:150px;">
					<page:select name="menuType" dicList="${typeList}" styleClass="select" selectedVal="${res.menuType }" />
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>菜单编码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${res.code }" placeholder="菜单编码" id="code" name="code">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">菜单图标：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ res.classStyle}" placeholder="菜单样式" id="classStyle" name="classStyle">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">链接URL：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ res.link }"  placeholder="链接URL" id="link" name="link">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">备注说明：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="comments" id="comments"  cols="" rows="" class="textarea" style="width:100%;height:120px;">${res.comments}</textarea>
			</div>
		</div>
		<input type="submit" id="sub_btn" value="提 交" style="display:none">
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript">
$(function(){
	$("#form-admin-add").validate({
		rules:{
			resName:{
				required:true,
				minlength:4,
				maxlength:20
			},
			code:{
				required:true,
				minlength:4,
				maxlength:50
			},
			classStyle:{
				minlength:4,
				maxlength:50
			},
			link:{
				maxlength:100
			},
			comments:{
				maxlength:500
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "res/saveResource.do",
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