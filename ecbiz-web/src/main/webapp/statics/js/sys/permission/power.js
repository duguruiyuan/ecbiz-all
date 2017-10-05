/**
 * 菜单管理js
 */
var powrer = {};
powrer.openDialogs = function(type, value) {
	var title='';
	var url = '';
	
	if(type == "delete"){
		powrer.toDelResource(value);
	}
	if(type == "add"){
		title="资源添加";
		powrer.AddWinOpen(value,title);
	}
	if(type == "edit"){
		title="资源修改";
		powrer.EditWinOpen(value,title);
	}if(type == "move"){
		title="资源移动";
	}
}

powrer.EditWinOpen=function(value,title){
	var val=value;
	powrer.getResourcebyId(val);
	$("#dialog").dialog({
		height:540,
		width:600,
		title:title,
		buttons : {
			"确定" : function() {
				powrer.toEditResource(val);
			},
			"取消" : function() {
				$(this).dialog('close');
				
			}
		}
	});
}
powrer.getResourcebyId=function(value){
	//禁用缓存
	$.ajaxSetup ({       cache: false    });
	var params = {id:value};
	$.ajax({
		url : "/power/edit",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			$("#name").val(data[0].name);
			$("#url").val(data[0].url);
			$("#description").val(data[0].description);
			$("#parentId").val(data[0].parentId);
		},error: function (data, status, e){
			alert(e);
		}
	});
}
powrer.toEditResource=function(value){
	var params = {
			id:value,
			name:$("#name").val(),
			url:$("#url").val(),
			parentId:$("#parentId").val(),
			description:$("#description").val()
	};
	$.ajax({
		url : "/power/editsave",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			if (data != '-1') {
				alert("修改成功！");
				location.href = "/power/list";
			} else {
				alert("修改失败！");
				location.href = "/power/list";
			}
		},error: function (data, status, e){
			alert(e);
		}
	});
}
powrer.resetdiv=function(){
	$("#name").val("");
	$("#url").val("");
	$("#parentId").val(0);
	$("#description").val("");
}
powrer.AddWinOpen=function(value,title){
	powrer.resetdiv();
	var val=value;
	$("#dialog").dialog({
		height:540,
		width:600,
		title:title,
		buttons : {
			"确定" : function() {
				powrer.toAddResource(val);
			},
			"取消" : function() {
				$(this).dialog('close');
			}
		}
	});
}
powrer.toAddResource=function(value){
	var params = {
			name:$("#name").val(),
			url:$("#url").val(),
			parentId:value,
			description:$("#description").val()
	};
	$.ajax({
		url : "/power/addsave",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			if (data != '-1') {
				alert("保存成功！");
				location.href = "/power/list";
			} else {
				alert("保存失败！");
				location.href = "/power/list";
			}
		},error: function (data, status, e){
			alert(e);
		}
	});
}
powrer.toDelResource=function(value){
	var params = {id:value};
	$.ajax({
		url : "/power/del",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			if (data != '-1') {
				//alert("删除成功！");
				location.href = "/power/list";
			} else {
				//alert("删除失败！");
				location.href = "/power/list";
			}
		},error: function (data, status, e){
			alert(e);
		}
	});
}