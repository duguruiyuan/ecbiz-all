/**
 * 
 */
var sysLogin={};
sysLogin.toLogin=function(){
	var username=$("#username").val();
	var pwssword=$("#password1").val();
	var a=username.length; var b=pwssword.length;
	
	if(a==0||b==0){
		alert("用户名和密码不能为空 !");
		return;
	}
	var params = {
			uname:username,
			password:pwssword
	};
	$.ajax({
		url : "/sysuser/findSysUser",
		dataType : 'json',
		type : 'post',
		scriptCharset :'utf-8',
		data : params,
		success : function(data) {
			if (data != '-1') {
				location.href = "index.htm";
			} else {
				alert("账号或秘密错误!");
			}
		},error: function (data, status, e){
			alert(e);
		}
	});
}
