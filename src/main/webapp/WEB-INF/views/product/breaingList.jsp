<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="product/listBreaing.do" method="post" id="breaing_list_form" >
				<div class="docs-queryfiled">
					分类：
					<span class="select-box inline radius">
						<page:select dicList="${ categoryList}" name="category" styleClass="select radius" selectedVal="${breaing.category }" defaultOption="全部分类"/>
					</span>
					<span style="padding-left:15px">
						型号：<input type="text" name="name" id="name" value="${breaing.productName }" placeholder=" 轴承型号" style="width:120px" class="input-text radius">
					</span>
					<span style="padding-left:15px">
						类型：<input type="text" name="type" id="type"  value="${breaing.type }"  placeholder=" 轴承类型" style="width:150px" class="input-text radius">
					</span>
					<span style="padding-left:10px">
						<button class="btn btn-primary radius" type="submit">查 询</button>
						<button class="btn btn-success radius" type="button" onclick="clearSerachForm('breaing_list_form')">清 空</button>
					</span>
				</div>
			</form>
			<div class="mt-30">
				<page:showOpt code="add" title="新增" type="button" method="breaing_edit('');" />
				<!-- <a class="btn btn-primary radius" data-title="新增" _href="article-add.html" onclick="breaing_edit('');" href="javascript:;">新增</a>
				<a href="javascript:;" onclick="breaing_del()" class="btn btn-danger radius" data-title="删除">删除</a> -->
			</div>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th width="50">序号</th>
							<th>名称</th>
							<th width="100">型号</th>
							<th width="80">分类</th>
							<th width="60">内径</th>
							<th width="60">外径</th>
							<th width="60">厚度</th>
							<th width="60">首页推荐</th>
							<th width="80">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ page}"  var="breaing"  varStatus="status">  
							<tr class="text-c">
								<td><input type="checkbox" value="" name=""></td>
								<td>${status.index+1 }</td>
								<td class="text-l">${breaing.productName }</td>
								<td>${breaing.type }</td>
								<td><page:idToName dicId="${breaing.category }"/></td>
								<td>${breaing.inDiameter }</td>
								<td>${breaing.outDiameter }</td>
								<td>${breaing.thickness }</td>
								<td><page:valueToName value="${breaing.isHot }" array="{'0':'否','1':'是'}"/></td>
								<td class="f-14 td-manage">
									<c:choose>
										<c:when test="${breaing.isHot eq 1 }">
											<page:showOpt type="link" code="setting" title="取消推荐" method="cancleHot('${breaing.id}')" iconfont="&#xe631;"/>
											<%-- <a style="text-decoration:none" class="ml-5" onClick="cancleHot('${breaing.id}')" href="javascript:;" title="取消推荐">[取消]</a> --%>
										</c:when>
										<c:otherwise>
											<page:showOpt type="link" code="setting" title="推荐热销" method="setHot('${breaing.id}')" iconfont="&#xe615;"/>
											<%-- <a style="text-decoration:none" class="ml-5" onClick="setHot('${breaing.id}')" href="javascript:;" title="推荐热销">[推荐]</a> --%>
										</c:otherwise>
									</c:choose>
									<page:showOpt type="link" code="update" title="编辑" method="breaing_edit('${breaing.id}')" iconfont="&#xe60c;"/>
									<page:showOpt type="link" code="delete" title="删除" method="breaing_del('${breaing.id}',this)" iconfont="&#xe6e2;"/>
<%-- 									<a style="text-decoration:none" class="ml-5" onClick="breaing_edit('${breaing.id}')" href="javascript:;" title="编辑">[编辑]</a>
									<a style="text-decoration:none" class="ml-5" onClick="breaing_del('${breaing.id}',this)" href="javascript:;" title="删除">[删除]</a> --%>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
              	<page:page formId="breaing_list_form" page="${page}" />  
			</div>
		</article>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*新增 修改*/
function breaing_edit(id){
	var index = layer.open({
		type: 2,
		title: "产品详情编辑",
		area: ['893px', '600px'],
		content: "product/editBreaing.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}

function breaing_del(id,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'product/deleteBreaing.do',
			dataType: 'json',
			success: function(data){
				// var  data = $.parseJSON( data );
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
	layer.confirm('确认要撤销首页推荐吗？',function(index){
		changeIsHot(id,"0");
	});
}

/*资讯-发布*/
function setHot(id){
	layer.confirm('确认要推荐到首页吗？',function(index){
		changeIsHot(id,"1");
	});
}

/*首页推荐发布设置*/
function changeIsHot(id,val){
	$.ajax({
		type: 'POST',
		data:{'id':id,'isHot':val},
		url: 'product/changeIsHot.do',
		dataType: 'json',
		success: function(data){
			if( data.result == "success"){
				layer.msg(data.message,{icon:1,time:1000});
				$('#breaing_list_form').submit();
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
