/**
 * 添加商品品牌js
 */

var companyAdd = {};

// 保存品牌
companyAdd.save = function(url) {
	var name = $("#name").val();
	var brand = $('#brand').val();
	var english = $('#english').val();
	var logo = $('#logo').val();
	var url = $("#url").val();
	var tel = $("#tel").val();
	var isShow  = $('input[name=isshow]:checked').val();
	var description = $("#description").val();
	var params = {
			name : name,
			brand : brand,
			english : english,
			logo : logo,
			url : url,
			tel : tel,
			isshow : isShow,
			description : description
	};
	$.ajax({
		url : "/productcompany/addsave",
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
companyAdd.MM_popupMsg = function(msg, url) {
	var bool = confirm(msg);
	if (bool) {
		companyAdd.save(url);
	}
}

// 页面初始化执行
$(document).ready(function() {
	$("#imgtr").hide();
	// 编辑器
	var editor = new UE.ui.Editor();
	editor.render("editor");
	 
	
});

//上传图片
function uploadImg(){
	$.ajaxFileUpload({
		url:'http://ecbiz.chexun.com/common/uploadImage?picType=4',
		secureuri:false,
		fileElementId: 'imageUrl',
		dataType: 'text',
		data:{},
		success: function (data){
			if(data == null){
				alert("图片上传失败，请重新上传") ;
			}
		},
		error: function (data, status, e){
			alert(e);
		}
	})
}
function notice_id(path,photoId)
{	
	$('#imgshow_2').attr("src",path);
	$("#logoImage").val(photoId);
	$("#imgtr").show();
} 
function file(){
	$("#imageUrl").click();
	
}