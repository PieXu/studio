<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
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
              	<page:page formId="goods_list_form" page="${page}" />  
