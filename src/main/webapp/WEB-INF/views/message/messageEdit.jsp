<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.icon-del{
    display: inline-block;
    width: 20px;
    height: 20px;
    opacity: 0; 
    position: absolute;
    top: 479px;
    left: 354px;
    font-size: 21px;
    color: red;
    border-radius: 50%;  
}


img-div{

display:none;

}
.img-div:hover .icon-del{
    opacity: 1;
}
</style>
<article class="cl pd-20">
	<form action="message/saveMessage.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="id" value="${message.id }" /> 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${message.title }" placeholder="输入新闻标题" id="title" name="title">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">标签：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${message.flag }" placeholder="输入新闻标签" id="flag" name="flag">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>类型：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<span class="select-box" style="width:150px;">
					<c:if test="${!empty messageType }">
						<select name="noticeType" id="noticeType" class="select radius">
							<option value="">全部类型</option>
							<c:forEach items="${messageType }" var="type" varStatus="status">
							<option value="${type.key }" <c:if test="${type.key eq message.noticeType }">selected</c:if> >${type.value }</option>
							</c:forEach>
						</select>
					</c:if>
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>新闻内容：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="content" id="content" style="width:100%;height:300px;">${message.content}</textarea>
			</div>
		</div>
			<input type="submit" id="sub_btn" value="提 交" style="display:none">
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">

$(function(){
	$("#form-admin-add").validate({
		rules:{
			title:{
				required:true,
				minlength:4,
				maxlength:50
			},
			noticeType:{
				required:true,
			},
			flag:{
				minlength:0,
				maxlength:50
			},
			content:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "message/saveMessage.do",
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

$(function(){
	var standardUe = UE.getEditor('content');
});
</script>
