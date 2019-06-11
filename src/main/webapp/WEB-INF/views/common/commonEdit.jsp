<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld"%>
<article class="cl pd-20">
	<form action="process/saveOrUpdate" method="post" class="form form-horizontal" id="form-common-edit">
		<input type="submit" id="sub_btn" style="display:none">
		<input type="hidden" name="_pageId" value="${def.id }"/>
		<input type="hidden" name="_tableName" value="${def.tableName }"/>
		<page:showEditFiled list="${filedList}" bizObject="${bizObject }"/>
	</form>
</article>
<script type="text/javascript">
$(function(){
	$("#form-common-edit").validate({
		rules:{
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "process/saveOrUpdate.do",
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
