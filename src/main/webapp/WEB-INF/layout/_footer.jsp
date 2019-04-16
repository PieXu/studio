<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="css/layout/h-ui/js/H-ui.js"></script>
<script type="text/javascript" src="css/layout/h-ui.admin/js/H-ui.admin.page.js"></script> 
<!-- 通用js -->
<script type="text/javascript" src="js/common/common.js"></script> 
<script type="text/javascript">
/**
 * 菜单选中的判断逻辑
 */
$(function(){
	var navstation = "${_currentMenu}";
	if(navstation != null){
	    $("._menu dd ul li a").each(function(){
	        if($(this).attr("id") == navstation){
	            $(this).parent().parent().parent().css("display","block");
	            $(this).parent().parent().prev().addClass("selected");
	            $(this).parent().addClass("current");
	        }
	    });
	}
});

var stompClient = null;
$(document).ready(function(){
	var socket = new SockJS("<c:url value='/webServer' />");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function(frame) {
		// 提出用户的例子
	    stompClient.subscribe('/user/${loginUser.loginName}/message',function(message){  
	    	var data = JSON.parse(message.body);
            layer.alert(data.content, {
           	  closeBtn: 0
           	}, function(){
           		$("#_form-logout").submit();
           	});
        });
	    
	    //广播的例子
	    stompClient.subscribe('/topic/greetings',function(message){  
	    	var data = JSON.parse(message.body);
	    	layer.open({
	    		  title: data.title,
	    		  offset: 'rb',
	    		  time: 5000,
	    		  btn:[],
	    		  closeBtn: 0,
	    		  shade :0,
	    		  anim: 2,
	    		  content: data.content
    		});   
        });
	    
	});
}); 

</script>
