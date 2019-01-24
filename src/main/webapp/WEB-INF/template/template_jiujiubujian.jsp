<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML>
<html>
<head>
	<base href="${rc.contextPath}/">
	<title>客如云纸类产品官方微平台</title>
	<link rel="stylesheet" type="text/css" href="static/jiujiubujian/jstyle0416.css">
	<script type="text/javascript" src="static/jiujiubujian/jquery1.4.2.min.js"/>
	<script type="text/javascript" src="static/jiujiubujian/jquery.SuperSlide.2.1.1.js"/>
	<script type="text/javascript">
		$(function() {
			$(".nav li").hover(function() {
				$(this).addClass("nav_li");
				$(this).siblings().removeClass("nav_li");
			});
		});
		$(function() {
			$(".jfbllc li a").hover(function() {
				$(this).parent().addClass("bg_li");
				$(this).parent().siblings().removeClass("bg_li");
	
			});
		});
	</script>
<style>
.big_box {
	width: 1135px;
	margin: 0 auto;
	margin-top: 15px;
}

.jfbllc {
	z-index: 10;
}

.jfbllc li a {
	display: block;
	width: 118px;
	height: 130px;
	text-indent: -9999px;
	overflow: hidden;
	background: url("images/jfpic2.png")
		/*tpa=http://www.jiujiubujian.com/jflh/images/jfpic2.png*/
		/*tpa=http://www.jiujiubujian.com/jflh/images/jfpic2.png*/;
}

.big_box img {
	position: absolute;
	left: 60px;
	top: 0;
	z-index: 0
}

.section h1 {
	font-size: 20px;
}

.login h3 {
	height: 80px;
	line-height: 80px;
}

.login li {
	margin-top: 18px;
	height: 85px;
	padding-top: 18px;
}
</style>
<body>
	<!--_header 作为公共模版分离出去-->
	<tiles:insertAttribute name="header" />
	<!--/_header 作为公共模版分离出去-->

	<tiles:insertAttribute name="body" />

	<!--_footer 作为公共模版分离出去-->
	<tiles:insertAttribute name="footer" />
</body>
</html>