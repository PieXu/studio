<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<div class="cl pd-20">
		<p class="f-20 text-success">欢迎使用业务管理平台
			<span class="f-14">Beta v1.0</span>
		</p>
		<div id="container" style="width:100%;height:80%"></div>
	</div>
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript">
var chart = Highcharts.chart('container', {
    chart: {
        type: 'column'
    },
    title: {
        text: '全球各大分销机构销售额排行'
    },
    subtitle: {
        text: '数据截止 2018-09'
    },
    xAxis: {
        type: 'category',
        labels: {
            rotation: -45  // 设置轴标签旋转角度
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: '销售额 (百万)'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        pointFormat: '总销售额: <b>{point.y:.1f} 百万</b>'
    },
    series: [{
        name: '总销售额',
        data: ${data},
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            format: '{point.y:.1f}', // :.1f 为保留 1 位小数
            y: 10
        }
    }]
});
</script>