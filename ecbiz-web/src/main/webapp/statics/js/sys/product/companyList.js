/**
 * 商品分类列表js
 */
var companyList={};
//删除一个分类
companyList.deletebyId=function(url,id){
	$.post("/productcompany/del",{id:id},function(data){
		location.href=url;
	});
};
//删除使用弹出层
companyList.MM_popupMsg=function(msg,url,id){
	  var bool=confirm(msg);
	  if(bool){
		  companyList.deletebyId(url,id);
	  }
};
//跳转修改页面
companyList.toEdit=function(url,id){
	location.href=url+"?id="+id;
};
//查询
companyList.seach=function(){
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

