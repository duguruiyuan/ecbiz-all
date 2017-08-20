/**
 * 添加商品品牌js
 */

var tradeAdd = {};

// 保存品牌
tradeAdd.save = function(url) {
	var productSn = $("#product_sn").val();
	var title = $("#title").val();
	var price = $("#price").val();
	var num = $('#num').val();
	var payment = $('#payment').val();
	var buyer_nick = $("#buyer_nick").val();
	var receiver_name = $("#receiver_name").val();
	var receiver_state = $("#receiver_state").val();	
	var receiver_address = $("#receiver_address").val();
	var receiver_zip = $("#receiver_zip").val();
	var receiver_mobile = $("#receiver_mobile").val();
	var post_fee = $("#post_fee").val();
	var pay_type = $("#pay_type").val();
	var pay_time = $("#pay_time").val();
	var params = {
			productSn:productSn,
			title:title,
			price:price,
			num:num,
			payment:payment,
			buyerNick:buyer_nick,
			receiverName:receiver_name,
			receiverState:receiver_state,
			receiverAddress:receiver_address,
			receiverZip:receiver_zip,
			receiverMobile:receiver_mobile,
			postFee:post_fee,
			payType:pay_type,
			payTimes:pay_time
	};
	$.ajax({
		url : "/trade/addsave",
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
tradeAdd.MM_popupMsg = function(msg, url) {
	var bool = confirm(msg);
	if (bool) {
		tradeAdd.save(url);
	}
}
tradeAdd.opcal= function(){
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