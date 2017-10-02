/**
 * 添加用户js
 */

var sysUserEdit = {};

// 保存用户
sysUserEdit.save = function(url) {
	var uname = $("#uname").val();
	var password1 = $("#password1").val();
	var password2 = $("#password2").val();
	var password;
	var name = $("#name").val();
	var organization = $('#organization').val();
	var rId = $('#r_id').val();
	var operator = $("#operator").val();
	var tel = $("#tel").val();
	var status = $("#status").val();	
	var note = $("#note").val();
	var uid=$("#uid").val();
	if(password1!=password2){
		alert("两个密码不一致!");
	}else{
		password=password1;
	}
	
	var params = {
			uname:uname,
			password:password,
			name:name,
			organization:organization,
			rId:rId,
			operator:operator,
			tel:tel,
			status:status,
			note:note,
			uid:uid
	};
	$.ajax({
		url : "/sysuser/editsave",
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
// 保存时使用
sysUserEdit.MM_popupMsg = function(msg, url) {
	var bool = confirm(msg);
	if (bool) {
		sysUserEdit.save(url);
	}
}
sysUserEdit.opcal= function(){
    $.calendar.Show();
}
// 页面初始化执行
$(document).ready(function() {
	$("#imgtr").hide();
	// 编辑器
	var editor = new UE.ui.Editor();
	editor.render("editor");
	$('#pay_time').calendar();
	 
	
});