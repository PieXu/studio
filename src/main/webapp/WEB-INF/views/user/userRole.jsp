<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="page" uri="http://com.innovate.page.tld" %>
<article class="cl pd-20">
	<form action="user/saveUserRole.do" method="post" class="form form-horizontal" id="form-user-role">
		<input type="hidden" name="userId" value="${userId}"/>
		<input type="submit" id="sub_btn" style="display:none">
		
		<div class="row cl">
			<div class="formControls col-xs-12 col-sm-11">
				<dl class="permission-list">
					<dt>
						<label>
							<input type="checkbox" value="" name="user-Character-0" id="user-Character-0">&nbsp;&nbsp;全选
						</label>
					</dt>
					<dd>
						<dl class="cl permission-list2" style="border:0">
							<c:if test="${!empty roleList }">
								<c:forEach items="${roleList }" var="role" varStatus="status">
									<dt>
										<label class="">
											<input type="checkbox" <page:checkContains list='${userRoleList }' value='${role.id }'/>
											 value="${role.id }" name="roleId" id="${role.id }">${role.name }
										</label>
									</dt>
								</c:forEach>
							</c:if>
						</dl>
					</dd>
				</dl>
			</div>
		</div>
	</form>
</article>
<script type="text/javascript">
$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	$("#form-user-role").validate({
		rules:{
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			$.ajax({
			   type: "POST",
			   url: "user/saveUserRole.do",
			   data: $(form).serialize() ,
			   success: function(data){
				   //data = $.parseJSON( data );
				   if( data.result == "success"){
					    parent.layer.msg(data.message,{icon:1,time:2000});
					    var index = parent.layer.getFrameIndex(window.name);
						parent.layer.close(index);
						
				   }else{
					   layer.msg(data.message,{icon:2,time:1000});
				   }
			   }
			});
		}
	});
});
</script>
<!--/请在上方写此页面业务相关的脚本-->