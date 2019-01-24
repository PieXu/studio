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
	<form action="product/saveBreaing.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="id" value="${breaing.id }" /> 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${breaing.productName }" placeholder="输入型号" id="productName" name="productName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>型号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${breaing.type }" placeholder="类型" id="type" name="type">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>品牌：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ breaing.brand}" placeholder="品牌" id="brand" name="brand">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>厚度：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ breaing.thickness }"  placeholder="厚度" id="thickness" name="thickness">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>内径：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${ breaing.inDiameter }" placeholder="内径" id="inDiameter" name="inDiameter">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>外径：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${ breaing.outDiameter }" placeholder="外径" name="outDiameter" id="outDiameter">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">分类：</label>
			<div class="formControls col-xs-8 col-sm-9"> 
				<span class="select-box" style="width:150px;">
					<page:select name="category" dicList="${dicList}" styleClass="select" selectedVal="${breaing.category }" />
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">推荐首页：</label>
			<div class="formControls col-xs-8 col-sm-9" style="display: inline-block;"> 
				  <div class="radio-box" style="padding-left:0">
				    <input type="radio" id="radio-2" <c:if test="${breaing.isHot ne 1 }">checked</c:if> name="isHot" value="0"><label for="radio-2">否</label>
				  </div>
				  <div class="radio-box" style="padding-left:0">
				    <input type="radio" <c:if test="${breaing.isHot eq 1}">checked</c:if> id="radio-1" name="isHot" value="1"><label for="radio-1">是</label>
				  </div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">产品图集：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<div class="btns">
					<div id="filePicker-2"></div>
				</div>
				<!-- 已经存在的附件图片列表 -->
				<c:if test="${!empty fileList }">
					<div class="uploader-list-container">
						<ul id="filelist" class="filelist">
							<c:forEach items="${ fileList}" var="file">
								<li class="img-div fl" id="imgli_${file.id }" onmouseover="showDel(this,'${file.id }')" onmouseleave="hideDel(this,'${file.id }')" >
									<img src="file/openFile.do?id=${file.id }&type=image"  style="width: 100%;height: 100%" />
									<input type="hidden" name="fileId" value="${file.id}"/>
									<div class="file-panel" id="del_${file.id }">
								        <span class="cancel" onclick="del_img('${file.id }');">删除</span>
							        </div>
								</li>
								<%-- <li class="img-div fl">
									<img src="http://www.bjfaj.com/images/${file.id }.${file.ext}"  style="width: 100%;height: 100%" onclick="del_img(this)" />
									<input type="hidden" name="fileId" value="${file.id}"/>
								</li> --%>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<div class="uploader-list-container">
					<div class="queueList">
						<div id="dndArea" class="placeholder">
							<p>或将照片拖到这里，单次最多可选300张</p>
						</div>
					</div>
					<div class="statusBar">
						<div class="progress"> <span class="text">0%</span> <span class="percentage"></span> </div>
						<div class="info"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>产品介绍：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="description" id="description" style="width:100%;height:200px;">${breaing.description}</textarea>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>规格描述：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<%-- <script id="editor" name="standard" type="text/plain" style="width:100%;height:200px;">${breaing.standard}</script> --%>
				<textarea name="standard" id="standard" style="width:100%;height:300px;">${breaing.standard}</textarea>
			</div>
		</div>
			<input type="submit" id="sub_btn" value="提 交" style="display:none">
	</form>
</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script>
<script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.min.js"> </script>
<script type="text/javascript" src="lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="js/common/upload.js"></script> 
<script type="text/javascript">

	function showDel(obj, id) {
		var $btns = $("#del_" + id);
		$btns.appendTo($(obj));
		$btns.stop().animate({
			height : 30
		});
	}
	
	function hideDel(obj, id) {
		var $btns = $("#del_" + id);
		$btns.appendTo($(obj));
		$btns.stop().animate({
			height : 0
		});
	}
	
	//删除图片
	function del_img(id) {
		layer.confirm('确定要删除吗？', {
			btn : [ '确定', '取消' ]
		//按钮
		}, function() {
			$("#imgli_" + id).remove();
			layer.msg('删除成功', {
				icon : 1
			});
		}, function() {
		});
	}


$(function(){
	$("#form-admin-add").validate({
		rules:{
			productName:{
				required:true,
				minlength:4,
				maxlength:20
			},
			type:{
				required:true,
				minlength:4,
				maxlength:50
			},
			brand:{
				required:true,
			},
			thickness:{
				required:true,
			},
			inDiameter:{
				required:true,
			},
			outDiameter:{
				required:true,
			},
			standard:{
				required:true,
			},
			description:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			uploader.upload();
			uploader.on( 'uploadFinished', function() {
				$.ajax({
					   type: "POST",
					   url: "product/saveBreaing.do",
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
			});
		}
	});
});

$(function(){
	var standardUe = UE.getEditor('standard');
	var descriptionUe = UE.getEditor('description');
});
</script>
<!--/请在上方写此页面业务相关的脚本-->