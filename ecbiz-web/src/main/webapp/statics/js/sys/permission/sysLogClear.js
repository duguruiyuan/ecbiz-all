/**
 * 
 */
var sysLogClear={};

sysLogClear.opcal= function(){
    $.calendar.Show();
}
// 页面初始化执行
$(document).ready(function() {
	$('#startTime').calendar();
	$('#endTime').calendar();
});

sysLogClear.logClear=function(){
	var startTime=$('#startTime').val();
	var endTime=$('#startTime').val();
	alert("日志清除成功!");
}

