<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
		<div class="docs-queryfiled">
				角色名称：<input type="text" id="name" name="name" value="${role.name }" class="input-text radius" placeholder="角色名称" style="width:120px;">
				&nbsp;&nbsp;
				类型：<input type="text" id="type" name="type" value="${role.type}" class="input-text radius" placeholder="角色类型" style="width:120px;">
				&nbsp;&nbsp;
				角色编码：<input type="text" id="code" name="code" value="${role.code}" class="input-text radius" placeholder="角色编码" style="width:120px;">
				&nbsp;&nbsp;
				<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
				<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
			</div>
			<div class="mt-30">
				<a class="btn btn-primary radius" data-title="新增" onclick="editRole('');" href="javascript:;">新增</a>
			</div>
				<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
					<thead>
						<tr class="text-c">
							<th width="50">序号</th>
							<th class="text-l">角色名称</th>
							<th width="120">角色编码</th>
							<th width="100">角色类型</th>
							<th width="130">创建时间</th>
							<th width="130">更新时间</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="role" items="${ page}" varStatus="status">  
							<tr class="text-c">
								<td>${status.index+1 }</td>
								<td class="text-l">${role.name }</td>
								<td>${role.code }</td>
								<td>${role.type }</td>
								<td><fmt:formatDate value="${role.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${role.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="f-14 td-manage">
									<a style="text-decoration:none" class="ml-5" onClick="editRole('${role.id}')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe60c;</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="grantRes('${role.id}')" href="javascript:;" title="菜单授权"><i class="Hui-iconfont">&#xe616;</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="delRole('${role.id}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
            	<page:page formId="list_query_form" page="${page}" ajaxType="true" />  
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*添加*/
function editRole(id){
	var index = layer.open({
		type: 2,
		title: "数据字典编辑",
		area: ['560px', '420px'],
		content: "role/roleEdit.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}

/*菜单授权*/
function grantRes(id){
	var index = layer.open({
		type: 2,
		title: "角色菜单授权",
		area: ['320px', '520px'],
		content: "role/grantRes.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}

/*删除*/
function delRole(id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'role/deleteRole.do',
			data:{'id':id},
			success: function(data){
				// var  data = $.parseJSON( data );
				if( data.result == "success"){
					layer.msg(data.message,{icon:1,time:1000});
					$('#role_list_form').submit();
				}else{
					layer.msg(data.message,{icon:1,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
 

</script>
