/**
 * 
 */
var sysLogs={};
sysLogs.opcal= function(){
    $.calendar.Show();
}
// 页面初始化执行
$(document).ready(function() {
	$('#startTime').calendar();
	$('#endTime').calendar();
});

sysLogs.seach=function(){
	var startTime=$('#startTime').val();
	var endTime=$('#startTime').val();
	var userName=$('#userName').val();
	alert("待完善!");
}