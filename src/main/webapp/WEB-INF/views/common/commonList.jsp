<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
<!-- 页面参数隐藏部分 -->	
<input type="hidden" name="_tabelName" id="_tabelName" value="${pageDefine.tableName }"/>
<input type="hidden" name="_pageId" id="_pageId" value="${pageDefine.id }"/>
<!-- 查询条件部分 -->	
<div class="docs-queryfiled">
	<page:showQueryFiled list="${queryList}" conditions="${conditions }"/>
	<!-- 不写死到后台，方便按钮的样式文字等的修改 -->
	<button class="btn btn-primary radius" type="button" onclick="commonQuery(true)">查 询</button>
	<button class="btn btn-success radius" type="button" onclick="resetSerachForm()">清 空</button>
</div>
<!-- 列表操作按钮部分 -->
<div class="mt-20"><page:optArea /></div>
<!-- 数据列表分页部分 -->
<div  class="mt-10">
	<page:showListFiled list="${filedList}" page="${page}"/>
	<page:page formId="list_query_form" page="${page}" ajaxType="true" />
</div>
<script>
	$(function() {
		$("[data-toggle='tooltip']").tooltip()
	});
</script>
	
	<%-- <table class="table table-border table-bordered table-bg table-hover table-sort mt-10">
		<thead>
			<tr class="text-l">
				<!-- <th width="25"><input type="checkbox" name="" value=""></th> -->
				<th width="50">序号</th>
				<th>姓名</th>
				<th width="80">登录名</th>
				<th width="100">用户类型</th>
				<th width="60">性别</th>
				<th width="60">年龄</th>
				<th width="100">状态</th>
				<th width="120">最后登录时间</th>
				<th width="120">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="user" items="${ page}" varStatus="status">  
			<tr class="text-l">
				<!-- <td><input type="checkbox" value="" name=""></td> -->
				<td>${status.index+1 }</td>
				<td class="text-l">${user.userName }</td>
				<td>${user.loginName }</td>
				<td><page:idToName dicId="${user.userType }"/> </td>
				<td><page:idToName dicId="${user.gender }"/></td>
				<td>${user.age }</td>
				<td><page:idToName dicId="${user.status }"/></td>
				<td><fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td class="f-14 td-manage">
					<page:showOpt type="link" code="update" title="编辑" method="editUser('${user.id}')" iconfont="&#xe60c;"/>
					<page:showOpt type="link" code="grant" title="设置角色" method="setRole('${user.id}')" iconfont="&#xe653;"/>
					<a style="text-decoration:none" class="ml-5" onClick="setRole('${user.id}')" href="javascript:;" title="设置角色"><i class="Hui-iconfont">&#xe653;</i></a>
					<c:if test="${!empty userStateEnable and userStateEnable.id ne user.status }">
						<page:showOpt type="link" code="setting" title="启用" method="enableUser('${user.id}')" iconfont="&#xe615;"/>
						<a style="text-decoration:none" class="ml-5" onClick="enableUser('${user.id}')" href="javascript:;" title="启用"><i class="Hui-iconfont">&#xe615;</i></a>
					</c:if>
					<c:if test="${!empty userStateEnable and userStateEnable.id eq user.status }">
						<page:showOpt type="link" code="setting" title="停用" method="disableUser('${user.id}')" iconfont="&#xe631;"/>
						<a style="text-decoration:none" class="ml-5" onClick="disableUser('${user.id}')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a>
					</c:if>
					<page:showOpt type="link" code="reset" title="重置密码" method="resetPassword('${user.id}')" iconfont="&#xe60e;"/>
									<a style="text-decoration:none" class="ml-5" onClick="resetPassword('${user.id}')" href="javascript:;" title="重置密码"><i class="Hui-iconfont">&#xe60e;</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="delUser('${user.id}')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
					<page:showOpt type="link" code="delete" title="删除" method="delUser('${user.id}')" iconfont="&#xe6e2;"/>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table> --%>
	
	<%-- <page:page formId="list_query_form" page="${page}" ajaxType="true" />  --%>
<!-- <script type="text/javascript">
/*添加*/
function editUser(id){
	var index = layer.open({
		type: 2,
		title: "用户编辑",
		area: ['600px', '520px'],
		content: "user/userEdit.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}

/*设置角色*/
function setRole(id){
	var index = layer.open({
		type: 2,
		title: "设置角色",
		area: ['600px', '480px'],
		content: "user/userRoleList.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}

/*删除*/
function delUser(id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'user/deleteUser.do',
			data:{'id':id},
			success: function(data){
				 //var data = $.parseJSON( data );
				if( data.result == "success"){
					layer.msg(data.message,{icon:1,time:1000});
					commonQuery();
				}else{
					layer.msg(data.message,{icon:1,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}
 
/*密码重置*/
function resetPassword(id){
	layer.confirm('确认要重置密码吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'user/resetPassword.do',
			data:{'id':id},
			success: function(data){
				 // var data = $.parseJSON( data );
				if( data.result == "success"){
					layer.msg(data.message,{icon:1,time:1000});
				}else{
					layer.msg(data.message,{icon:1,time:1000});
				}
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
} 

/*账户禁用*/
function disableUser(id){
	layer.confirm('确认要停用该用户吗？',function(index){
		changeUserStatus(id,'-1');	
	});
} 

/*账户启用*/
function enableUser(id){
	layer.confirm('确认要启用该用户吗？',function(index){
		changeUserStatus(id,'0');
	});
} 

function changeUserStatus(id,status){
	$.ajax({
		type: 'POST',
		url: 'user/changeStatus.do',
		data:{'id':id,'status':status},
		success: function(data){
			 // var data = $.parseJSON( data );
			if( data.result == "success"){
				layer.msg(data.message,{icon:1,time:1000});
				commonQuery();
			}else{
				layer.msg(data.message,{icon:1,time:1000});
			}
		},
		error:function(data) {
			console.log(data.msg);
		},
	});	
}

</script>
 -->

<!-- <script type="text/javascript">
$(document).ready(function(){
	var load = layer.load(3,{shade:0.1});
	$.ajax({
		   type: "POST",
		   url: "${_redirect_link}",
		   data: $("#list_query_from").serialize() ,
		   success: function(data){
			   setTimeout(function(){
				   $("#_main_body").html(data);
				   layer.closeAll('loading');
				 }, 500);
		   }
	});
});

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
				$('#goods_list_form').submit();
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
 -->