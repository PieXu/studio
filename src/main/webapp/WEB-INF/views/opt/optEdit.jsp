<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
<article class="cl pd-20">
	<form action="opt/saveOpt.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${opt.id }"/>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>名称（用于显示）：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${opt.name }" autocomplete="off" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>编码（唯一标识）：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${opt.code }"  autocomplete="off"  id="code" name="code">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>类型（输出位置）：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<span class="select-box">
					<select size="1" name="type" class="select">
						<option value="">请选择...</option>
						<c:if test="${ !empty optTypeList}">
							<c:forEach items="${optTypeList }" varStatus="status" var="dic">
								<option value="${dic.id }" <c:if test="${opt.type eq dic.id }">selected</c:if> >${dic.name } </option>
							</c:forEach>
						</c:if>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>是否窗口：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<page:radio name="isWindow" dicList="${isWindowList }" selectedVal="${opt.isWindow }"/>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">操作URL：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${opt.url }" id="url" name="url">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">图标样式：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${opt.iconFont}" id="iconFont" name="iconFont" style="width: 35%;margin-right: 20px" onchange="changeIcon();">
				图标预览： 
				<i class="Hui-iconfont" id="preview_i" style="font-size: 24px"></i>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">备注：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<textarea name="comments" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" >${role.comments}</textarea>
			</div>
		</div>
		<input type="submit" id="sub_btn" style="display:none">
	</form>
</article>
<script type="text/javascript">
function changeIcon()
{
	$("#preview_i").addClass($("#iconFont").val());
}
$(function(){
	$("#form-admin-add").validate({
		rules:{
			name:{
				required:true,
				minlength:2,
				maxlength:50
			},
			code:{
				required:true,
				minlength:1,
				maxlength:16
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
			   url: "opt/saveOpt.do",
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
