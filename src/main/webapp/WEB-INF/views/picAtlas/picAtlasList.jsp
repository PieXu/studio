<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="pic/listPicAtlas.do" method="post" id="atlas_list_form" >
				<div class="docs-queryfiled">
					<span style="padding-left:15px">
					 图集名称：
						<input type="text" name="atlasName" id="atlasName" value="${picAtlas.atlasName }" placeholder=" 图集名称" style="width:160px" class="input-text radius">
					</span>
					<span style="padding-left:15px">
						图集编号：<input type="text" name="atlasCode" id="atlasCode" value="${picAtlas.atlasCode }" placeholder=" 图集编号" style="width:120px" class="input-text radius">
					</span>
					<span style="padding-left:10px">
						<button class="btn btn-primary radius" type="submit">查 询</button>
						<button class="btn btn-success radius" type="button" onclick="clearSerachForm('atlas_list_form')">清 空</button>
					</span>
				</div>
			</form>
			<div class="mt-30">
				<page:showOpt code="add" title="新增" type="button" method="atlasEdit('');" />
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<!-- <th width="25"><input type="checkbox" name="" value=""></th> -->
							<th width="50">序号</th>
							<th>名称</th>
							<th width="180">编码</th>
							<th width="120">图片数量</th>
							<th width="100">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ page}"  var="picAtlas"  varStatus="status">  
							<tr class="text-c">
								<!-- <td><input type="checkbox" value="" name=""></td> -->
								<td>${status.index+1 }</td>
								<td class="text-l">${picAtlas.atlasName }</td>
								<td>${picAtlas.atlasCode }</td>
								<td>${picAtlas.picNum }</td>
								<td class="f-14 td-manage">
									<page:showOpt type="link" code="update" title="编辑" method="atlasEdit('${picAtlas.id}')" iconfont="&#xe60c;"/>
									<page:showOpt type="link" code="delete" title="删除" method="atlasDel('${picAtlas.id}',this)" iconfont="&#xe6e2;"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
              	<page:page formId="atlas_list_form" page="${page}" />  
			</div>
		</article>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*新增 修改*/
function atlasEdit(id){
	var index = layer.open({
		type: 2,
		title: "产品详情编辑",
		area: ['893px', '600px'],
		content: "pic/editPicAtlas.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}

function atlasDel(id,obj){
	layer.confirm('删除该图集，可能影响系统业务正常显示,确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'pic/deleteAtlas.do',
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
