/**
 * 菜单管理js
 */
var powrer = {};
powrer.openDialogs = function(type, value) {
	var title='';
	var url = '';
	if(type == "delete"){
		
	}
	if(type == "add"){
		title="资源添加";
		url = "/system/resource/add.do?parentId="+value;
		powrer.WinOpen(url,title);
	}
	if(type == "edit"){
		title="资源修改";
		url = "/system/resource/edit.do?id="+value;
		powrer.WinOpen(url,title);
	}if(type == "move"){
		title="资源移动";
		url = "/system/resource/move.do?id="+value;
		powrer.WinOpen(url,title);
	}
}
powrer.WinOpen=function(url,title){
	$(function() {
		$("#dialog").dialog({
			height:540,
			width:600,
			title:title,
			buttons : {
				"确定" : function() {
					
				},
				"取消" : function() {
					$(this).dialog('close');
				}
			}
		});
	});
}
powrer.addResource=function(){
	
}
