<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form action="oauth2/saveOAuthReg.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="id" value="${client.id }" /> 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>应用ID：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${client.clientId }" placeholder="应用ID" id="clientId" name="clientId">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>应用秘钥：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ client.clientSecret}" placeholder="应用秘钥" id="clientSecret" name="clientSecret">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">应用链接：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ client.redirectUri }"  placeholder="请以 http:// 开头" id="redirectUri" name="redirectUri">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">授权类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box inline radius">
					<c:if test="${!empty dicList }">
						<select name="authorizedGrantTypes" id="authorizedGrantTypes" class="select radius" style="min-width: 100px">
							<option value="">请选择...</option>
							<c:forEach items="${dicList }" var="authorizedGrantTypes" varStatus="status">
								<option value="${authorizedGrantTypes.id }" <c:if test="${authorizedGrantTypes.id eq client.authorizedGrantTypes}">selected</c:if> >${authorizedGrantTypes.name }</option>
							</c:forEach>
						</select>
					</c:if>
				</span>
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
			produceName:{
				required:true,
				minlength:2,
				maxlength:20
			},
			creditsScore:{
				required:true,
				number:true,
				min:0,
				max:1000000
			},
			amount:{
				required:true,
				number:true,
				min:0,
				max:999999
			},
			comments:{
				minlength:0,
				maxlength:500
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			/* uploader.upload();
			uploader.on( 'uploadFinished', function() { */
				$.ajax({
					   type: "POST",
					   url: "oauth2/saveOAuthReg.do",
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
			/* }); */
		}
	});
});
</script>
<!--/请在上方写此页面业务相关的脚本-->