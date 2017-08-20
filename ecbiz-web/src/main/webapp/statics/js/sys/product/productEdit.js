/**
 * 添加商品js
 */
var productEdit={};
//保存商品
productEdit.save=function(url){
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
	var id=$("#id").val();
	var params = {
			catId:catId,
			productName : productName,
			productSn : productSn,
			unit : unit,
			weight : weight,
			productNum : productNum,
			shopPrice : shopPrice,
			isShow:isShow,
			isOnsale:isOnSale,
			marketPrice : marketPrice,
			productDesc : productDesc,
			content :content,
			id:id
	};
	$.ajax({
		url : "/productinfo/editsave",
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

//页面初始化执行
$(document).ready(function() {
	// 编辑器
	var editor = new UE.ui.Editor();
	editor.render("editor");
	
});

//保存时使用
productEdit.MM_popupMsg=function(msg,url){ 
	var bool=confirm(msg);
	if(bool){
		productEdit.save(url);
	}
}	