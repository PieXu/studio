<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
		<div class="docs-queryfiled">
			访问状态：
			<span class="select-box inline radius">
				<page:select name="shopState" selectedVal="${shop.shopState}" dicList="${shopStateList}"></page:select>
				<%-- <select name="shopState" id="shopState" class="select radius">
					<option value="">--选择--</option>
					<c:if test="${!empty shopStateList }">
						<c:forEach items="${shopState }" var="state" varStatus="status">
							<option value="${state.id }" <c:if test="${state.id eq shop.shopState }">selected</c:if> >${state.name }</option>
						</c:forEach>
					</c:if>
				</select> --%>
			</span> 
			<span style="padding-left:15px">
				商户名称：<input type="text" name="shopName" id="shopName" value="${shop.shopName }" placeholder="商铺名称" style="width:280px" class="input-text radius">
			</span>
			<span style="padding-left:10px">
				<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
				<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
			</span>
		</div>
	
	<div class="mt-30">
		<page:showOpt code="add" title="新增" type="button" method="goods_edit('');" />
	</div>
		 <table class="table table-border table-bordered table-bg table-hover table-sort "mt-10">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="50">序号</th>
					<th>名称</th>
					<th width="260">访问状态</th>
					<th width="100">访问秘钥</th>
					<th width="120">创建时间</th>
					<th width="60">修改时间</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${ page}" var="shop"  varStatus="status">  
					<tr class="text-c">
						<td><input type="checkbox" value="" name=""></td>
						<td>${status.index+1 }</td>
						<td class="text-l">${shop.shopName }</td>
						<td><c:if test="${!empty goodsType }">${goodsType[goods.goodsType]}</c:if></td>
						<td>${goods.price }</td>
						<td>${goods.amount}</td>
						<td><page:valueToName value="${goods.goodsTag }" array="{'0':'否','1':'是'}"/></td>
						<td class="f-14 td-manage">
							<c:choose>
								<c:when test="${goods.goodsTag eq 1 }">
									<page:showOpt type="link" code="setting" title="取消推荐" method="cancleHot('${goods.id}')" iconfont="&#xe631;"/>
								</c:when>
								<c:otherwise>
									<page:showOpt type="link" code="setting" title="推荐热销" method="setHot('${goods.id}')" iconfont="&#xe615;"/>
								</c:otherwise>
							</c:choose>
							<page:showOpt type="link" code="update" title="编辑" method="goods_edit('${goods.id}')" iconfont="&#xe60c;"/>
							<page:showOpt type="link" code="delete" title="删除" method="goods_del('${goods.id}',this)" iconfont="&#xe6e2;"/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
        <page:page formId="list_query_form" page="${page}" ajaxType="true" /> 

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
/*新增 修改*/
function goods_edit(id){
	var index = layer.open({
		type: 2,
		title: "产品编辑",
		area: ['893px', '600px'],
		content: "goods/editGoods.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	 	}
	}); 
}
function goods_del(id,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'goods/deleteGoods.do',
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
		url: 'goods/changeIsHot.do',
		dataType: 'json',
		success: function(data){
			if( data.result == "success"){
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
}
</script>
