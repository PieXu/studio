<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="credits/listCredits.do" method="post" id="credits_list_form" >
				<div class="docs-queryfiled">
					<span style="padding-left:15px">
						名称：<input type="text" name="produceName" id="produceName" value="${credits.produceName }" placeholder=" 商品名称" style="width:240px" class="input-text radius">
					</span>
					<span style="padding-left:10px">
						<button class="btn btn-primary radius" type="submit">查 询</button>
						<button class="btn btn-success radius" type="button" onclick="clearSerachForm('credits_list_form')">清 空</button>
					</span>
				</div>
			</form>
			<div class="mt-30">
				<page:showOpt code="add" title="新增" type="button" method="credits_edit('');" />
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="50">序号</th>
							<th>名称</th>
							<th width="150">兑换分值</th>
							<th width="60">数量</th>
							<th width="100">商品图片</th>
							<th width="100">状态</th>
							<th width="120">创建时间</th>
							<th width="120">修改时间</th>
							<th width="80">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ page}"  var="credits"  varStatus="status">  
							<tr class="text-c">
								<td><input type="checkbox" value="" name=""></td>
								<td>${status.index+1 }</td>
								<td class="text-l">${credits.produceName }</td>
								<td class="text-l">${credits.creditsScore }</td>
								<td class="text-l">${credits.amount }</td>
								<td><img src="file/openFile.do?id=${credits.productImage}&type=image" alt="${credits.produceName }" width="80px" height="60px"</td>
								<td><page:valueToName value="${credits.status }" array="{'0':'下架','1':'上架'}"/></td>
								<td><fmt:formatDate value="${credits.createTime}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${credits.updateTime}" pattern="yyyy-MM-dd"/></td>
								<td class="f-14 td-manage">
									<c:choose>
										<c:when test="${credits.status eq 1 }">
											<page:showOpt type="link" code="setting" title="下架" method="cancleHot('${credits.id}')" iconfont="&#xe631;"/>
										</c:when>
										<c:otherwise>
											<page:showOpt type="link" code="setting" title="上架" method="setHot('${credits.id}')" iconfont="&#xe615;"/>
										</c:otherwise>
									</c:choose>
									<page:showOpt type="link" code="update" title="编辑" method="credits_edit('${credits.id}')" iconfont="&#xe60c;"/>
									<page:showOpt type="link" code="delete" title="删除" method="credits_del('${credits.id}',this)" iconfont="&#xe6e2;"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
              	<page:page formId="credits_list_form" page="${page}" />  
			</div>
		</article>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*新增 修改*/
function credits_edit(id){
	var index = layer.open({
		type: 2,
		title: "编辑",
		area: ['893px', '600px'],
		content: "credits/editCredits.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}

function credits_del(id,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'credits/deleteCredits.do',
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
/*资讯-下架*/
function cancleHot(id){
	layer.confirm('确认要下架改兑换商品吗？',function(index){
		changeIsHot(id,"0");
	});
}

/*资讯-发布*/
function setHot(id){
	layer.confirm('确认要上架改商品吗？',function(index){
		changeIsHot(id,"1");
	});
}

/*首页推荐发布设置*/
function changeIsHot(id,val){
	$.ajax({
		type: 'POST',
		data:{'id':id,'status':val},
		url: 'credits/changeIsHot.do',
		dataType: 'json',
		success: function(data){
			if( data.result == "success"){
				layer.msg(data.message,{icon:1,time:1000});
				$('#credits_list_form').submit();
			}else{
				layer.msg(data.message,{icon:1,time:1000});
			}
		},
		error:function(data) {
			console.log(data);
		},
	});		
}
</script>
