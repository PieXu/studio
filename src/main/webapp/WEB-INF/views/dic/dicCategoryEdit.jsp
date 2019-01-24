<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<article class="cl pd-20">
	<form method="post" class="form form-horizontal" id="form-admin-add">
	<input type="hidden" name="id" id="id" value="${dic.id }"/>
	<input type="submit" id="sub_btn" style="display: none">
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${dic.categoryName }" placeholder="" id="categoryName" name="categoryName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>编码：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${dic.categoryCode }" placeholder="" id="categoryCode" name="categoryCode">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>类型：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${dic.categoryType }" placeholder="" id="categoryType" name="categoryType">
			</div>
		</div>
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
			categoryName:{
				required:true,
				minlength:1,
				maxlength:16
			},
			categoryCode:{
				required:true,
				minlength:1,
				maxlength:16
			},
			categotyType:{
				required:true,
				minlength:1,
				maxlength:16
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "dic/saveDicCategory.do",
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