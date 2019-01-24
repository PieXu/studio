<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<script language="javascript">
$(function() {
  $('.flexslider').flexslider({
	animation: "slide"
  });
});
</script>
<!--幻灯片-->
<div class="banner" >
	<div class="slider">
        <div class="flexslider">
          <ul class="slides">
       		<c:choose>
       			<c:when test="${!empty fileList }">
       				<c:forEach items="${fileList }" var="p">
	           			 <li style="height: 300px"><img src="file/openFile.do?id=${p.id}&type=image" alt="北京弗安吉轴承" height="300px"/></li>
	           		</c:forEach>
       			</c:when>
       			<c:otherwise>
       			 <li><a href=""><img src="www/upload/lunbo.jpg" alt=""/></a></li>
       			</c:otherwise>
       		</c:choose>
          </ul>
        </div>
    </div>
</div>
<!--幻灯片-->
<!--主体盒子-->
<div class="bg_a">
	<div class="i_head"><a href="">热销产品</a><em>&nbsp;</em></div>
	<div class="i_ma">
    	<div class="mainPhoto"> <span class="goleft nextPage"><a href="javascript:void(0)"><img src="www/images/prev.png" alt=""/></a></span>
          <div class="go slidegrid">
            <ul class="slideitems">
            <c:if test="${!empty bearingList }">
           		<c:forEach items="${bearingList }" var="p">
	              <li>
	                  <a href="product_${p.id}">
	                  	<div class="tu"><img src="file/openFile.do?id=${p.coverImage}&type=image" alt="${p.productName }"/></div>
	                    <div class="title">${p.productName }</div>
	                    <div class="des">${p.description }</div>
	                  </a>
	              </li>
	             </c:forEach>
             </c:if>
             <!--  <li>
                  <a href="#">
                  	<div class="tu"><img src="www/upload/example2.jpg" alt=""/></div>
                    <div class="title">吉他功放机</div>
                    <div class="des">吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机</div>
                  </a>
              </li>
              <li>
                  <a href="#">
                  	<div class="tu"><img src="www/upload/example.jpg" alt=""/></div>
                    <div class="title">吉他功放机</div>
                    <div class="des">吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机</div>
                  </a>
              </li>
              <li>
                  <a href="#">
                  	<div class="tu"><img src="www/upload/example.jpg" alt=""/></div>
                    <div class="title">吉他功放机</div>
                    <div class="des">吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机</div>
                  </a>
              </li>
              <li>
                  <a href="#">
                  	<div class="tu"><img src="www/upload/example.jpg" alt=""/></div>
                    <div class="title">吉他功放机</div>
                    <div class="des">吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机</div>
                  </a>
              </li>
              <li>
                  <a href="#">
                  	<div class="tu"><img src="www/upload/example.jpg" alt=""/></div>
                    <div class="title">吉他功放机</div>
                    <div class="des">吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机吉他功放机</div>
                  </a>
              </li> -->
            </ul>
          </div>
          <span class="goright prevPage"><a href="javascript:void(0)"><img src="www/images/next.png" alt="" /></a></span> </div>
<script>
$(function(){
$('.mainPhoto .slidegrid').scrollable({size:5,circular:true,next:'.nextPage',prev:'.prevPage'}).autoscroll();
});	
</script> 
    </div>
</div>