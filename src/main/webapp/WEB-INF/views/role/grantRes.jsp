<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.excheck-3.5.min.js"></script>
<!--自定义样式 解决样式冲突问题-->
<style>
	.ztree ul li .line{border:0}
</style>
<article class="cl pd-20">
	<input type="hidden" id="sub_btn" onclick="saveUserRes();"/>
	<input type="hidden" id="roleId" id="roleId"  value="${roleId }"/>
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</article>
<script type="text/javascript">
var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox"
	},
	data: {
		simpleData: {
			enable: true,
			
		}
	}
};
function saveUserRes()
{
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var roleId = $("#roleId").val();
	var nodes = treeObj.getCheckedNodes(true);
	if(nodes){
		var ids = "";
		$(nodes).each(function(index,node){
			if(node.id !="-1" && node.id){
				ids +=node.id+","
			}
		})
	}
	if(ids){
		$.post("role/saveRoleRes.do", {"ids":ids,"roleId":roleId},
	   		function(data){
				//data = $.parseJSON( data );
			  if( data.result == "success"){
				  layer.alert(data.message, {
				    closeBtn: 0
				  }, function(){
					   var index = parent.layer.getFrameIndex(window.name);
					   parent.layer.close(index);
				  });
			   }else{
				   layer.msg(data.message,{icon:2,time:1000});
			   }
	     });
	}
}
$(document).ready(function(){
	$.fn.zTree.init($("#treeDemo"), setting, ${resJson});
});
</script>
