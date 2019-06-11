<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />

<article class="cl pd-20">
	<form action="plat/savePageDefine.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="id" value="${pageDefine.id }" /> 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-9 col-sm-10">
				<input type="text" class="input-text" autocomplete="off" value="${pageDefine.pageName }" placeholder="输入页面定义名称" id="pageName" name="pageName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2" ><span class="c-red">*</span>编码：</label>
			<div class="formControls col-xs-9 col-sm-10" >
				<input type="text" class="input-text" autocomplete="off" value="${pageDefine.pageCode }" placeholder="输入页面定义编码，用于连接访问查询，唯一标识" id="pageCode" name="pageCode">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2" >说明：</label>
			<div class="formControls col-xs-9 col-sm-10" >
				<input type="text" class="input-text" autocomplete="off" value="${pageDefine.comments}" placeholder="页面定义说明" id="comments" name="comments">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2" ><span class="c-red">*</span>关联表：</label>
			<div class="formControls col-xs-9 col-sm-10" >
				<span class="select-box">
					<select name="tableName" id="tableName" class="select radius" onchange="changeTable();">
						<option value="">---请选择---</option>
						<c:if test="${!empty tableList }">
							<c:forEach items="${tableList }" var="table">
								<option value="${table.tableName}" <c:if test="${pageDefine.tableName eq table.tableName}">selected</c:if> >${table.tableName} <c:if test="${!empty table.remarks}">[${table.remarks }]</c:if> </option>
							</c:forEach>
						</c:if>
					</select>
				</span>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2" >过滤条件：</label>
			<div class="formControls col-xs-9 col-sm-10" >
				<textarea name="sqlWhere" id="sqlWhere" class="textarea valid" style="width:100%;height:180px;">${pageDefine.sqlWhere}</textarea>
			</div>
		</div>
		<input type="submit" id="sub_btn" value="提 交" style="display:none">
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
$(function(){
	$("#form-admin-add").validate({
		rules:{
			pageName:{
				required:true,
				minlength:2,
				maxlength:20
			},
			pageCode:{
				required:true,
				maxlength:20
			},
			tableName:{
				required:true
			},
			comments:{
				minlength:0,
				maxlength:100
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "plat/savePageDefine",
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
