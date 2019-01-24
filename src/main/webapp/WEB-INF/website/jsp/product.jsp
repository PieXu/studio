<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--主体盒子-->
<div class="scd_bg">
	<div class="scd">
    	<div class="pst">
        	您现在的位置：
            <a href="index">首页</a>>
            <a href="">产品中心</a>
        </div>
        <div class="space_hx">&nbsp;</div>
        <div class="s_name p_name">
        	<a href="productlist__1.do"  <c:if test="${empty category }"> class="on" </c:if>>全部</a>
        	<c:forEach items="${dicList }" var="dic">
        		<a href="productlist_${dic.id}_1.do" <c:if test="${category eq dic.id }">class="on"</c:if>>${dic.name }</a>
        	</c:forEach>
       	</div>
        <div class="equipment" style="min-height: 480px">
            <ul class="clearfix">
            	<c:if test="${!empty page }">
	            	<c:forEach items="${ page}"  var="p"  varStatus="status"> 
		            	<li>
		                	<a href="product_${p.id}.do">
		                    	<img src="file/openFile.do?id=${p.coverImage}&type=image" alt="${p.productName }"/>
		                        <div class="ctn">
		                        	<div class="name">${p.productName }</div>
		                            <div class="des">
		                            	品牌：${p.brand }
                            		</div>
		                        </div>
		                    </a>
		                </li>
	                </c:forEach>
                </c:if>
                <c:if test="${empty page }">当前分类暂无产品</c:if>
            </ul>
            <div class="space_hx">&nbsp;</div>
            <div class="pages">
            	<c:forEach begin="1" end="${pages }" var="idx">
            		<a href="productlist_${category}_${idx}.do">第${idx }页</a>
            	</c:forEach>
            </div>
        </div>
    </div>
</div>
<!--主体盒子-->