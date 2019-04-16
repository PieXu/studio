<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="page" uri="http://com.innovate.page.tld" %>
		<article class="cl pd-10">
			<div class="mt-10">
				<a class="btn btn-primary radius" data-title="新增" onclick="editOpt('');" href="javascript:;">新增</a>
				<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius" data-title="批量删除">批量删除</a>
			</div>
				<div class="row cl">
					<div class="formControls col-xs-12 col-sm-11">
						<dl class="permission-list">
							<dt>
								<label>
									<input type="checkbox" value="" name="user-Character-0" onclick="_checkAll(this,'optId')">&nbsp;&nbsp;全选
								</label>
							</dt>
							<dd>
								<dl class="cl permission-list2" style="border:0">
									<c:if test="${!empty optList }">
										<c:forEach items="${optList }" var="opt" varStatus="status">
											<dt>
												<label class="" title="编码：${opt.code }" data-toggle="tooltip" data-placement="bottom" >
													<input type="checkbox"
													 value="${opt.id }" name="optId" id="${opt.id }"><b>&nbsp;${opt.name }</b>
													 <a style="text-decoration:none" class="ml-5" onClick="editOpt('${opt.id}')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe60c;</i></a>
												</label>
											</dt>
										</c:forEach>
									</c:if>
								</dl>
							</dd>
						</dl>
					</div>
				</div>
		</article>
<script type="text/javascript">

/*编辑 新增*/
function editOpt(id)
{
	var index = layer.open({
		type: 2,
		title: "操作编辑",
		area: ['460px', '380px'],
		content: "opt/optEdit.do?id="+id,
		btn: ['保存', '关闭']
		,yes: function(){
	    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
	  	}
	});
}

/*删除*/
function datadel()
{
	var len = $("input[name='optId']:checked").length;
	if(len > 0 ){
		$.ajax({
		   type: "POST",
		   url: "opt/checkDelete.do",
		   data: $("#form-opt").serialize() ,
		   success: function(data){
			   //data = $.parseJSON( data );
			   if( data.result == "success"){
				   layer.confirm(data.message, {
					   btn: ['确定','取消'] //按钮
					 }, function(){
						 doDelete();
					 }, function(){
					 });
			   }else{
				   layer.msg(data.message,{icon:2,time:1000});
			   }
		   }
		});
	}else{
		  layer.msg("请选择要删除的操作",{icon:3,time:2000});
	}
}

function doDelete()
{
	$.ajax({
	   type: "POST",
	   url: "opt/deleteOpts.do",
	   data: $("#form-opt").serialize() ,
	   success: function(data){
		   //data = $.parseJSON( data );
		   if( data.result == "success"){
			   layer.msg(data.message,{icon:1,time:1000});
			   $("#btn-refresh").click();
		   }else{
			   layer.msg(data.message,{icon:2,time:1000});
		   }
	   }
	});
}
</script>
