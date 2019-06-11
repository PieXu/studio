<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="page" uri="http://com.innovate.page.tld" %>
<article class="cl pd-15">
	<form action="res/saveResOpt.do" method="post" class="form form-horizontal" id="form-res-opt">
		<input type="hidden" name="resId" value="${resId}"/>
		<input type="submit" id="sub_btn" style="display:none">
		<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
			<thead>
				<tr class="text-c">
					<th width="35">选择</th>
					<th class="text-l" width="80">名称</th>
					<th width="35">图标</th>
					<th width="50">类型</th>
					<th width="50">窗口</th>
					<th width="60">高度(px)</th>
					<th width="60">宽度(px)</th>
					<th>URL</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="opt" items="${ optList}" varStatus="status">  
				<tr class="text-c">
					<td><input type="checkbox" value="${opt.id }" name="optId"  <page:checkContains list='${resOptList }' value='${opt.id }'/></td>
					<td class="text-l">${opt.name }</td>
					<td><i class="Hui-iconfont ${opt.iconFont}" style="font-size:24px"></i></td>
					<td><page:idToName dicId="${opt.type }"/></td>
					<td><page:idToName dicId="${opt.isWindow }"/></td>
					<td><input type="text" class="input-text radius" autocomplete="off" value="${opt.height }" name="${opt.id}_height"></td>
					<td><input type="text" class="input-text radius" autocomplete="off" value="${opt.width }" name="${opt.id}_width"></td>
					<td>
						<c:choose>
							<c:when test="${!empty opt.afterUrl }">
								<input type="text" class="input-text radius" autocomplete="off" value="${opt.afterUrl }" name="${opt.id}_url">
							</c:when>
							<c:otherwise>
								<input type="text" class="input-text radius" autocomplete="off" value="${opt.orginUrl }" name="${opt.id}_url">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<%-- <div class="row cl">
			<div class="formControls col-xs-12 col-sm-11">
				<dl class="permission-list">
					<dt>
						<label>
							<input type="checkbox" value="" onclick="_checkAll(this,'optId')" id="user-Character-0">&nbsp;全选
						</label>
					</dt>
					<dd>
						<dl class="cl permission-list2" style="border:0">
							<c:if test="${!empty optList }">
								<c:forEach items="${optList }" var="opt" varStatus="status">
									<dt>
										<label class=""  title="编码：${opt.code }" data-toggle="tooltip" data-placement="bottom" >
											<input type="checkbox" <page:checkContains list='${resOptList }' value='${opt.id }'/>
											 	value="${opt.id }" name="optId" id="${opt.id }">${opt.name }
										</label>
									</dt>
								</c:forEach>
							</c:if>
						</dl>
					</dd>
				</dl>
			</div>
		</div> --%>
	</form>
</article>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$("#form-res-opt").validate({
		rules:{
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "res/saveResOpt.do",
			   data: $("#form-res-opt").serialize() ,
			   success: function(data){
				   //data = $.parseJSON( data );
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
