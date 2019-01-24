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
						<div class="formControls col-xs-12 col-sm-11">
							<dl class="permission-list" style="border:0">
								<dd>
									<dl class="cl permission-list2" style="border:0">
										<c:if test="${!empty tableList }">
											<c:forEach items="${tableList }" var="table" varStatus="status">
												<dt style="width:180px">
													<label class="" title="备注描述  ${table.remarks }" data-toggle="tooltip" data-placement="right" >
														<input type="checkbox" value="${table.tableName }" name="tableName">&nbsp;${table.tableName }
													</label>
												</dt>
											</c:forEach>
										</c:if>
									</dl>
								</dd>
							</dl>
							<!-- 生成 -->
							<h4 style="color: gray;">生成的内容</h4>
							<dl class="permission-list" style="border:0">
								<dd>
									<dl class="cl permission-list2" style="border:0">
										<dt style="width:150px">
											<label class="">
												<input type="checkbox" value="_controller" name="genType">&nbsp;Controller层
											</label>
										</dt>
										<dt style="width:150px">
											<label class="">
												<input type="checkbox" value="_service" name="genType">&nbsp;Service层
											</label>
										</dt>
										<dt style="width:120px">
											<label class="">
												<input type="checkbox" value="_dao" name="genType">&nbsp;DAO层
											</label>
										</dt>
										<dt style="width:150px">
											<label class="">
												<input type="checkbox" value="_domain" name="genType">&nbsp;Model实体
											</label>
										</dt>
										<dt style="width:150px">
											<label class="">
												<input type="checkbox" value="_view" name="genType">&nbsp;View页面
											</label>
										</dt>
									</dl>
								</dd>
							</dl>
							
						</div>
					</div>
				</div>
			</form>
			<div class="mt-30">
				<page:showOpt code="add" title="代码生成" type="button" method="genCode();" styleClass="btn btn-success radius"/>
			</div>
		</article>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">
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
