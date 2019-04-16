<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
		<article class="cl pd-20">
				<div class="docs-queryfiled">
					类型：
					<span class="select-box inline radius">
						<c:if test="${!empty messageType }">
							<select name="noticeType" id="noticeType" class="select radius">
								<option value="">全部类型</option>
								<c:forEach items="${messageType }" var="type" varStatus="status">
								<option value="${type.key }" <c:if test="${type.key eq message.noticeType }">selected</c:if> >${type.value }</option>
								</c:forEach>
							</select>
						</c:if>
					</span> 
					<span style="padding-left:15px">
						标题：<input type="text" name="title" id="title" value="${message.title }" placeholder=" 标题" style="width:120px" class="input-text radius">
					</span>
					<span style="padding-left:15px">
						标签：<input type="text" name="flag" id="flag"  value="${message.flag }"  placeholder=" 消息标签" style="width:150px" class="input-text radius">
					</span>
					<span style="padding-left:10px">
						<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
						<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
					</span>
				</div>
			<div class="mt-30">
				<page:showOpt code="add" title="新增" type="button" method="message_edit('');" />
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="50">序号</th>
							<th>标题</th>
							<th width="120">分类</th>
							<th width="150">标签</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ page}"  var="message"  varStatus="status">  
							<tr class="text-c">
								<td><input type="checkbox" value="" name=""></td>
								<td>${status.index+1 }</td>
								<td class="text-l">${message.title }</td>
								<td>${message.noticeType }</td>
								<td>${message.flag }</td>
								<td class="f-14 td-manage">
									<page:showOpt type="link" code="update" title="编辑" method="message_edit('${message.id}')" iconfont="&#xe60c;"/>
									<page:showOpt type="link" code="delete" title="删除" method="message_del('${message.id}',this)" iconfont="&#xe6e2;"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
                <page:page formId="list_query_form" page="${page}" ajaxType="true" />  
			</div>
		</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*新增 修改*/
function message_edit(id){
	var index = layer.open({
		type: 2,
		title: "编辑",
		area: ['893px', '600px'],
		content: "message/editMessage.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}
function message_del(id,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'message/deleteMessage.do',
			dataType: 'json',
			success: function(data){
				if(data.result == "success"){
					$(obj).parents("tr").remove();
					layer.msg(data.message,{icon:1,time:1000});
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
</script>
