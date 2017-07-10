/**
 * 添加商品分类js
 */
var categoryEdit={};
//保存分类
categoryEdit.save=function(url){
	var type_id=$("#type_id").val();
	var cat_name=$("#cat_name").val();
	var istop  = $('input[name=istop]:checked').val();
	var isShow  = $('input[name=isshow]:checked').val();
	var parent_id=$("#parent_id").val();
	var sort_order=$("#sort_order").val();
	var id=$("#id").val();
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
var output='<option value="0">请选择所属上级分类</option>';
var catId="${category.catId}";
//商品分类树 下拉列表使用
$(document).ready(function() {
	//初始化
	var istop  = $('input[name=istop]:checked').val();
	if(istop=="1"){
		$("#parent_id").empty();
		$("#parent_id").append(output);
	}else{
		//自动触发点击不是顶级
		$("#notop").click(categoryEdit.clickNotop());
	}
	//点击是顶级
	$("#istop").click(function(){
		$("#parent_id").empty();
		$("#parent_id").append('<option value="0">请选择所属上级分类</option>');
	});
	//点击不是顶级
	$("#notop").click(function(){categoryEdit.clickNotop();});
});
//点击不是顶级单选框业务处理
categoryEdit.clickNotop=function(){
	var params = {parentId:0};
	$("#parent_id").empty();
	$.ajax({
		url : "/productcategory/catlist",
		dataType : 'json',
		type : 'post',
		scriptCharset : 'utf-8',
		data : params,
		success : function(data) {
			categoryEdit.parsejson(data,0);			
			$("#parent_id").append(output);output='<option value="0">请选择所属上级分类</option>';
		}
	});
}
//拼接option
categoryEdit.parsejson=function(json,count)
{
	var spaceStr="";
	for (var i=1;i<count;i++) {spaceStr+="&nbsp;";}
	for(var i=0;i<json.length;i++)
	{	var parentId=$("#parentId").val();
		if(json[i].catName!=undefined&&parentId==json[i].id){
			output+='<option value="'+json[i].id+'" selected>'+spaceStr+''+json[i].catName+'</option>';
		}if(json[i].catName!=undefined&&parentId!=json[i].id){
			output+='<option value="'+json[i].id+'" >'+spaceStr+''+json[i].catName+'</option>';
		}
		if(json[i].childCategoryList!=undefined){
			arguments.callee(json[i].childCategoryList,count+4);
		}
	}
}
//保存时使用
categoryEdit.MM_popupMsg=function(msg,url){ 
	var bool=confirm(msg);
	if(bool){
		categoryEdit.save(url);
	}
}	