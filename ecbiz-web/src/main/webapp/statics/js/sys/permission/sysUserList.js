/**
 * 商品分类列表js
 */
var sysUserList={};
//删除一个分类
sysUserList.deletebyId=function(url,id){
	$.post("/productbrand/del",{id:id},function(data){
		location.href=url;
	});
};
//删除使用弹出层
sysUserList.MM_popupMsg=function(msg,url,id){ alert(1);
	  var bool=confirm(msg);
	  if(bool){
		  brandList.deletebyId(url,id);
	  }
};
//跳转修改页面
sysUserList.toEdit=function(url,id){
	location.href=url+"?id="+id;
};
//查询
sysUserList.seach=function(){
	var letter=$("#letter").val();
	var seachType=$("#seachType").val();
	var keyword=$("#keyword").val();
	if(letter==""){
		alert("输入条件!");
		
	}if(keyword=""){
		alert("输入关键字!！");
		
	}
	$("#searchForm").submit();
	
}

$(document).ready(function(){
	
})	
