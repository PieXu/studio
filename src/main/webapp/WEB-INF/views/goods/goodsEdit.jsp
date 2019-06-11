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
	<form action="goods/saveGoods.do" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="id" id="id" value="${goods.id }" /> 
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${goods.goodsName }" placeholder="输入商品名称" id="goodsName" name="goodsName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
					<c:if test="${!empty goodsType }">
						<select name="goodsType" id="goodsType" class="select radius">
							<option value="">全部类型</option>
							<c:forEach items="${goodsType }" var="type" varStatus="status">
							<option value="${type.key }" <c:if test="${type.key eq goods.goodsType }">selected</c:if> >${type.value }</option>
							</c:forEach>
						</select>
					</c:if>
				</span> 
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>单价：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ goods.price}" placeholder="单价" id="price" name="price">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2"><span class="c-red">*</span>总量：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" autocomplete="off" value="${ goods.amount }"  placeholder="厚度" id="总量" name="amount">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">购买限制：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${ goods.goodsLimits }" placeholder="购买限制" id="goodsLimits" name="goodsLimits">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">推荐首页：</label>
			<div class="formControls col-xs-8 col-sm-9" style="display: inline-block;"> 
				  <div class="radio-box" style="padding-left:0">
				    <input type="radio" id="radio-2" <c:if test="${goods.goodsTag ne 0 }">checked</c:if> name="goodsTag" value="0"><label for="radio-2">否</label>
				  </div>
				  <div class="radio-box" style="padding-left:0">
				    <input type="radio" <c:if test="${goods.goodsTag eq 1}">checked</c:if> id="radio-1" name="goodsTag" value="1"><label for="radio-1">是</label>
				  </div>
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">商品图集：</label>
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
									<img src="http://127.0.0.1:8088//${file.fullPath}"  style="width: 100%;height: 100%" />
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
		<div class="row cl">
			<label class="form-label col-xs-3 col-sm-2">商品介绍：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea name="goodsSummary" id="goodsSummary" style="width:100%;height:200px;">${goods.goodsSummary}</textarea>
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
		$("#imgli_" + id).remove();
		/* layer.confirm('确定要删除吗？', {
			btn : [ '确定', '取消' ]
		//按钮
		}, function() {
			$("#imgli_" + id).remove();
			layer.msg('删除成功', {
				icon : 1
			});
		}, function() {
		}); */
	}


$(function(){
	$("#form-admin-add").validate({
		rules:{
			goodsName:{
				required:true,
				minlength:2,
				maxlength:20
			},
			goodsType:{
				required:true
			},
			price:{
				required:true,
				number:true,
				min:0,
				max:9999
			},
			amount:{
				required:true,
				number:true,
				min:0,
				max:999999
			},
			goodsLimits:{
				number:true,
				min:0,
				max:999999
			},
			goodsSummary:{
				minlength:0,
				maxlength:500
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			 $.ajax({
				   type: "POST",
				   url: "goods/saveGoods.do",
				   data: $(form).serialize() ,
				   success: function(data){
					   if( data.result == "success"){
						    var objectId = data.data.objectId;
						  /*   uploader.on('uploadBeforeSend', function (obj, data) {
						        data = {
						            "objectId":objectId,
						            "operator": "管理员"}
						        }); */
						        
						    var fileIds =  $("input[name=fileId]").map(function(){return this.value;}).get().join(",");
					       // alert(fileIds);
					        uploader.onUpload(objectId,"管理员","bUYA+chR0FEiO8/X/yDZ3g==",fileIds);   
						   /*  var obj = new Object();
				            obj.objectId = objectId;
				            obj.operator = "管理员";
						    uploader.options.formData=obj;
						    uploader.upload();
						    uploader.on('uploadSuccess', function(file,response) {
					            alert(JSON.stringify(response));
					            console.log("file:",file);
					            console.log("response:",response);
					        }); */
					   }else{
						   layer.msg(data.message,{icon:2,time:1000});
					   }
				   }
				});  
			
			/* uploader.upload();
			uploader.on( 'uploadFinished', function() {
				$.ajax({
					   type: "POST",
					   url: "goods/saveGoods.do",
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
			});   */
		}
	});
});

$(function(){
	UE.getEditor('goodsSummary');
});
</script>
<!--/请在上方写此页面业务相关的脚本-->