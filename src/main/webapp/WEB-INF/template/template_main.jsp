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
	<br/>
	<tiles:insertAttribute name="body" />
<tiles:insertAttribute name="footer" />
</body>
</html>