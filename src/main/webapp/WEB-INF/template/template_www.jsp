<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
<head>
	<base href="${rc.contextPath}/">
	<title>北京弗安吉动力装备有限公司</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="stylesheet" type="text/css" href="www/css/reset.css"/>
	<script type="text/javascript" src="www/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="www/plugins/FlexSlider/jquery.flexslider.js"></script>
	<link rel="stylesheet" type="text/css" href="www/plugins/FlexSlider/flexslider.css">
	<script type="text/javascript" src="www/js/js_z.js"></script>
	<link rel="stylesheet" type="text/css" href="www/css/thems.css">
	<LINK rel="Bookmark" href="favicon.ico" >
	<LINK rel="Shortcut Icon" href="favicon.ico" />
</head>
<body>
<!--_header 作为公共模版分离出去-->
<tiles:insertAttribute name="header" />
<!--/_header 作为公共模版分离出去-->

<tiles:insertAttribute name="body" />

<!--_footer 作为公共模版分离出去-->
<tiles:insertAttribute name="footer" />
</body>
</html>