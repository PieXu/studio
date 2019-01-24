<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="order/listOrder.do" method="post" id="order_list_form" >
				<div class="docs-queryfiled">
					订单状态：
					<span class="select-box inline radius">
						<c:if test="${!empty orderStatus }">
							<select name="orderStatus" id="orderStatus" class="select radius">
								<option value="">全部...</option>
								<c:forEach items="${orderStatus }" var="oStatus" varStatus="status">
								<option value="${oStatus.key }" <c:if test="${oStatus.key eq order.orderStatus }">selected</c:if> >${oStatus.value }</option>
								</c:forEach>
							</select>
						</c:if>
					</span> 
					<span style="padding-left:15px">
						订单号：<input type="text" name="id" id="id" value="${order.id }" placeholder=" 订单号" style="width:240px" class="input-text radius">
					</span>
					<span style="padding-left:10px">
						<button class="btn btn-primary radius" type="submit">查 询</button>
						<button class="btn btn-success radius" type="button" onclick="clearSerachForm('order_list_form')">清 空</button>
					</span>
				</div>
			</form>
			<div class="mt-10">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="50">序号</th>
							<th>订单号</th>
							<th width="100">用户</th>
							<th width="120">商品</th>
							<th width="60">单价</th>
							<th width="60">数量</th>
							<th width="60">总价</th>
							<th width="60">订单状态</th>
							<th width="100">创建时间</th>
							<th width="100">修改时间</th>
							<th width="50">操作</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${ page}"  var="order"  varStatus="status">  
							<tr class="text-c">
								<td>${status.index+1 }</td>
								<td class="text-l">${order.id }</td>
								<td>${order.userName }</th>
								<td>${order.goodsName }</td>
								<td>${order.price }</td>
								<td>${order.amount }</td>
								<td>${order.totalPrice }</td>
								<td><c:if test="${!empty order.orderStatus }">${orderStatus[order.orderStatus]}</c:if></td>
								<td><fmt:formatDate value="${credits.createTime}" pattern="yyyy-MM-dd"/></td>
								<td><fmt:formatDate value="${credits.updateTime}" pattern="yyyy-MM-dd"/></td>
								<td class="f-14 td-manage">
								<c:choose>
									<c:when test="${goods.goodsTag eq 1 }">
										<page:showOpt type="link" code="setting" title="取消推荐" method="cancleHot('${goods.id}')" iconfont="&#xe631;"/>
									</c:when>
									<c:otherwise>
										<page:showOpt type="link" code="setting" title="推荐热销" method="setHot('${goods.id}')" iconfont="&#xe615;"/>
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
              	<page:page formId="order_list_form" page="${page}" />  
			</div>
		</article>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
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
				$('#order_list_form').submit();
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
