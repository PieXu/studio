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
function clearSerachForm(formId)
{
	if(formId){
		 $(':input','#'+formId).not(':button,:submit,:reset,:hidden')   //将myform表单中input元素type为button、submit、reset、hidden排除
	       .val('')  //将input元素的value设为空值
	       .removeAttr('checked');
	}
}