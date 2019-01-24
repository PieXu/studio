<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside class="Hui-aside">
	<div class="menu_dropdown bk_2">
		<c:if test="${!empty linkList }">
			<c:forEach items="${linkList }" var="res">
				<dl id="menu-article" class="_menu">
					<dt><i class="Hui-iconfont ${res.classStyle }" style="font-size:18px">&nbsp;</i><span id="span_${res.id }">${res.resName }</span><i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
					<c:if test="${!empty res.childRes }">
						<dd>
							<ul>
								<c:forEach items="${res.childRes }" var="child">
									<li><a href="security/loadMenu.do?resId=${child.id }" title="${child.resName }" id="${child.id}">${child.resName }</a></li>
								</c:forEach>
							</ul>
						</dd>
					</c:if>
				</dl>
			</c:forEach>
		</c:if>
	</div>
</aside>
<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
