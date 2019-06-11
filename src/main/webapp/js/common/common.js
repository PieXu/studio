 /**
  *  通用打开窗口 类型2
  */
function _common_open_win(width,height,url)
{
	if(url){
		var pageId = $("#_pageId").val();
		if(url.indexOf("?") > 0){
			url += "&_pageId="+pageId;
		}else{
			url += "?_pageId="+pageId;
		}
		var index = layer.open({
			type: 2,
			title:"编辑页面窗口",
			area: [width+"px", height+"px"],
			content: url,
			btn: ['保存', '关闭']
			,yes: function(){
		    	$("#layui-layer-iframe"+index).contents().find("#sub_btn").click();
		  	}
		});
	}else{
		layer.msg('当前操作未配置链接，请联系管理员设置!', function(){});
	}
}

/**
 * 操作的异步请求
 * @param url
 * @returns
 */
function _deals_Ajax(url,name)
{
	if(url){
		var tabelName = $("#_tabelName").val();
		if(url.indexOf("?") > 0){
			url += "&_tableName="+tabelName;
		}else{
			url += "?_tableName="+tabelName;
		}
		layer.confirm("["+name+"]操作可能会影响系统正常运行，确定执行吗？", {
		   btn: ['确定','取消'] //按钮
		}, function(){
			$.ajax({
			   type: "POST",
			   url: url,
			   success: function(data){
				   if( data.result == "success"){
					   layer.msg(data.message,{icon:1,time:1000});
					   commonQuery(true);;
				   }else{
					   layer.msg(data.message,{icon:2,time:1000});
				   }
			   }
			});	
		}, function(){
		});
	}else{
		layer.msg('当前操作未配置链接，请联系管理员设置!', function(){});
	}
}

/**
 * 全选
 * @param selector
 * @param checkeName
 * @returns
 */
function _checkAll(obj,checkeName)
{
	var chkflag = $(obj).prop('checked');
    $("input[type=checkbox][name='"+checkeName+"']").prop("checked",chkflag);
}

/**
 * 清空表单
 * @param formId
 * @returns
 */
function resetSerachForm(formId)
{
	if(formId){
		 $(':input','#'+formId).not(':button,:submit,:reset,:hidden') //将myform表单中input元素type为button、submit、reset、hidden排除
	       .val('')  //将input元素的value设为空值
	       .removeAttr('checked');
		 commonQuery(true);//重置查询一下， 如不需要查询，只需要清空，则注释掉即可
	}else{ // 未设置formid的取默认的form清空
		$(':input','#list_query_form').not(':button,:submit,:reset,:hidden')   //将myform表单中input元素type为button、submit、reset、hidden排除
       .val('')  //将input元素的value设为空值
       .removeAttr('checked');
		commonQuery(true);//重置查询一下， 如不需要查询，只需要清空，则注释掉即可
	}
}