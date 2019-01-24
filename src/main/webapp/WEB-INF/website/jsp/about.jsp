<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!--幻灯片-->
<div class="banner banner_s" style="height:300px">
	<c:choose>
		<c:when test="${!empty aboutPic}">
   			 <img src="file/openFile.do?id=${aboutPic.id}&type=image" height="300px"/>
		</c:when>
		<c:otherwise>
		 <img src="www/upload/about.jpg"  height="300px"/>
		</c:otherwise>
	</c:choose>
</div>
<!--幻灯片-->
<!--主体盒子-->
<div class="scd_bg">
	<div class="scd">
    	<div class="pst">
        	您现在的位置：
            <a href="">首页</a>>
            <a href="">关于我们</a>
        </div>
        <div class="space_hx">&nbsp;</div>
        <div class="s_name"><a href="">关于我们</a></div>
        <div class="about">
            <dl class="clearfix">
            	<dt><img src="www/upload/company.jpg" alt=""/></dt>
                <dd>本公司成立于2011年，
                	是一家网上销售机械设备、电气设备、文具用品、办公用品、劳保用品、日用品、
                	体育用品、机械设备、电子产品、针纺织品、仪器仪表、通信设备、计算机软硬件及辅助设备；
                	软件开发；技术推广、技术服务、软件开发、技术咨询；组织文化艺术交流活动；经济贸易咨询。（
                	依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事本市产业政策禁止和限制类项目的经营活动。）
				</dd>
            </dl>
        </div>
    </div>
</div>
<!--主体盒子-->