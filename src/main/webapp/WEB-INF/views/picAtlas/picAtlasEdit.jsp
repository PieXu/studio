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
	<form action="pic/savePicAtlas.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="id" value="${picAtlas.id }" /> 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${picAtlas.atlasName }" placeholder="图集名称" id="atlasName" name="atlasName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>编码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${picAtlas.atlasCode }" placeholder="编码" id="atlasCode" name="atlasCode">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">图集：</label>
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
	$(function() {
		$("#form-admin-add").validate(
		{
			rules : {
				atlasName : {
					required : true,
					minlength : 4,
					maxlength : 20
				},
				atlasCode : {
					required : true,
					minlength : 4,
					maxlength : 50
				},
			},
			onkeyup : false,
			focusCleanup : true,
			success : "valid",
			submitHandler : function(form) {
				uploader.upload();
				uploader.on('uploadFinished', function() {
					$.ajax({
						type : "POST",
						url : "pic/savePicAtlas.do",
						data : $(form).serialize(),
						success : function(data) {
							if (data.result == "success") {
								var index = parent.layer
										.getFrameIndex(window.name);
								parent.$('#btn-refresh').click();
								parent.layer.close(index);
							} else {
								layer.msg(data.message, {
									icon : 2,
									time : 1000
								});
							}
						}
					});
				});
			}
		});
	});
</script>
<!--/请在上方写此页面业务相关的脚本-->