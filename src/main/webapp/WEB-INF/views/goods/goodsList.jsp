<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
<article class="cl pd-20">
		<div class="docs-queryfiled">
			分类：
			<span class="select-box inline radius">
				<c:if test="${!empty goodsType }">
					<select name="goodsType" id="goodsType" class="select radius">
						<option value="">全部类型</option>
						<c:forEach items="${goodsType }" var="type" varStatus="status">
						<option value="${type.key }" <c:if test="${type.key eq goods.goodsType }">selected</c:if> >${type.value }</option>
						</c:forEach>
					</select>
				</c:if>
			</span> 
			<span style="padding-left:15px">
				名称：<input type="text" name="goodsName" id="goodsName" value="${goods.goodsName }" placeholder=" 商品名称" style="width:280px" class="input-text radius">
			</span>
			<span style="padding-left:10px">
				<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
				<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
			</span>
		</div>
	
	<div class="mt-30">
		<page:showOpt code="add" title="新增" type="button" method="goods_edit('');" />
	</div>
	<div class="mt-10" id="pageTable">
		 <table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="" value=""></th>
					<th width="50">序号</th>
					<th>名称</th>
					<th width="260">类型</th>
					<th width="100">单价</th>
					<th width="120">数量</th>
					<th width="60">首页推荐</th>
					<th width="80">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${ page}"  var="goods"  varStatus="status">  
					<tr class="text-c">
						<td><input type="checkbox" value="" name=""></td>
						<td>${status.index+1 }</td>
						<td class="text-l">${goods.goodsName }</td>
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
	</div>
	</form>
</article>

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
