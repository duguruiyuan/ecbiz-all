/**
 * 分配角色
 */
//var popedom={}
//
//popedom.selectRole=function(){
//	alert(33);
//}
//popedom.noselectRole=function(){
//	alert(44);
//}
$(document).ready(function() {
	$("#roleId option:first,#popedomIds option:first").attr("selected",true);
	$("#selects").live("click",function(){
		var items = $("#roleId option");
		var so = $("#roleId option:selected");
		if(so.get(so.length-1).index == items.length-1) so.prev().attr("selected",true);
		else so.next().attr("selected",true);
		$("#popedomIds").append(so);
	});
	$("#unselects").live("click",function(){
	    var items = $("#popedomIds option");
	    var so = $("#popedomIds option:selected");
			if(so.get(so.length-1).index == items.length-1) so.prev().attr("selected",true);
			else so.next().attr("selected",true);
			$("#roleId").append(so);
	});
	$("#roleId").live("dblclick",function(){
		var items = $("#roleId option");
		var so = $("#roleId option:selected");
		if(so.get(so.length-1).index == items.length-1) so.prev().attr("selected",true);
		else so.next().attr("selected",true);
		$("#popedomIds").append(so);
	});
	$("#popedomIds").live("dblclick",function(){
		var items = $("#popedomIds option");
	    var so = $("#popedomIds option:selected");
			if(so.get(so.length-1).index == items.length-1) so.prev().attr("selected",true);
			else so.next().attr("selected",true);
			$("#roleId").append(so);
	});
	
});
function save(form, action) {
		$("#popedomIds option:all").attr("selected",true);
		form.action = action;
		form.method = "post";
		form.submit();
}