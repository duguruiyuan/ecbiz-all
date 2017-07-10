//表单部分
String.prototype.Trim = function(){
	return this.replace(/(^\s*)|(\s*$)/g, "");
}

var popupWin;

function msover(event)
{   var target = event.srcElement ? event.srcElement : event.target
		target.style.backgroundColor="#ccffff";
		target.style.cursor = "hand";
}

function msout(event)
{
	var target = event.srcElement ? event.srcElement : event.target
			target.style.backgroundColor="#FFF6DC";
			target.style.cursor = "auto";
}

function mOvr(src,clrOver,event){
	event = event||window.event;
    $(event.target).addClass("active");
	if (!src.contains(event.fromElement)) {
		src.style.cursor = 'hand';
		src.bgColor = clrOver;
	}
}
function mOut(src,clrIn,event)  {
	event = event||window.event;
    $(event.target).addClass("active");
    
	if (!src.contains(event.toElement)) {
		src.style.cursor = 'default';
		src.bgColor = clrIn;
	}
}

//清空表单
function ClearReset()
{
//	document.forms[0].vertype(0).checked == true
//	def.style.display = "none"
	document.forms[0].title.value="";
	document.forms[0].type.value="";
	document.forms[0].forbid2.value="";
	document.forms[0].year.value="";
	document.forms[0].month.value="";
	document.forms[0].date.value="";

}

//表单提交
function submitonce(theform)
{
	if (validate() == false){
		return false;
	}

}

//表单验证
function validate()
{

   //标题验证
   var title =document.forms[0].title.value.Trim();
   if ( title == "")
   {
	alert("\请输入标题！");
	return false;
   }

}

//首页
function FirstPage(theform)
{
	document.forms[0].currentPage.value = 1;
}

//上一页
function ForwardPage(theform)
{
	var currentPage = Number(document.forms[0].currentPage.value);
	var totalPage = Number(document.forms[0].totalPage.value);
	if(1 < currentPage && currentPage <= totalPage+1)
	{
		currentPage--;
		document.forms[0].currentPage.value = currentPage;
	}else{
		alert("已为第一页！");
		return false;
	}
}

//下一页
function NextPage(theform)
{
	var currentPage = Number(document.forms[0].currentPage.value);
	var totalPage = Number(document.forms[0].totalPage.value);
	if(0 < currentPage && currentPage < totalPage)
	{
		currentPage++;
		document.forms[0].currentPage.value = currentPage;
	}else{
		alert("已为最后一页！");
		return false;
	}
}

//末页
function LastPage(theform)
{
	var totalPage = Number(document.forms[0].totalPage.value);
	document.forms[0].currentPage.value = totalPage;
}

//附件下载
function FileSubmit()
{
  document.forms[0].action = "downLoadAction.do";
  document.forms[0].submit();
}


//结束