<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
			<table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
				<thead>
					<tr class="text-c">
						<th width="50">序号</th>
						<th width="100">用户名</th>
						<th width="120">访问主机</th>
						<th >Session编号</th>
						<th width="150">登录时间</th>
						<th width="150">最后访问时间</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="onlinUser" items="${ onlineUser}" varStatus="status">  
					<tr class="text-c">
						<td>${status.index+1 }</td>
						<td>${onlinUser.loginUser }</td>
						<td>${onlinUser.host }</td>
						<td>${onlinUser.sessionId }</td>
						<td><fmt:formatDate value="${onlinUser.startDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${onlinUser.lastAccessDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td>
							<a href="javascript:void(0)" onclick="clickQuit('${onlinUser.loginUser }','${onlinUser.sessionId }');">踢出</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
<script type="text/javascript">
/**
 * 踢出
 */
function clickQuit(user,sessionId)
{
	layer.confirm('确定要踢出用户 '+user+" 吗？", {
	  btn: ['确 定','取 消'] //按钮
	}, function(){
		$.post("security/kickout.do", { "sessionId": sessionId,"userName":user},
		   function(data){
			   if( data.result == "success"){
				   layer.msg(data.message,{icon:1,time:1000});
				   //stompClient.send("/app/hello", {}, JSON.stringify({ 'userName': user }));
				   $("#form-onlineuser").submit();
				  // stompClient.send("/hello", {}, JSON.stringify({'userName': user}));
			   }else{
				   layer.msg(data.message,{icon:2,time:2000});
			   }
	    });
	});
}
</script>