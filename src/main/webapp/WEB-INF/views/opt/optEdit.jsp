<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<article class="cl pd-20">
	<form action="opt/saveOpt.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${opt.id }"/>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>操作名称：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${opt.name }" placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>操作编码：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" value="${opt.code }" placeholder="" id="code" name="code">
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
