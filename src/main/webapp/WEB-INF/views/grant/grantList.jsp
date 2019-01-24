<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<!--自定义样式 解决样式冲突问题-->
<style>
	.ztree ul li .line{border:0}
	.ztree{border:1px solid #ddd;min-height: 500px}
</style>
<article class="cl pd-20">
	<input type="hidden" id="menuFunType"  value="${menuFunType.id }"/>
	<table class="table">
		<tr>
			<td width="200" class="va-t" style="height: 90%"><ul id="treeDemo" class="ztree" ></ul></td>
			<td class="va-t" id="user_opt_td">
				
			</td>
		</tr>
	</table>
</article>
<script type="text/javascript">
/**
 * 设置
 */
var setting = {
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true,
		}
	},
	callback: {
		onClick: zTreeOnClick
	}
};
/**
 * 展开 显示 右侧操作授权
 */
var res_menuId;
function zTreeOnClick(event, treeId, treeNode)
{
	var funMenuType = $("#menuFunType").val();
	res_menuId = treeNode.id;
	if(funMenuType && treeNode.menuType && funMenuType == treeNode.menuType){
		initRoleOptByResId();
	}else{
		layer.msg("请选择功能菜单设置",function(){});
		$("#user_opt_td").html("");
	}
	
}
/**
 * 保存设置的信息
 */
function saveRoleOpt()
{
	layer.confirm('确定要保存角色的操作授权信息吗？', {
	  btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
		   type: "POST",
		   url: "grant/saveRoleOpt.do",
		   data: $("#form-roleopt-grant").serialize() ,
		   success: function(data){
			   //data =  $.parseJSON( data )
			   if( data.result == "success"){
				   layer.msg(data.message);
				   //initRoleOptByResId();
			   }else{
				   layer.msg(data.message,{icon:2,time:1000});
			   }
		   }
		});
	});
}

/**
 * 加载设置的信息
 */
function initRoleOptByResId()
{
	 $("#user_opt_td").load("grant/roleOptList.do", {"menuId": res_menuId}, function(){});	
}

$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, ${resJson});
});
</script>
