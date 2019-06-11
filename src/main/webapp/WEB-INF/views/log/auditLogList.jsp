<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
			<div class="docs-queryfiled">
				用户名：<input type="text" id="visitorName" name="visitorName" value="${log.visitorName }" class="input-text radius" placeholder="访问用户名" style="width:120px;">
				&nbsp;&nbsp;
				<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
				<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
			</div>
				<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
					<thead>
						<tr class="text-c">
							<th width="50">序号</th>
							<th>访问类</th>
							<th width="100">访问方法</th>
							<th width="120">访问时间</th>
							<th width="60">访问结果</th>
							<th width="100">用户名</th>
							<th width="100">访问IP</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="log" items="${ page}" varStatus="status">  
						<tr class="text-c">
							<td>${status.index+1 }</td>
							<td>${log.className }</td>
							<td>${log.methodName }</td>
							<td><fmt:formatDate value="${log.visitDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td>${log.visitResult }</td>
							<td>${log.visitorName }</td>
							<td>${log.visitIp }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				<page:page formId="list_query_form" page="${page}" ajaxType="true" />  
