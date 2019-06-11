<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
 <table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
	<thead>
		<tr class="text-l">
			<th width="30" rowspan="2">序号</th>
			<th rowspan="2">字段名</th>
			<th width="80" rowspan="2">属性名</th>
			<th width="80" rowspan="2">显示名</th>
			<th width="70" rowspan="2">类型</th>
			<th width="210" colspan="2" class="text-c">列表域</th>
			<th width="200" colspan="2" class="text-c">查询域</th>
		</tr>
		<tr class="text-l">
			<th class="text-c" width="10%">显示宽度(px)</th>
			<th width="50%">转换标准</th>
			<th class="text-c" width="10%">显示宽度(px)</th>
			<th width="50%">转换标准</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${columns}" var="column"  varStatus="status">  
			<tr class="text-l">
				<td>${status.index+1 }</td>
				<td >${column.columnName }</td>
				<td>${column.attributeName}</td>
				<td><input type="text" value="${column.columnCommnet}" class="input-text radius" placeholder="显示名称" style="width:100%"></td>
				<td>${column.typeName}</td>
				<td style="margin: 2px;padding: 2px" >
					<select class="select-box radius" style="width:60%">
						<option value="1">显示</option>
						<option value="0">隐藏</option>
					</select>
					<input type="text" class="input-text radius"  style="width:35%;">
				</td>
				<td>
					<input type="text" class="input-text radius" placeholder="字典或数组" style="width:100%">
				</td>
				<td style="margin: 2px;padding: 2px" >
					<select class="select-box radius" style="width:60%">
						<option value="0">隐藏</option>
						<option value="1">显示</option>
					</select>
					<input type="text" class="input-text radius"  style="width:35%;">
				</td>
				<td>
					<input type="text" class="input-text radius" placeholder="字典或数组" style="width:100%;">
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
