<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
<style>.docs-queryfiled:after{content: "已存在设置列表"}</style>
			<div class="docs-queryfiled">
			<form action="code/saveCodeSet.do" method="post" class="form form-horizontal" id="form-admin-add">
				<table class="table table-border table-bordered table-bg">
					<thead>
						<tr class="text-c">
							<th colspan="4">当前使用配置</th>
						</tr>
					</thead>
					<tr class="text-c">
						<td width="10%"><span class="c-red">*</span>数据库驱动类</td>
						<td colspan="3" class="formControls" ><input type="text" class="input-text" value="${codeSet.dbDriver }" placeholder="输入数据库驱动类" id="dbDriver" name="dbDriver"></td>
					</tr>
					<tr class="text-c">
						<td width="10%"><span class="c-red">*</span>数据库连接</td>
						<td colspan="3"  class="formControls" ><input type="text" class="input-text"value="${codeSet.dbUrl }" placeholder="输入数据库连接" id="dbUrl" name="dbUrl"></td>
					</tr>
					<tr class="text-c">
						<td width="10%"><span class="c-red">*</span>代码目标路径</td>
						<td class="formControls" style="width:280px" ><input type="text" class="input-text" value="${codeSet.targetProject }" placeholder="输入代码目标路径" id="targetProject" name="targetProject"></td>
						<td style="width:100px">工程工作空间</td>
						<td class="formControls" ><input type="text" class="input-text" value="${codeSet.workPath }" placeholder="输入工作空间路径" id="workPath" name="workPath"></td>
					</tr>
					<tr class="text-c">
						<td width="10%"><span class="c-red">*</span>数据库用户名</td>
						<td class="formControls" ><input type="text" class="input-text" value="${codeSet.dbUser }" placeholder="输入数据库用户名" id="dbUser" name="dbUser"></td>
						<td width="10%"><span class="c-red">*</span>数据库密码</td>
						<td class="formControls" ><input type="text" class="input-text" value="${codeSet.dbPass }" placeholder="输入数据库密码" id="dbPass" name="dbPass"></td>
					</tr>
				</table>
			</form>
			<div class="mt-30">
				<page:showOpt code="add" title="加载当前DataSource配置" type="button" method="loadProperties();" />
				<page:showOpt code="add" title="保 存" type="button" method="saveCodeSet();" />
			</div>
			</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">

/* 加载当前的数据信息 */
function loadProperties(){
	layer.confirm('确认要加载当前系统配置的数据库信息吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'code/loadDataSource.do',
			dataType: 'json',
			success: function(data){
				if(data != null){
					$("#dbDriver").val(data.dbDriver);
					$("#dbUrl").val(data.dbUrl);
					$("#dbUser").val(data.dbUser);
					$("#dbPass").val(data.dbPass);
					$("#targetProject").val(data.targetProject);
				}
				layer.msg("加载成功...",{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data);
			},
		});		
	}); 
}

/*新增*/
function addCodeSet()
{
	clearSerachForm('form-admin-add');
	$("#id").val('');
}

function editDB(id)
{
	if(id){
		$.ajax({
			type: 'POST',
			url: 'code/editDataConfig.do',
			data:{"id":id},
			success: function(data){
				if(data != null){
					$("#dbDriver").val(data.dbDriver);
					$("#dbUrl").val(data.dbUrl);
					$("#dbUser").val(data.dbUser);
					$("#dbPass").val(data.dbPass);
					$("#targetProject").val(data.targetProject);
				}else{
					layer.msg("信息加载异常，请联系管理员",{icon:2,time:1000});
				}
			},
			error:function(data) {
				console.log(data);
			},
		});	
	}else{
		layer.msg("请选择需要编辑的设置信息",{icon:2,time:1000});
	}
}

/*保存*/
function saveCodeSet()
{
	$("#form-admin-add").submit();
}

/**删除该数据库连接设置*/
function delDB(id){
	layer.confirm('确认要删除该设置吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'code/deleteCodeSet.do',
			dataType: 'json',
			success: function(data){
				if(data.result == "success"){
					layer.msg(data.message,{icon:1,time:1000});
					$("#btn-refresh").click();
				}else{
					layer.msg(data.message,{icon:2,time:1000});
				}
			},
			error:function(data) {
				console.log(data);
			},
		});		
	});
}

/**
 * 更新状态
 */
function updateStatus(id,status){
	layer.confirm('确认要执行该操作吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id,'status':status},
			url: 'code/updateStatus.do',
			dataType: 'json',
			success: function(data){
				if(data.result == "success"){
					layer.msg(data.message,{icon:1,time:1000});
					$("#btn-refresh").click();
				}else{
					layer.msg(data.message,{icon:2,time:1000});
				}
			},
			error:function(data) {
				console.log(data);
			},
		});		
	});
}
		

$(function(){
	$("#form-admin-add").validate({
		rules:{
			dbDriver:{
				required:true,
				minlength:1,
				maxlength:100
			},
			dbUrl:{
				required:true,
				minlength:1,
				maxlength:100
			},
			targetProject:{
				required:true,
				minlength:1,
				maxlength:100
			},
			dbPass:{
				required:true,
				minlength:1,
				maxlength:100
			},
			dbUser:{
				required:true,
				minlength:1,
				maxlength:100
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "code/saveCodeSet.do",
			   data: $(form).serialize() ,
			   success: function(data){
				   if( data.result == "success"){
						$('#btn-refresh').click();
				   }else{
					   layer.msg(data.message,{icon:2,time:1000});
				   }
			   }
			});
		}
	});
});
</script>
