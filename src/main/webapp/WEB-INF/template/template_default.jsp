<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
<head>
 <base href="${rc.contextPath}/">
 <tiles:insertAttribute name="meta" />
 <title>后台管理系统</title>
</head>
<body>
<!--_header 作为公共模版分离出去-->
<tiles:insertAttribute name="header" />
<!--/_header 作为公共模版分离出去-->
<!--_menu 作为公共模版分离出去-->
 <tiles:insertAttribute name="menu" />
<!--/_body 作为公共模版分离出去-->
<section class="Hui-article-box">
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="security/changeRootMenu.do">首页</a>
		<span class="c-gray en" id="bread_path">${_navstation_path}</span>
		<a class="btn btn-success radius r" id="btn-refresh" style="line-height:1.6em;margin-top:3px" onclick="javascript:commonQuery();" href="javascript:void(0);"  title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<form action="${_redirect_link}" method="post" id="list_query_form">
		<div class="Hui-article" id="_main_body"></div>
	</form>
</section>
<!--_footer 作为公共模版分离出去-->
<tiles:insertAttribute name="footer" />
<script type="text/javascript">
/**
 * 通用查询
 * @param formId
 * @returns
 */
function commonQuery(resetFlag)
{
	if(resetFlag){//页码是否需要重置
		$("#_pageNum_").val('');//重置是将分页重置
	}
	var load = layer.load(3,{shade:0.1});
	$.ajax({
	   type: "POST",
	   url: "${_redirect_link}",
	   data: $("#list_query_form").serialize() ,
	   success: function(data){
		   setTimeout(function(){
			   $("#_main_body").html(data);
			   layer.closeAll('loading');
			 }, 300);
	   },
	   error:function(){
		   layer.msg("操作失败,请稍后再试...");
		   layer.closeAll('loading');  
	   }
	});
}
//页面完成后，加载右侧列表的数据信息
$(document).ready(function(){
	commonQuery();
});
</script>
</body>
</html>