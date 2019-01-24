<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
<head>
 <base href="${rc.contextPath}/">
 <tiles:insertAttribute name="meta"/>
 <title>系统后台管理</title>
</head>
<body>
 <tiles:insertAttribute name="header" />
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> <a href="security/changeRootMenu.do">首页</a>
		<span class="c-gray en" id="bread_path">${_navstation_path}</span>
		<a class="btn btn-success radius r" id="btn-refresh" style="line-height:1.6em;margin-top:3px" onclick="javascript:location.replace(location.href);" href="javascript:void(0);"   title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
	</nav>
	<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />
</body>
</html>