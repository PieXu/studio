<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML>
<html>
<head>
 <base href="${rc.contextPath}/">
 <script type="text/javascript" src="${rc.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
 <tiles:insertAttribute name="meta" />
 <title>管理平台</title>
</head>
<body>
<tiles:insertAttribute name="body" />
<!--_footer 作为公共模版分离出去-->
<tiles:insertAttribute name="footer" />
</body>
</html>