<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="oauth2/regList.do" method="post" id="client_list_form" >
				<div class="docs-queryfiled">
					<span style="padding-left:15px">
						应用ID：<input type="text" name="clientId" id="clientId" value="${client.clientId }" placeholder="应用客户端ID" style="width:240px" class="input-text radius">
					</span>
					<span style="padding-left:15px">
						授权类型：
						<span class="select-box inline radius">
							<c:if test="${!empty dicList }">
								<select name="authorizedGrantTypes" id="authorizedGrantTypes" class="select radius" style="min-width: 100px">
									<option value="">全部...</option>
									<c:forEach items="${dicList }" var="authorizedGrantTypes" varStatus="status">
										<option value="${authorizedGrantTypes.id }" <c:if test="${authorizedGrantTypes.id eq client.authorizedGrantTypes}">selected</c:if> >${authorizedGrantTypes.name }</option>
									</c:forEach>
								</select>
							</c:if>
						</span> 
					</span>
					<span style="padding-left:10px">
						<button class="btn btn-primary radius" type="submit">查 询</button>
						<button class="btn btn-success radius" type="button" onclick="clearSerachForm('client_list_form')">清 空</button>
					</span>
				</div>
			</form>
			<div class="mt-30">
				<page:showOpt code="add" title="新增" type="button" method="client_edit('');" />
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<!-- <th width="25"><input type="checkbox" name="" value=""></th> -->
							<th width="50">序号</th>
							<th>应用ID</th>
							<th width="180">应用秘钥</th>
							<th width="80">授权类型</th>
							<th width="180">跳转URL</th>
							<th width="120">TOKEN时长（秒）</th>
							<th width="150">TOKEN刷新时长（秒）</th>
							<th width="60">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ page}"  var="client"  varStatus="status">  
							<tr class="text-c">
								<!-- <td><input type="checkbox" value="" name=""></td> -->
								<td>${status.index+1 }</td>
								<td>${client.clientId }</td>
								<td>${client.clientSecret }</td>
								<td><page:idToName dicId="${client.authorizedGrantTypes }"/></td>
								<td>${client.redirectUri }</td>
								<td>${client.accessTokenValidity}</td>
								<td>${client.refreshTokenValidity}</td>
								<td class="f-14 td-manage">
									<page:showOpt type="link" code="update" title="编 辑" method="client_edit('${client.id}')" iconfont="&#xe60c;"/>
									<page:showOpt type="link" code="delete" title="删 除" method="credits_del('${client.id}',this)" iconfont="&#xe6e2;"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
              	<page:page formId="client_list_form" page="${page}" />  
			</div>
		</article>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*新增 修改*/
function client_edit(id){
	var index = layer.open({
		type: 2,
		title: "编 辑",
		area: ['600px', '460px'],
		content: "oauth2/editOAuthReg.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}

function credits_del(id,obj){
	layer.confirm('删除该应用ID可能造成系统不稳定运行，确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: '"oauth2/deleteOAuth.do',
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
