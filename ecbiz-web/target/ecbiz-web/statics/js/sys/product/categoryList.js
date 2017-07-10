/**
 * 商品分类列表js
 */
var categoryList={};
//删除一个分类
categoryList.deletebyId=function(url,id){
	$.post("/productcategory/del",{id:id},function(data){
		location.href=url;
	});
	
};
//删除市使用弹出层
categoryList.MM_popupMsg=function(msg,url,id){ 
	  var bool=confirm(msg);
	  if(bool){
		  categoryList.deletebyId(url,id);
	  }
};
//跳转修改页面
categoryList.toEdit=function(url,id){
	location.href=url+"?id="+id;
};
categoryList.allnone=function(){
	$('#tab2 tr').each(
			function(){
				if($(this).attr("id") == ''){$(this).addClass('hiddshow');}
			});
	        $('.jia').each(function(){
			 $(this).attr("src",$(this).attr("src").replace('jian.','jia.'));
			});
};

$(document).ready(function(){
////输出列表
	$.post("/productcategory/catlist",{},function(data){
			parsejson(data,0);	
			$("#tab2").append(output);
			data_list("data_list","#FFFFFF","#C0EFFF","#fcf5dd","#fcf5dd");
			categoryList.allnone();
			
	});
	var output='';
	var parsejson=function(json,count) {
		var spaceStr="";
		if (0!=count) {
			for (var i=0;i<count;i++) {
				spaceStr+="&nbsp;&nbsp;&nbsp;";
			}
		} else {
			count=1;
		}
		for (var i=0;i<json.length;i++) {
				if(json[i].catName!=undefined){
					var idvalue='';
					if (json[i].parentId=='0') {spaceStr='';idvalue="r-"+json[i].depthPath;}
					output+='<tr onMouseOut=mOut(this,"#FFFFFF",event) id="'+idvalue+'" class="r-'+json[i].depthPath+'" onMouseOver=mOvr(this,"#C0EFFF",event)  bgcolor="#F8F8F8" align="center" valign="middle">';
					output+='<td width="490">'+spaceStr+'<img src="../../statics/images/ico_jian.gif" class="jia" border="0" style="margin-left:'+count+'em" />'+json[i].catName+'</td>';
					output+='<td>';
						if (json[i].parentId=='0'){
							output+='顶级分类';
						}else{
							output+='上级分类名';
						}
					output+='</td>';
					output+='<td>';
					output+=json[i].sortOrder+'层';
					output+='</td>';
					output+='<td>';
					if (json[i].isShow=='1') {
						output+='显示';
					}else{
						output+='不显示';
					}
					output+='</td>';
					 output+='<td>';
					 output+='<a href=javascript:categoryList.toEdit("edit",'+json[i].id+')><img src="../../statics/images/picture/edit2.gif" width="15" height="15" border="0"></a>';
					 output+='</td>';
					output+='<td>';
					 output+='<img src="../../statics/images/picture/delete.gif" width="15" height="15" onclick=categoryList.MM_popupMsg("你确删除此信息嘛?","list",'+json[i].id+')>';
					output+='</td>';
				output+='</tr>';
				}
				if (json[i].childCategoryList!=undefined) {
						arguments.callee(json[i].childCategoryList,count+1);
				}
		}
		
	};
	//点击加减图标事件
	$(document).on("click",'.jia',function(){
		var thisSrc = $(this).attr("src");
		var stat = '';
		if (thisSrc.indexOf("jia.",0) == -1){
			stat = 'jia.';thisSrc = thisSrc.replace('jian.','jia.');
		}else{
			thisSrc = thisSrc.replace('jia.','jian.');stat = 'jian.';
		}
		$(this).attr("src",thisSrc);
		var pclass = $(this).parent().parent().attr("class");
		$('#tab2 tr').each(function(){	
			if($(this).attr("class").indexOf(pclass+'-',0) != -1){
					if(stat == 'jia.'){
					    var src = $(this).children().eq(0).children().eq(0).attr("src").replace('jian.','jia.');
						if($(this).attr("class").indexOf('hiddshow',0) == -1){ 
							$(this).addClass('hiddshow');
						}
					}if(stat == 'jian.'){
						var src = $(this).children().eq(0).children().eq(0).attr("src").replace('jia.','jian.');
						if($(this).attr("class").indexOf('hiddshow',0) != -1){
							$(this).removeClass('hiddshow');
						}
					}
					$(this).children().eq(0).children().eq(0).attr("src",src);
			 }
		});});
	$(document).on("click",'#allhidd',function(){
				$('#tab2 tr').each(
				function()
				{
					if($(this).attr("id") == ''){
						$(this).addClass('hiddshow');
					}
				});
				$('.jia').each(
				function()
				{
					$(this).attr("src",$(this).attr("src").replace('jian.','jia.'));
				});
			});
	$(document).on("click",'#allShow',function(){
				$('#tab2 tr').each(
				function()
				{
					if($(this).attr("id") == ''){
					$(this).removeClass('hiddshow');
					}
				});
				$('.jia').each(
				function()
				{
					$(this).attr("src",$(this).attr("src").replace('jia.','jian.'));
				});
			});
	
})	