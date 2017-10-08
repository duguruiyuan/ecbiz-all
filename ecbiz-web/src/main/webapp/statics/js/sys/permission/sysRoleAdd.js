/**
 * 添加商品品牌js
 */

var sysRoleAdd = {};

// 保存品牌
sysRoleAdd.save = function(url) {
	var rName = $("#r_name").val();
	var describe = $("#describe").val();
	var note = $("#note").val();
	
	var params = {
			rName:rName,
			describe:describe,
			note:note
	};
	$.ajax({
		url : "/sysrole/addsave",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data : params,
		success : function(data) {
			if (data != '-1') {
				
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
sysRoleAdd.MM_popupMsg = function(msg, url) {
	var bool = confirm(msg);
	if (bool) {
		sysRoleAdd.save(url);
	}
}
sysRoleAdd.opcal= function(){
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