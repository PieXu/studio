<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!--幻灯片-->
<div class="banner banner_s" >
	<c:choose>
		<c:when test="${!empty contactPic and !empty contactPic.id }">
   			 <img src="file/openFile.do?id=${contactPic.id}&type=image" height="300px"/>
		</c:when>
		<c:otherwise>
		 <img src="www/upload/contact.jpg" height="300px"/>
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
            <a href="">联系我们</a>
        </div>
        <div class="space_hx">&nbsp;</div>
        <div class="s_name"><a href="">联系我们</a></div>
        <div class="contact">
            <div class="title">联系方式</div>
            <div class="des">
            	<p>
                	<span>王先生 15313022999</span>
                    <span>王先生 13426389330</span>
                    <span>固   话 010-87678998</span>
                </p>
                <p>
                	<span>传真：010-87781259</span>
                    <span>地址：北京市朝阳区广渠东路5号A座5单元</span>
                </p>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="map"><img src="www/upload/address.png" width="720" height="500" alt=""/></div>
        </div>
    </div>
</div>
<!--主体盒子-->