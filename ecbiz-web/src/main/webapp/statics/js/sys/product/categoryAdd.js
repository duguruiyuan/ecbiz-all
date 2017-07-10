/**
 * 添加商品分类js
 */
var category={};
//保存分类
category.save=function(url){
	var type_id=$("#type_id").val();
	var cat_name=$("#cat_name").val();
	var istop  = $('input[name=istop]:checked').val();
	var isShow  = $('input[name=isshow]:checked').val();
	var parent_id=$("#parent_id").val();
	var sort_order=$("#sort_order").val();
	var params = {
			typeId:type_id,
			catName:cat_name,
			parentId:parent_id,
			isShow:isShow,
			sortOrder:sort_order
		};
		$.ajax({
			url : "/productcategory/addsave",
			dataType : 'json',
			type : 'post',
			scriptCharset : 'utf-8',
			data : params,
			success : function(data) {
				if (data != '-1') {
					alert("保存成功！");
					location.href=url;
				} else {
					alert("保存失败！");
					location.href=url;
				}
			},
		});
}
//保存时使用
category.MM_popupMsg=function(msg,url){ 
	  var bool=confirm(msg);
	  if(bool){
		  category.save(url);
	  }
}
var output='<option value="0">请选择所属上级分类</option>';
var catId="${category.catId}";
//商品分类树 下拉列表使用
$(document).ready(function() {
	//初始化
	var istop  = $('input[name=istop]:checked').val();
	if(istop=="1"){
		$("#parent_id").empty();
		$("#parent_id").append(output);
	}
	//点击是顶级
	$("#istop").click(function(){
		$("#parent_id").empty();
		$("#parent_id").append('<option value="0">请选择所属上级分类</option>');
	 });
	//点击不是顶级
	$("#notop").click(function(){
			var params = {parentId:0};
			$("#parent_id").empty();
			$.ajax({
				url : "/productcategory/catlist",
				dataType : 'json',
				type : 'post',
				scriptCharset : 'utf-8',
				data : params,
				success : function(data) {
					category.parsejson(data,0);			
					$("#parent_id").append(output);output='<option value="0">请选择所属上级分类</option>';
				}
			});
	 });
});
//拼接option
category.parsejson=function(json,count)
{
	var spaceStr="";
	for (var i=1;i<count;i++) {spaceStr+="&nbsp;";}
	for(var i=0;i<json.length;i++)
	{		
		if(json[i].catName!=undefined){
			output+='<option value="'+json[i].id+'">'+spaceStr+''+json[i].catName+'</option>';
		}
		if(json[i].childCategoryList!=undefined){
			arguments.callee(json[i].childCategoryList,count+4);
		}
	}

}
	