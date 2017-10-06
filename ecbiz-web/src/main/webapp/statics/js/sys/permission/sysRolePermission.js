/**
 *给角色授权 
 */
var rolePermission={};

rolePermission.authorize=function(roleId){
var chk_value="" ;
$('input[name="permissionIds"]:checked').each(function(){
	chk_value=chk_value+$(this).val()+"#";
}); 
//var operatorIDs=JSON.stringify(chk_value);//将数组转为JSON字符串
var params = {
		permissionIds:chk_value,
		roleId:roleId
};
$.ajax({
	url : "/sysrolepermission/addsave",
	dataType : 'json',
	type : 'post',
	scriptCharset :'utf-8',
	data : params,
	success : function(data) {
		if (data != '-1') {
			alert("保存成功！");
			location.href = url;
		} else {
			alert("保存失败！");
			location.href = url;
		}
	},error: function (data, status, e){
		alert(e);
	}
});
}