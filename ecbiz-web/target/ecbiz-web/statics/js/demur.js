function MM_popupMsg(msg,url) { //v1.0
  var bool=confirm(msg);
  if(bool){
  	location.href=url;
  }
}

function MM_callJS(jsStr) { //v2.0
  return eval(jsStr)
}