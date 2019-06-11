<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="page" uri="http://com.innovate.page.tld" %>
<link rel="stylesheet" href="lib/jquery-treetable-3.2.0/css/jquery.treetable.css" type="text/css">
<link rel="stylesheet" href="lib/jquery-treetable-3.2.0/css/jquery.treetable.theme.default.css" type="text/css">
			<table class="table table-border table-bordered table-bg table-hover table-sort  mt-10" id="example-tabletree">
			<thead>
				<tr class="text-l">
					<th>名称</th>
					<th width="150">编码</th>
					<th width="70">类型</th>
					<th width="150">链接</th>
					<th >操作权限</th>
					<th width="60" class="text-c">图标</th>
					<th width="150">操作</th>
				</tr>
			</thead>
			<tbody>
				<tr class="text-l" data-tt-id="_0">
					<td colspan="6">菜单管理</td>
					<td>
						<a style="text-decoration:none" class="ml-6" onClick="res_add('')" href="javascript:;" title="编辑">[添加下级]</a>
					</td>
				</tr>
				<c:if test="${!empty treeList }">
					<c:forEach items="${treeList }" var="res">
							<c:choose>
								<c:when test="${!empty res.parent }">
									<tr data-tt-id="${res.id}" data-tt-parent-id="${res.parent }">
								</c:when>
								<c:otherwise>
									<tr data-tt-id="${res.id}" data-tt-parent-id="_0">
								</c:otherwise>
							</c:choose>
						    <td>${res.resName }</td>
						    <td>${res.code }</td>
						    <td><page:idToName dicId="${res.menuType }"/></td>
						    <td>${res.link }</td>
						    <td>${res.resOpt }</td>
						    <td  class="text-c"><c:if test="${!empty res.classStyle }"><i class="Hui-iconfont  ${res.classStyle }" style="font-size:24px"></i></c:if></td>
						    <td style="font-size: 14px">
						    	<a style="text-decoration:none" class="ml-6" onClick="res_del('${res.id}',this)" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
							    <a style="text-decoration:none" class="ml-5" onClick="res_add('${res.id}')" href="javascript:;" title="添加下级菜单"><i class="Hui-iconfont">&#xe610;</i></a>
						    	<a style="text-decoration:none" class="ml-5" onClick="res_edit('${res.id}')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe60c;</i></a>
						    	<a style="text-decoration:none" class="ml-5" onClick="upSeq('${res.id}','${res.parent}','${res.seqNum}')" href="javascript:;" title="上移"><i class="Hui-iconfont">&#xe6dc;</i></a>
						    	<a style="text-decoration:none" class="ml-5" onClick="downSeq('${res.id}','${res.parent}','${res.seqNum}')" href="javascript:;" title="下移"><i class="Hui-iconfont">&#xe6de;</i></a>
						    	<c:if test="${!empty menuType and !empty res.menuType and res.menuType eq menuType.id }">
						    		<a style="text-decoration:none" class="ml-5" onClick="setResOpt('${res.id}')" href="javascript:;" title="操作授权"><i class="Hui-iconfont">&#xe616;</i></a>
						    	</c:if>
						    </td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery-treetable-3.2.0/jquery.treetable.js"></script> 
<script type="text/javascript">
/**
 * 上移
 */
function upSeq(id,parent,seq)
{
	if(seq == 1 || seq == "1"){
		layer.msg("当前菜单已经是第一个，不能上移",function(){})
	}else{
		$.ajax({
			type: 'POST',
			data:{'id':id,'parent':parent,'seq':seq},
			url: 'res/upSeq.do',
			dataType: 'json',
			success: function(data){
				if(data.result == "success"){
					layer.msg(data.message,{icon:1,time:1000});
					$("#btn-refresh").click();
				}else{
					layer.msg(data.message,{icon:1,time:1000});
				}
			},
			error:function(data) {
				console.log(data);
			},
		});	
	}
}

/**
 * 下移
 */
function downSeq(id,parent,seq)
{
	$.ajax({
		type: 'POST',
		data:{'id':id,'parent':parent,'seq':seq},
		url: 'res/dowSeq.do',
		dataType: 'json',
		success: function(data){
			if(data.result == "success"){
				layer.msg(data.message,{icon:1,time:1000});
				$("#btn-refresh").click();
			}else{
				layer.msg(data.message,{icon:1,time:1000});
			}
		},
		error:function(data) {
			console.log(data);
		},
	});
}

//删除菜单
function res_del(id,obj){
	if(id){
		$.post("res/checkHasChild.do", { "parent":id },
		   function(data){
			if(data == null){
				layer.alert('删除异常，请联系管理员！', {icon: 6});
			}else if(data ==  0){
				 delObj(id,obj); 
		    }else{
		    	 layer.alert('当前菜单下存在子菜单，不能删除！', {icon: 6});
		    }
	   });
	}else{
		layer.alert('请选中要删除的记录', {icon: 3});
	}
}

function res_edit(id){
	var index = layer.open({
		type: 2,
		title: "菜单编辑",
		area: ['580px', '480px'],
		content: "res/editRes.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	}); 
}

function res_add(id)
{
	var index = layer.open({
		type: 2,
		title: "菜单添加",
		area: ['580px', '480px'],
		content: "res/editRes.do?parent="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  }
	}); 
}

function delObj(id,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			data:{'id':id},
			url: 'res/deleteRes.do',
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

/*菜单操作授权*/
function setResOpt(id){
	var index = layer.open({
		type: 2,
		title: "设置操作",
		area: ['800px', '520px'],
		content: "res/editResOpt.do?resId="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}


$('#example-tabletree').treetable({
	expandable: true,
	initialState :"expand",//默认打开所有节点 collapsed   expand 
	stringCollapse:'关闭',    
	stringExpand:'展开',
	clickableNodeNames:true,
	onInitialized:function () { 
		//expandNode("_0");
		$("#example-tabletree").treetable("expandNode", "_0");
    } 
});
</script>