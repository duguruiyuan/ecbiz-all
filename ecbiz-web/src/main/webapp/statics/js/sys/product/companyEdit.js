/**
 * 添加商品分类js
 */
var brandEdit={};
//保存分类
brandEdit.save=function(url){
	var type_id=$("#type_id").val();
	var cat_name=$("#cat_name").val();
	var istop  = $('input[name=istop]:checked').val();
	var isShow  = $('input[name=isshow]:checked').val();
	var parent_id=$("#parent_id").val();
	var sort_order=$("#sort_order").val();
	var id=$("#id").val();alert(id);
	var params = {
			typeId:type_id,
			catName:cat_name,
			parentId:parent_id,
			isShow:isShow,
			sortOrder:sort_order,
			id:id
		};
		$.ajax({
			url : "/productcategory/editsave",
			dataType : 'json',
			type : 'post',
			scriptCharset : 'utf-8',
			data : params,
			success : function(data) {
				if (data != '-1') {
					alert("修改成功！");
					location.href=url;
				} else {
					alert("修改失败！");
					location.href=url;
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
brandEdit.MM_popupMsg=function(msg,url){ 
	var bool=confirm(msg);
	if(bool){
		brandEdit.save(url);
	}
}	