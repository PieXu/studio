<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="page" uri="http://com.innovate.page.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
<article class="cl pd-20">
	<form action="#" method="post" class="form form-horizontal" id="form-admin-add">
		<input type="hidden" name="pageId" id="pageId" value="${pageDefine.id }" /> 
		<div class="row cl">
			<label class="form-label col-xs-2 col-sm-1" style="padding-left: 0px;padding-right:0px;">名称：</label>
			<div class="formControls col-xs-10 col-sm-11" style="padding-right:10px;">
				<input type="text" class="input-text disabled"  readonly value="${pageDefine.pageName }<c:if test='${empty pageDefine.comments }'> [${!empty pageDefine.comments }]</c:if>">
			</div>
		</div>
		<div class="row cl" >
			<label class="form-label col-xs-2 col-sm-1" style="padding-left: 0px;padding-right:0px;">定义：</label>
			<div class="formControls col-xs-10 col-sm-11" style="padding-right:10px;">
				<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
					<thead>
						<tr class="text-c">
							<th width="30">序号</th>
							<th width="80">字段</th>
							<th width="100">显示名</th>
							<th width="70">类型</th>
							<th width="120">列表显示(px)</th>
							<th width="45">查询域</th>
							<th width="70">编辑域</th>
							<th width="60">数据类型</th>
							<th>转换标准</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${columns}" var="column"  varStatus="status">  
							<tr>
								<td>${status.index+1 }</td>
								<td>${column.columnName }
								<input type="hidden" name="id"  value="${column.id }"/>
								<input type="hidden" name="columnName"  value="${column.columnName}"/>
								<input type="hidden" name="attributeName"  value="${column.attributeName}"/>
								</td>
								<td><input type="text"  name="showName"  value="${column.showName}" class="input-text radius" placeholder="显示名称" style="width:100%"></td>
								<td>${column.filedType}<input type="hidden"  name="filedType"  value="${column.filedType}"/></td>
								<td>
									<select class="select-box radius" name="isList" style="width:60%">
										<option value="show" <c:if test="${column.isList eq 'show' }">selected</c:if> >显示</option>
										<option value="hidden" <c:if test="${column.isList eq 'hidden' }">selected</c:if>>隐藏</option>
										<option value="noshow" <c:if test="${column.isList eq 'noshow' }">selected</c:if>>不显示</option>
									</select>
									<input type="text" class="input-text radius" value="${column.width }"  name="width"  title="列表显示宽度，单位PX" data-toggle="tooltip" data-placement="right"  style="width:35%;">
								</td>
								<td>
									<select class="select-box radius" name="isQuery" style="width:100%">
										<option value="N" <c:if test="${column.isQuery eq 'N' }">selected</c:if> >否</option>
										<option value="Y"  <c:if test="${column.isQuery eq 'Y' }">selected</c:if> >是</option>
									</select>
								</td>
								<td>
									<select class="select-box radius" name="isEdit" style="width:100%">
										<option value="show" <c:if test="${column.isEdit eq 'show' }">selected</c:if> >显示</option>
										<option value="hidden" <c:if test="${column.isEdit eq 'hidden' }">selected</c:if>>隐藏</option>
										<option value="noshow" <c:if test="${column.isEdit eq 'noshow' }">selected</c:if>>不显示</option>
									</select>
								</td>
								<td>
									<select class="select-box radius" name="dataType" style="width:100%">
										<option value="text" <c:if test="${column.dataType eq 'text' }">selected</c:if>>文本</option>
										<option value="date" <c:if test="${column.dataType eq 'date' }">selected</c:if>>日期</option>
										<option value="json" <c:if test="${column.dataType eq 'json' }">selected</c:if>>JSON</option>
										<option value="dic" <c:if test="${column.dataType eq 'dic' }">selected</c:if>>字典</option>
										<option value="tag" <c:if test="${column.dataType eq 'tag' }">selected</c:if>>标签</option>
									</select>
								</td>
								<td><input type="text" class="input-text radius" value="${column.dataBody}" name="dataBody"  title="数据字典，数组或者标签等" data-toggle="tooltip" data-placement="left" style="width:100%;"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
			   url: "plat/savePageSet",
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
