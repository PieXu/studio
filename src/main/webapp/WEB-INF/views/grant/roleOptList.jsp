<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
<!-- <form action="grant/saveRoleOpt.do" method="post" class="form form-horizontal" id="form-roleopt-grant"> -->
	<input type="hidden" name="menuId" id="menuId" value="${menuId }">
	<table class="table table-border table-bordered table-bg table-hover table-sort">
		<thead>
			<tr class="text-c">
				<th width="80">
				  角色 \ 操作
				</th>
				<c:if test="${!empty optList }">
					<c:forEach items="${optList }" var="opt" varStatus="status">
						<th width="80">${opt.name }</th>
					</c:forEach>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:if test="${!empty roleList }">
			<c:forEach items="${roleList }" var="role" varStatus="status">
				<tr class="text-c">
					<td>
					  ${role.name }
					</td>
					<c:if test="${!empty optList }">
						<c:forEach items="${optList }" var="opt" varStatus="status">
							<td><input type="checkbox"  name="role_opt_check" <page:checkContains list='${checkList }' value='${opt.id }_${role.id}'/>
								 value="${opt.id },${role.id}"/></td>
						</c:forEach>
					</c:if>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
<!-- </form> -->
<div class="mt-30 text-c"  >
	<a class="btn btn-secondary radius" data-title="保 存" onclick="saveRoleOpt();" href="javascript:void(0);">保 存</a>
	<a class="btn btn-default radius" data-title="刷 新" onclick="initRoleOptByResId();" href="javascript:void(0);">刷 新</a>
</div>
