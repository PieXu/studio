<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://com.innovate.page.tld" prefix="page"%>
<style>.docs-queryfiled:after{content:"数据库中存在表结构"}</style>
	<div class="Hui-article">
		<article class="cl pd-20">
			<form action="gen/codeCreated.do" method="post" id="gencode_gen_form" >
				<div class="docs-queryfiled">
					<div class="row cl">
						<div class="formControls col-md-12">
							<dl class="permission-list" style="border:0">
								<dd>
									<dl class="cl permission-list2" style="border:0">
										<c:if test="${!empty tableList }">
											<c:forEach items="${tableList }" var="table" varStatus="status">
												<dt style="width:330px;height: 35px">
													<label style="width:180px">
														<input type="checkbox" value="${table.tableName }" name="tableName" onchange="checkChange(this)">&nbsp;${table.tableName }
													</label>
													&nbsp;
													<input type="text" height="20px"  class="input-text" name="domainName" id="${table.tableName }_domainName" value="" style="width: 100px;padding: 0;display: none"  placeholder="  实体类名"  title="对应的实体类名" data-toggle="tooltip" data-placement="right" />
												</dt>
											</c:forEach>
										</c:if>
									</dl>
								</dd>
							</dl>
							<!-- 生成 -->
							<h4 style="color: black;">生成的内容</h4>
							<dl class="permission-list" style="border:0">
								<dd>
									<dl class="cl permission-list2" style="border:0">
										<dt style="width:120px">
											<label class="">
												<input type="checkbox" value="_mapper" name="genType">&nbsp;DAO接口
											</label>
										</dt>
										<dt style="width:100px">
											<label class="">
												<input type="checkbox" value="_model" name="genType">&nbsp;实体类
											</label>
										</dt>
										<dt style="width:120px">
											<label class="">
												<input type="checkbox" value="_xml" name="genType">&nbsp;映射文件
											</label>
										</dt>
									</dl>
								</dd>
								<dd>
								<dl class="cl permission-list2" style="border:0">
										<dt style="width:300px">
											<label class=""><span class="c-red">*</span>代码包路径</label>
											<input type="text" style="width: 200px" class="input-text" value="" placeholder="输入代码包路径,如 com.test.*" id="packagePath" name="packagePath">
										</dt>
										<dt style="width:350px">
											<label class="">工程目录</label>
											<input type="text" style="width: 280px"  class="input-text" value="" placeholder="输入工程目录,如果在当前项目生成，可为空" id="workPath" name="workPath">
										</dt>
										<dt style="width:60px">
											<page:showOpt code="add" title="代码生成" type="button" method="genCode();" styleClass="btn btn-success radius"/>
										</dt>
									</dl>
								</dd>
							</dl>
						</div>
					</div>
				</div>
			</form>
		</article>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
function checkChange(obj)
{
	var tableId = $(obj).val();
	if($(obj).is(":checked")){
		$("#"+tableId+"_domainName").show();
	}else{
		$("#"+tableId+"_domainName").val("");
		$("#"+tableId+"_domainName").hide();
	}
}

/* 代码生成 */
function genCode(){
	var tables = $("input[type=checkbox][name='tableName']:checked").length;
	var genTypes = $("input[type=checkbox][name='genType']:checked").length;
	if(tables==null || tables ==0){
		layer.msg('请选择要生成代码对应的表',function(){});
		return ;
	}else if(genTypes==null || genTypes ==0){
		layer.msg('请选择要生成内容',function(){});
		return ;
	}else{
		var idx = layer.confirm('生成代码将覆盖现有位置的代码，确认要生成吗？',function(index){
			layer.close(idx);
			var load = layer.load(0,{shade:0.3});
			$.ajax({
				type: 'POST',
				url: 'gen/codeCreated.do',
				data:$("#gencode_gen_form").serialize(),
				success: function(data){
					layer.closeAll('loading');
					if(data == "success"){
						layer.msg('代码生成成功，请刷新工程');
					}else{
						layer.alert(data.message, {icon: 6});
					}
				},
				error:function(data) {
					console.log(data);
					layer.closeAll('loading');
				},
			});	
		}); 
	}
}
</script>
