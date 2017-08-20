/**
 * 添加商品品牌js
 */

var productAdd = {};

// 保存品牌
productAdd.save = function(url) {
	var catId = $("#catId").val();
	var productName = $("#product_name").val();
	var productSn = $("#product_sn").val();
	var unit = $('#unit').val();
	var weight = $('#weight').val();
	var productNum = $("#product_num").val();
	var shopPrice = $("#shop_price").val();
	var marketPrice = $("#market_Price").val();
	var isShow  = $('input[name=isshow]:checked').val();
	var isOnSale  = $('input[name=isonSale]:checked').val();
	var productDesc = $("#product_desc").val();
	var content =UE.getEditor('editor').getContent();alert(isOnSale);
	var params = {
			catId:catId,
			productName : productName,
			productSn : productSn,
			unit : unit,
			weight : weight,
			productNum : productNum,
			shopPrice : shopPrice,
			isShow:isShow,
			isOnSale:isOnSale,
			marketPrice : marketPrice,
			productDesc : productDesc,
			content : content
	};
	$.ajax({
		url : "/productinfo/addsave",
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