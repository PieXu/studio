<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<article class="cl pd-20">
	<form method="post" class="form form-horizontal" id="form-admin-add">
	<input type="hidden" name="id" id="id" value="${dic.id }"/>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>字典名称：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${dic.name }" placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>字典编码：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${dic.code }" placeholder="" id="code" name="code">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>字典分类：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<span class="select-box" >
				<select size="1" name="category.id" class="select">
					<c:if test="${ !empty categoryList}">
						<c:forEach items="${categoryList }" varStatus="status" var="category">
							<option value="${category.id }" <c:if test="${category.id eq dic.category.id }">selected</c:if> >${category.categoryName } </option>
						</c:forEach>
					</c:if>
				</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"> 描述备注：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<textarea name="comments" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true">${dic.comments }</textarea>
			</div>
		</div>
		<input type="submit" id="sub_btn" style="display: none">
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
				minlength:1,
				maxlength:50
			},
			code:{
				required:true,
				minlength:1,
				maxlength:59
			},
			categotyCode:{
				required:true,
				minlength:1,
				maxlength:50
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "dic/saveDic.do",
			   data: $(form).serialize() ,
			   success: function(msg){
			     	var index = parent.layer.getFrameIndex(window.name);
					parent.$('#btn-refresh').click();
					parent.layer.close(index);
			   }
			});
		}
	});
});
</script>
<!--/请在上方写此页面业务相关的脚本-->