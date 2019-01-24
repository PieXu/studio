<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
<button class="btn btn-primary radius" type="button" id="redis_lock">Redis分布式锁的调试</button>

<script>
//锁调试按钮
$("#redis_lock").click(function(){
	$.ajax({
		type: 'POST',
		url: 'sample/lock.do',
		success: function(data){
			console.log(data);
			layer.msg(data);
		},
		error:function(data) {
			console.log(data);
		},
	});		
});
</script>	
