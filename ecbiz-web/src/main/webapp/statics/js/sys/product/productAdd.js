/**
 * 添加商品品牌js
 */

var productAdd = {};

// 保存品牌
productAdd.save = function(url) {
	var productName = $("#product_name").val();
	var englishName = $("#english_name").val();
	var english = $('#english').val();
	var productLogo = $('#logoImage').val();
	var officialUrl = $("#official_url").val();
	var telephone = $("#telephone").val();
	var isShow  = $('input[name=isshow]:checked').val();
	var productDesc = $("#product_desc").val();
	var content =UE.getEditor('editor').getContent();
	var params = {
			productName : productName,
			englishName : englishName,
			english : english,
			productLogo : productLogo,
			officialUrl : officialUrl,
			telephone : telephone,
			isShow : isShow,
			productDesc : productDesc,
			content : content
	};
	$.ajax({
		url : "/productproduct/addsave",
		dataType : 'json',
		type : 'post',
		scriptCharset : 'utf-8',
		data : params,
		success : function(data) {
			if (data != '-1') {
				alert("保存成功！");
				location.href = url;
			} else {
				alert("保存失败！");
				location.href = url;
			}
		},
	});
}
// 保存时使用
productAdd.MM_popupMsg = function(msg, url) {
	var bool = confirm(msg);
	if (bool) {
		productAdd.save(url);
	}
}

// 页面初始化执行
$(document).ready(function() {
	$("#imgtr").hide();
	// 编辑器
	var editor = new UE.ui.Editor();
	editor.render("editor");
	 
	
});