<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<article class="cl pd-20">
		<p class="f-20 text-success">管理平台业务数据分析
			<span class="f-14">Beta v1.0</span>
		</p>
		<p>
			<c:if test="${!empty loginUser && !empty loginUser.lastLoginIp }">登录IP：<b>${loginUser.lastLoginIp }</b>  </c:if>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<c:if test="${!empty loginUser && !empty loginUser.lastLoginTime }">登录时间：<b><fmt:formatDate value="${loginUser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss" /></b></c:if>
		</p>
		<div id="container" style="width:100%;height:80%"></div>
	</article>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript">
$(function () {
    $('#container').highcharts({
        title: {
            text: '平台登录访问统计',
            x: -20 //center
        },
        subtitle: {
            text: '非实时统计',
            x: -20
        },
        xAxis: {
            categories: ${categories}
        },
        yAxis: {
            title: {
                text: '访问量'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '次'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: ${series}
    });
});
</script>