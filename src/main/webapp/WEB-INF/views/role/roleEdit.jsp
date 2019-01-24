<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<article class="cl pd-20">
	<form action="role/saveRole.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" value="${role.id }"/>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>角色名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${role.name }" placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>角色编码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${role.code }" placeholder="" id="code" name="code">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>角色类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${role.code }" placeholder="" id="type" name="type">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="comments" cols="" rows="" class="textarea"  placeholder="说点什么...100个字符以内" dragonfly="true" >${role.comments}</textarea>
			</div>
		</div>
		<input type="submit" id="sub_btn" style="display:none">
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
				maxlength:50
			},
			code:{
				required:true,
				minlength:1,
				maxlength:16
			},
			type:{
				required:true,
				minlength:1,
				maxlength:50
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
			   url: "role/saveRole.do",
			   data: $(form).serialize() ,
			   success: function(data){
				   if( $.parseJSON( data ).result == "success"){
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
<!--/请在上方写此页面业务相关的脚本-->