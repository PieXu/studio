<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
			<div class="docs-queryfiled">
				分类名称：<input type="text" id="categoryName" name="categoryName" value="${dicCateogry.categoryName }" class="input-text radius" style="width:180px;">
				&nbsp;&nbsp;
				所属分类：<input type="text" id="categoryType" name="categoryType" value="${dicCateogry.categoryType }" class="input-text radius"   style="width:120px;">
				&nbsp;&nbsp;
				字典编码：<input type="text" id="categoryCode" value="${dicCateogry.categoryCode }" name="categoryCode" class="input-text radius" style="width:120px;">
				&nbsp;&nbsp;
				<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
				<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
			</div>
			<div class="mt-30">
				<a class="btn btn-primary radius" data-title="新增"  onclick="edit('');" href="javascript:;">新增</a>
			</div>
				<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
					<thead>
						<tr class="text-c">
							<th width="30">序号</th>
							<th class="text-l">分类名称</th>
							<th class="text-l" width="150">编码</th>
							<th width="100">所属分类</th>
							<th width="150">创建时间</th>
							<th width="150">更新时间</th>
							<th width="120">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="dic" items="${ page}" varStatus="status">  
							<tr class="text-c">
								<td>${status.index+1 }</td>
								<td class="text-l">${dic.categoryName }</td>
								<td class="text-l">${dic.categoryCode }</td>
								<td>${dic.categoryType }</td>
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
                <page:page formId="list_query_form" page="${page}" ajaxType="true" /> 
<script type="text/javascript">
/*添加*/
function edit(id){
	var index = layer.open({
		type: 2,
		title: "字典分类",
		area: ['420px', '300px'],
		content: "dic/editDicCategory.do?id="+id,
		btn: ['保 存', '关 闭']
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
			url: 'dic/deleteCategory.do',
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
