/**
 * 
 */

var sysOperation={};
sysOperation.openDialogs = function(type,value) {
	if(type=='add'){
		sysOperation.AddWinOpen(value,"添加操作");
	}
}
sysOperation.openUpdDialogs=function(type,value,resourceId){
	if(type=='edit'){
		sysOperation.EditWinOpen(value,"编辑操作",resourceId);
	}
	
}
sysOperation.del=function(type,value,resourceId){
	if(type=='del'){
		sysOperation.delOperation(value,resourceId);
	}
}
sysOperation.delOperation=function(value,resourceId){
	var params = {
			id:value,
			resourceId:resourceId
	};
	$.ajax({
		url : "/sysoperation/delete",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			if (data != '-1') {
				//alert("删除成功！");
				location.href = "/sysoperation/list?resourceId="+resourceId;
			} else {
				alert("保存失败！");
				location.href = "/sysoperation/list?resourceId="+resourceId;
			}
		},error: function (data, status, e){
			alert(e);
		}
	});
}
sysOperation.resetdiv=function(){
	$("#name").val("");
	$("#url").val("");
	$("#id").val(0);
	$("#description").val("");
}
sysOperation.getOperationbyId=function(value){
	//禁用缓存
	$.ajaxSetup ({ cache: false });
	var params = {id:value};
	$.ajax({
		url : "/sysoperation/edit",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			$("#name").val(data[0].name);
			$("#url").val(data[0].url);
			$("#description").val(data[0].description);
			$("#id").val(data[0].id);
		},error: function (data, status, e){
			alert(e);
		}
	});
}
sysOperation.EditWinOpen=function(value,title,resourceId){
	var val=value;
	sysOperation.getOperationbyId(val);
	$("#dialog").dialog({
		height:440,
		width:580,
		title:title,
		buttons : {
			"确定" : function() {
				sysOperation.toEditsysOperation(val,resourceId);
			},
			"取消" : function() {
				$(this).dialog('close');
			}
		}
	});
}
sysOperation.toEditsysOperation=function(val,resourceId){
	var params = {
			name:$("#name").val(),
			url:$("#url").val(),
			id:val,
			description:$("#description").val()
	};
	$.ajax({
		url : "/sysoperation/editsave",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			if (data != '-1') {
				alert("保存成功！");
				location.href = "/sysoperation/list?resourceId="+resourceId;
			} else {
				alert("保存失败！");
				location.href = "/sysoperation/list?resourceId="+resourceId;
			}
		},error: function (data, status, e){
			alert(e);
		}
	});
}
sysOperation.AddWinOpen=function(value,title){
	sysOperation.resetdiv();
	var val=value;
	$("#dialog").dialog({
		height:440,
		width:580,
		title:title,
		buttons : {
			"确定" : function() {
				sysOperation.toAddsysOperation(val);
			},
			"取消" : function() {
				$(this).dialog('close');
			}
		}
	});
}

sysOperation.toAddsysOperation=function(value){
	var params = {
			name:$("#name").val(),
			url:$("#url").val(),
			resourceId:value,
			description:$("#description").val()
	};
	$.ajax({
		url : "/sysoperation/addsave",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data:params,
		success : function(data) {
			if (data != '-1') {
				alert("保存成功！");
				location.href = "/sysoperation/list?resourceId="+value;
			} else {
				alert("保存失败！");
				location.href = "/sysoperation/list?resourceId="+value;
			}
		},error: function (data, status, e){
			alert(e);
		}
	});
}

