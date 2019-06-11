<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
	<!-- 查询条件部分 -->	
	<div class="docs-queryfiled">
		用户组名称：<input type="text" id="name" name="name" value="${group.name }" class="input-text radius" placeholder="用户组名称" style="width:120px;">
		&nbsp;&nbsp;
		用户组编码：<input type="text" id="code" name="code" value="${group.name}" class="input-text radius" placeholder="用户组编码" style="width:120px;">
		&nbsp;&nbsp;
		&nbsp;&nbsp;
		<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
		<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
	</div>
	<!-- 操作按钮部分 -->
	<div class="mt-30">
		<page:showOpt code="add" title="新增" type="button" method="editUserGroup('');" />
	</div>
	<!-- 数据列表分页部分 -->
	<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
		<thead>
			<tr class="text-l">
				<th width="50">序号</th>
				<th>用户组名称</th>
				<th width="200">用户组编码</th>
				<th width="120">创建时间</th>
				<th width="120">修改时间</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="group" items="${page}" varStatus="status">  
			<tr class="text-l">
				<td>${status.index+1 }</td>
				<td class="text-l">${group.name }</td>
				<td>${group.code }</td>
				<td><fmt:formatDate value="${group.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td><fmt:formatDate value="${group.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="f-14 td-manage">
					<page:showOpt type="link" code="update" title="编辑" method="editUserGroup('${group.id}')" iconfont="&#xe60c;"/>
					<page:showOpt type="link" code="delete" title="删除" method="delUserGroup('${group.id}')" iconfont="&#xe6e2;"/>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<page:page formId="list_query_form" page="${page}" ajaxType="true" /> 
<script type="text/javascript">
/*添加*/
function editUserGroup(id){
	var index = layer.open({
		type: 2,
		title: "用户组维护",
		area: ['560px', '420px'],
		content: "group/groupEdit?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}
/*删除*/
function delUserGroup(id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'group/deleteUserGroup',
			data:{'id':id},
			success: function(data){
				if( data.result == "success"){
					commonQuery();
				}else{
					layer.msg(data.message,{icon:1,time:1500});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
</script>