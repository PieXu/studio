<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
		<div class="docs-queryfiled">
			<span>
				名称：<input type="text" name="pageName" id="pageName" value="${pageDefine.pageName }" placeholder="页面名称" style="width:160px" class="input-text radius">
			</span>
			<span style="padding-left:15px">
				编码：<input type="text" name="pageCode" id="pageCode" value="${pageDefine.pageCode }" placeholder="页面编码" style="width:150px" class="input-text radius">
			</span>
			<span style="padding-left:15px">
				表名：<input type="text" name="tableName" id="tableName" value="${pageDefine.tableName }" placeholder="表名" style="width:180px" class="input-text radius">
			</span>
			<span style="padding-left:10px">
				<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
				<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
			</span>
		</div>
		<div class="mt-30">
			<page:showOpt code="add" title="新 增" type="button" method="page_edit('');" />
		</div>
		 <table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
			<thead>
				<tr class="text-c">
					<th width="30">序号</th>
					<th>名称</th>
					<th width="80">编码</th>
					<th width="150">表名</th>
					<th width="200">页面说明</th>
					<th width="120">创建时间</th>
					<th width="120">修改时间</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${page}" var="define"  varStatus="status">  
					<tr class="text-c">
						<td>${status.index+1 }</td>
						<td>${define.pageName }</td>
						<td>${define.pageCode }</td>
						<td>${define.tableName }</td>
						<td>${define.comments }</td>
						<td><fmt:formatDate value="${define.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${define.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="f-14 td-manage">
							<page:showOpt type="link" code="update" title="编辑" method="page_edit('${define.id}')" iconfont="&#xe60c;"/>
							<page:showOpt type="link" code="delete" title="删除" method="page_del('${define.id}',this)" iconfont="&#xe6e2;"/>
							<page:showOpt type="link" code="update" title="页面属性定义" method="page_set('${define.id}')" iconfont="&#xe626;"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
        <page:page formId="list_query_form" page="${page}" ajaxType="true" /> 

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*新增 修改*/
function page_edit(id){
	var index = layer.open({
		type: 2,
		title: "页面定义",
		area: ['800px', '560px'],
		content: "plat/editPageDefine.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}

function page_set(id){
	var index = layer.open({
		type: 2,
		title: "页面定义",
		area: ['1060px', '600px'],
		content: "plat/setPageDefine.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}

function page_del(id,obj){
	layer.confirm('删除可能会对业务运行造成影响，确认要删除吗？',{icon: 3, title:'删除确认'},function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'plat/deletePageDefine.do',
			dataType: 'json',
			success: function(data){
				if(data.result == "success"){
					layer.msg(data.message,{icon:1,time:1000});
					commonQuery();
				}else{
					layer.msg(data.message,{icon:1,time:1000});
				}
			},
			error:function(data) {
				console.log(data);
			},
		});		
	});
}
$(function () { $("[data-toggle='tooltip']").tooltip()}); 
</script>
