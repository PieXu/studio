<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="page" uri="http://com.innovate.page.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<div class="docs-queryfiled">
		名称：<input type="text" id="name" name="name" value="${opt.name }" class="input-text radius" placeholder="操作名称..." style="width:120px;">
		&nbsp;&nbsp;
		编码：<input type="text" id="code" name="code" value="${opt.code}" class="input-text radius" placeholder="操作编码..." style="width:120px;">
		&nbsp;&nbsp;
		类型：
		<span class="select-box radius" style="width:150px">
			<select size="1" name="type" class="select">
				<option value="">请选择...</option>
				<c:if test="${ !empty optTypeList}">
					<c:forEach items="${optTypeList }" varStatus="status" var="dic">
						<option value="${dic.id }" <c:if test="${opt.type eq dic.id }">selected</c:if> >${dic.name } </option>
					</c:forEach>
				</c:if>
			</select>
		</span> 
		&nbsp;&nbsp;
		<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
		<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
	</div>
	<div class="mt-10">
		<a class="btn btn-primary radius" data-title="新增" onclick="editOpt('');" href="javascript:;">新增</a>
		<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius" data-title="批量删除">批量删除</a>
	</div>
	<!-- 数据列表分页部分 -->
	<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
		<thead>
			<tr class="text-c">
				<th width="35"><input type="checkbox" value="" name="user-Character-0" onclick="_checkAll(this,'optId')"></th>
				<th class="text-l" width="60">名称</th>
				<th width="50">编码</th>
				<th width="50">类型</th>
				<th width="35">图标</th>
				<th width="50">窗口</th>
				<th class="text-l" width="150">URL</th>
				<th class="text-l">描述</th>
				<th width="120">创建时间</th>
				<th width="120">更新时间</th>
				<th width="40">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="opt" items="${ page}" varStatus="status">  
			<tr class="text-c">
				<td><input type="checkbox" value="${opt.id }" name="optId"/></td>
				<td class="text-l">${opt.name }</td>
				<td>${opt.code }</td>
				<td><page:idToName dicId="${opt.type }"/></td>
				<td><i class="Hui-iconfont ${opt.iconFont}" id="preview_i" style="font-size: 22px"></i></td>
				<td><page:idToName dicId="${opt.isWindow }"/></td>
				<td class="text-l">${opt.url }</td>
				<td class="text-l">${opt.comments }</td>
				<td><fmt:formatDate value="${opt.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${opt.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="f-14 td-manage">
					<page:showOpt type="link" code="update" title="编辑" method="editOpt('${opt.id}')" iconfont="&#xe60c;"/>
					<%-- <page:showOpt type="link" code="delete" title="删除" method="delOpt('${opt.id}')" iconfont="&#xe6e2;"/> --%>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<page:page formId="list_query_form" page="${page}" ajaxType="true" /> 
<script type="text/javascript">

/*编辑 新增*/
function editOpt(id)
{
	var index = layer.open({
		type: 2,
		title: "操作编辑",
		area: ['680px', '530px'],
		content: "opt/optEdit.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}

/*删除*/
function datadel()
{
	var len = $("input[name='optId']:checked").length;
	if(len > 0 ){
		$.ajax({
		   type: "POST",
		   url: "opt/checkDelete.do",
		   data: $("#list_query_form").serialize() ,
		   success: function(data){
			   //data = $.parseJSON( data );
			   if( data.result == "success"){
				   layer.confirm(data.message, {
					   btn: ['确定','取消'] //按钮
					 }, function(){
						 doDelete();
					 }, function(){
					 });
			   }else{
				   layer.msg(data.message,{icon:2,time:1000});
			   }
		   }
		});
	}else{
		  layer.msg("请选择要删除的操作",{icon:3,time:2000});
	}
}

function doDelete()
{
	$.ajax({
	   type: "POST",
	   url: "opt/deleteOpts.do",
	   data: $("#list_query_form").serialize() ,
	   success: function(data){
		   //data = $.parseJSON( data );
		   if( data.result == "success"){
			   layer.msg(data.message,{icon:1,time:1000});
			   $("#btn-refresh").click();
		   }else{
			   layer.msg(data.message,{icon:2,time:1000});
		   }
	   }
	});
}
</script>
