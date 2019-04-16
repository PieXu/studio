<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
		<article class="cl pd-20">
			<div class="docs-queryfiled">
				名称：<input type="text" id="name" name="name" value="${dic.name }" class="input-text radius" placeholder="字典名称" style="width:180px;">
				&nbsp;&nbsp;
				编码：<input type="text" id="code" name="code" value="${dic.code }" class="input-text radius" placeholder="字典编码" style="width:120px;">
				&nbsp;&nbsp;
				分类：
				<span class="select-box radius" style="width:150px">
					<select size="1" name="category.id" class="select">
						<option value="">请选择...</option>
						<c:if test="${ !empty categoryList}">
							<c:forEach items="${categoryList }" varStatus="status" var="category">
								<option value="${category.id }" <c:if test="${category.id eq dic.category.id }">selected</c:if> >${category.categoryName } </option>
							</c:forEach>
						</c:if>
					</select>
				</span> 
				&nbsp;&nbsp;
				<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
				<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
			</div>
			<div class="mt-30">
				<a class="btn btn-primary radius" data-title="新增" _href="article-add.html" onclick="edit('');" href="javascript:;">新增</a>
				<a href="javascript:void(0);" onclick="datadel()" class="btn btn-danger radius" data-title="删除">删除</a>
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="30">序号</th>
							<th>名称</th>
							<th width="150">编码</th>
							<th width="120">分类编码</th>
							<th width="150">创建时间</th>
							<th width="150">更新时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dic" items="${ dicList}" varStatus="status">  
							<tr class="text-c">
								<td><input type="checkbox" value="" name=""></td>
								<td>${status.index+1 }</td>
								<td class="text-l">${dic.name }</td>
								<td>${dic.code }</td>
								<td>${dic.category.categoryName }</td>
								<td><fmt:formatDate value="${dic.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td><fmt:formatDate value="${dic.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								<td class="f-14 td-manage">
									<a style="text-decoration:none" class="ml-5" onClick="edit('${dic.id}')" href="javascript:;" title="编辑">[编辑]</a>
									<a style="text-decoration:none" class="ml-5" onClick="del('${dic.id}',this)" href="javascript:;" title="删除">[删除]</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
         		<page:page formId="list_query_form" page="${dicList}" ajaxType="true" /> 
		</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*添加*/
function edit(id){
	var index = layer.open({
		type: 2,
		title: "数据字典编辑",
		area: ['560px', '420px'],
		content: "dic/dicEdit.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}

/*删除 逻辑删除*/
function del(id,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{id:id},
			url: 'dic/dicDelete.do',
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

</script>
