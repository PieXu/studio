<%@page contentType="text/html;charset=utf-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="www/plugins/annimation/js/move.js"></script>
<link rel="stylesheet" type="text/css" href="www/plugins/annimation/css/zzsc.css">
<!--幻灯片-->
<div class="banner banner_s" ><img src="www/upload/product.jpg" alt=""/></div>
<!--幻灯片-->
<!--主体盒子-->
<div class="scd_bg">
	<div class="scd">
    	<div class="pst">
        	您现在的位置：
            <a href="">首页</a>>
            <a href="">产品案例</a>>
            <a href="">${breaing.productName }</a>
        </div>
        <div class="space_hx">&nbsp;</div>
        <div class="pro_d">
        	<div class="pro_da clearfix">
        	 <div class="pro_dal">
        	 <div id="playimages" class="play">
				<ul class="big_pic">
					<div class="prev"></div>
				    <div class="next"></div>
				    <a class="mark_left" href="javascript:;"></a>
				    <a class="mark_right" href="javascript:;"></a>
				    <!-- <div class="bg"></div> --> 
				    <c:if test="${!empty fileList }" >
                   		<c:forEach items="${fileList }" var="file"  varStatus="status">
                   			 <li <c:if test="${status.index == 0 }">style="z-index:1;"</c:if>><img src="http://localhost:8089/${file.id}.${file.ext }" style="width:100%;height: 100%"/></li> 
                   		</c:forEach>
                   	</c:if>
				    </ul>
				    <div id="small_pic" class="small_pic">
				    	<ul style="width:400px;">
				    		<c:if test="${!empty fileList }" >
                        		<c:forEach items="${fileList }" var="file" varStatus="status">
                        			<li <c:if test="${status.index == 0 }">style=" filter: alpha(opacity:100); opacity:1;"</c:if>><img src="http://localhost:8089/${file.id}.${file.ext }"/></li>
                           		</c:forEach>
                         	</c:if>
				        </ul>       
				    </div>
				</div>
			</div>
               <div class="pro_dar">
                    <h1>${breaing.productName }</h1>
                    <div class="title">产品特点：</div>
                    <div class="des">
                    	<p>型号：${breaing.type }</p>
                        <p><b>基本参数</b></p>
                        <p>品牌：${breaing.brand }</p>
                        <p>内径：${breaing.inDiameter }</p>
                        <p>外径：${breaing.outDiameter }</p>
                        <p>厚度：${breaing.thickness }</p>
                    </div>
                    <div class="share">
                        <div class="bdsharebuttonbox"><a href="#" class="bds_more" data-cmd="more"></a><a href="#" class="bds_qzone" data-cmd="qzone" title="分享到QQ空间"></a><a href="#" class="bds_tsina" data-cmd="tsina" title="分享到新浪微博"></a><a href="#" class="bds_tqq" data-cmd="tqq" title="分享到腾讯微博"></a><a href="#" class="bds_renren" data-cmd="renren" title="分享到人人网"></a><a href="#" class="bds_weixin" data-cmd="weixin" title="分享到微信"></a></div>
						    <script>
						    window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];
						    </script>
                    </div>
                </div>
            </div>
            <div class="space_hx">&nbsp;</div>
            <div class="pro_dm">
                <div class="tabBox_t">
                    <div class="tabBox">
                      <ul class="tabNav clearfix">
                        <li class="a now"><span>商品介绍</span></li>
                        <li class="b"><span>规格参数</span></li>
                      </ul>
                      <div class="tabCont a_ctn" style="display:block;">
                     	${breaing.description}
                      </div>
                      <div class="tabCont b_ctn">
                      	${breaing.standard}
                      </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--主体盒子-->