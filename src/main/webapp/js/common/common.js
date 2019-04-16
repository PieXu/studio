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