function scirptLibs(){}
//添加String对象的方法trim
String.prototype.trim = function(value) {
	if (value == null || value == "") {
		return this.replace(/(^\s*)|(\s*$)/g, "");
	} else {
		var regexp = new RegExp("(^" + value + "*)|(" + value + "*$)", "g");
		return this.replace(regexp, "");
	}
}

//添加String对象的方法padLeft
function padLeft(chr, len) {
	var temp = this;
	for(var i = 0; i < len - this.length; i=i+1)
		temp = chr + temp;
	return temp;
}
String.prototype.padLeft = padLeft;

//添加String对象的方法padRight
function padRight(chr, len) {
	var temp = this;
	for(var i = 0; i < len - this.length; i=i+1)
		temp += chr;
	return temp;
}
String.prototype.padRight = padRight;

//添加String对象的方法replaceAll
function replaceAll(str, replaceWith) {
	var regexp = new RegExp(str, "g");
	return this.replace(regexp, replaceWith);
}
String.prototype.replaceAll = replaceAll;

//添加String对象的方法bytes
function totalBytes(){
	var value = this;
	var n = 0;
	for(var i=0; i<value.length; i=i+1)
		n += (value.charAt(i) <= "~") ? 1:2;
	return(n);
}
String.prototype.totalBytes = totalBytes;

function tableExpend(n) {
	var td = window.event.srcElement;
	var tr = td.parentElement.nextSibling;
	var disp = "";
	if (tr.style.display == "") {
		disp = "none";
		td.innerText = td.innerText.replace('-','+');
	} else {
		disp = "";
		td.innerText = td.innerText.replace('+','-');
	}
	for (var i=1; i<=n; i=i+1) {
		tr.style.display = disp;
		tr = tr.nextSibling;
	}
}

//是否为数字
function isNumber(value, minlen, maxlen) {
	if (minlen == null) minlen = 1;
	if (maxlen == null) maxlen = "";
	var regexp = new RegExp("^\\d{" + minlen + "," + maxlen + "}$");
	return (regexp.test(value));
}

function isFloat(value, minlen, maxlen, fminlen, fmaxlen) {
	return true;
}

//验证代码
function isCode(value, minlen, maxlen) {
	if (minlen == null) minlen = 1;
	if (maxlen == null) maxlen = "";
	var regexp = new RegExp("^[0-9,a-z,A-Z,_,-]{" + minlen + "," + maxlen + "}$");
	return (regexp.test(value));
}

//验证文本
function isText(value, minlen, maxlen) {
	if (minlen == null) minlen = 1;
	if (maxlen == null) {
		return (value.length >= minlen);
	} else {
		return (value.length >= minlen) && (value.length <= maxlen);
	}
}

//验证文本
function isBytes(value, minlen, maxlen) {
	var len = value.totalBytes();
	if (minlen == null) minlen = 1;
	if (maxlen == null) {
		return (len >= minlen);
	} else {
		return (len >= minlen) && (len <= maxlen);
	}
}

//是否为移动号码
function isMobileNumber(value, format) {
	if (format == 1) {
		return /^(86)13[4-9][0-9]{8}$/.test(value);
	} else if (format == 2) {
		return /^13[4-9][0-9]{8}$/.test(value);
	} else {
		return /^(86)?13[4-9][0-9]{8}$/.test(value);
	}
}

//是否为联通号码
function isUnicomNumber(value, format) {
	if (format == 1) {
		return /^8613[0-3][0-9]{8}$/.test(value);
	} else if (format == 2) {
		return /^13[0-3][0-9]{8}$/.test(value);
	} else {
		return /^(86)?13[0-3][0-9]{8}$/.test(value);
	}
}

//是否为枚举元素
function isEnum(value, items) {
	try {
		for (var i=0; i<items.length; i=i+1)
			if (value == items[i]) return true;
		return false;
	} catch(e) {
		return false;
	}
	return false;
}

//是否为时间
function isDateTime(value, pattern) {
	var format = pattern.replaceAll("mm", "([0-5][0-9])");
	format = format.replaceAll("m", "([0-9]|[1-5][0-9])");
	format = format.replaceAll("HH", "([0-1][0-9]|[2][0-3])");
	format = format.replaceAll("H", "([0-9]|[1][0-9]|[2][0-3])");
	format = format.replaceAll("ss", "([0-5][0-9])");
	format = format.replaceAll("s", "([0-9]|[1-5][0-9])");
	format = format.replaceAll("dd", "([0-2][0-9]|[3][0-1])");
	format = format.replaceAll("d", "([0-9]|[1-2][0-9]|[3][0-1])");
	format = format.replaceAll("MM", "([0][1-9]|[1][0-2])");
	format = format.replaceAll("M", "([1-9]|[1][0-2])");
	format = format.replaceAll("yyyy", "([0-9]{4})");
	format = format.replaceAll("yy", "([0-9]{2})");
	
	var rege = new RegExp("^" + format + "$", "g");
	if (rege.test(value) == false) return false;
	rege = new RegExp("^" + format + "$", "g");
	var values = rege.exec(value);
	
	var year = 0, month = 0, day = 0;
	format = "(MM|M|yyyy|yy|ss|s|HH|H|dd|d|mm|m)[^yMdHms]*";
	rege = new RegExp(format, "g");
	var index = 1;
	while((names=rege.exec(pattern)) != null) {
		if (names[1] == "yyyy")
			year = (values[index++]);
		else if (names[1] == "yy")
			year = 1900 + new Number(values[index++]);
		else if (names[1] == "MM" || names[1] == "M")
			month = (values[index++]);
		else if (names[1] == "dd" || names[1] == "d")
			day = (values[index++]);
		else if (names[1] == "HH" || names[1] == "H" 
			|| names[1] == "mm" || names[1] == "m"
			|| names[1] == "ss" || names[1] == "ss")
			index++;
	}
	
	if (month == 4 || month == 6 || month == 9 || month == 11) {
		return (day <= 30);
	} else if (month == 2) {
		if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
			return (day <= 29);
		else
			return (day <= 28);
	} else {
		return (day <= 31);
	}
}

//匹配正则表达式
function isRegExp(value, express) {
	var regexp = new RegExp(express, "g");
	return regexp.test(value);
}

//15转18位
function certIdConvert(certid) {
	certid = certid.substr(0,6) + "19" + certid.substr(6,9);
	var v_sfz_bianma = "2485A973612485A97";
	var v_sum = 0 ;
	var v_bianma = 0;
	var v_18 = "";
	for (i=0; i<17; i=i+1) {
		if (v_sfz_bianma.substr(16-i,1) != 'A')
	  		v_bianma = new Number(v_sfz_bianma.substr(16-i,1));
	  	else
	  		v_bianma = 10;
		v_sum += new Number(certid.substr(i,1)) * v_bianma  ;
	}
	
	v_sum = v_sum % 11;
	
	if (v_sum == 0 || v_sum == 1) {
		v_18 = 1 - v_sum;
	} else if (v_sum == 2) {
		v_18 = "X";
	} else {
		v_18 = 12 - v_sum;
	}
	certid += v_18;
	return certid;
}

//是否是身份证号
function isCertId(value, format) {
	value = value.toUpperCase();
	if (format == 18) {
		var id15 = value.substr(0, 6) + value.substr(8, 9);
		return (certIdConvert(id15) == value && isDateTime(value.substr(6, 8), "yyyyMMdd"));
	} else if (format == 15) {
		if (value.length != 15) return false;
		return (isDateTime(value.substr(6, 6), "yyMMdd"));
	} else {
		if (value.length == 15) {
			return (isDateTime(value.substr(6, 6), "yyMMdd"));
		} else if (value.length == 18) {
			var id15 = value.substr(0, 6) + value.substr(8, 9);
			return ((certIdConvert(id15) == value) && isDateTime(value.substr(6, 8), "yyyyMMdd"));
		} else {
			return false;	
		}
	}
}

// 验证选择的时间是否晚于当前时间
function test(time)
{
	var myDate = new Date();
	myDate.toLocaleString();

	var time1 = new Date(time);
	if(Date.parse(myDate) - Date.parse(time1)==0)
	{
		alert(myDate+"=="+time1)
	}
	if(Date.parse(time) - Date.parse(myDate)<0)
	{
		alert(time+"zao"+myDate)
	}
	if(Date.parse(time) - Date.parse(myDate)>0)
	{
		alert(time+"wan"+myDate)
	}
}

//验证类型定义
function ValidType() {
	this.UnicomNumber = "UnicomNumber"; //手机号码 "false|true, UnicomNumber{0|1|2}, 消息"
	this.MobileNumber = "MobileNumber"; //手机号码 "false|true, MobileNumber{0|1|2}, 消息"
	this.Integer = "Integer"; //整型 "false|true, Integer{min, max}, 消息"
	this.Float = "Float"; //浮点型 "false|true, Float{min, max}, 消息"
	this.Text = "Text"; //验证文本字符长度 "false|true, Text{min, max}, 消息"
	this.Bytes = "Bytes"; //验证文本字节长度 "false|true, Bytes{min, max}, 消息"
	this.DateTime = "DateTime"; //日期 "false|true, DateTime{pattern}, 消息"
	this.Enum = "Enum"; //枚举类型 "false|true, Enum{item1, item2, item3}, 消息"
	this.Code = "Code"; //代码由0-9, a-z, A-Z, _组成 "false|true, Code{min, max}, 消息"
	this.CertId = "CertId"; //身份证号 "false|true, CertId{0|15|18}, 消息"
	this.RegExp = "RegExp"; //正则表达式 "false|true, RegExp{pattern}, 消息"
	this.Function = "Function"; //自定义函数 "false|true, Function{funName}, 消息"
}

//解析验证表达式
function ValidExp(validexp) {
	try {
		var regexp = new RegExp("^(false|true),([^,{}]*)(\{(.*)\})?,(.*)$", "g");
		var arr = regexp.exec(validexp);
		
		if (arr == null || arr.length != 6 ) throw "解析验证表达式时发生错误！";
		this.nullable = arr[1];
		this.validType = arr[2];
		this.message = arr[5];
		
		switch( arr[2] ) {
		case "UnicomNumber": case "MobileNumber": case "CertId":
			this.format = arr[4];
			break;
		case "Integer": case "Float": case "Text": case "Bytes": case "Code":
			if (arr[4] == "") {
				this.min = null;
				this.max = null;
				break;	
			}
			var point = arr[4].indexOf(",");
			if (point == -1) {
				this.min = arr[4];
				this.max = null;
			} else {
				this.min = arr[4].substring(0, point);
				this.max = arr[4].substring(point+1);
			}
			break;
		case "DateTime": case"RegExp":
			this.pattern = arr[4];
			break;
		case "Enum":
			this.items = arr[4].split(",");
			break;
		case "Function":
			this.functionName = arr[4];
			break;
		default:
			alert("验证类型(" + arr[2] + ")未定义");
			break;
		}
	} catch(e) {
		alert(e.description + "(" + validexp + ")");
	}
}

//统一验证表单方式
function validateForm(form) {
	//验证表单是否存在
	if (form == null || undefined) {
		alert("验证表单不存在！");
		return false;
	}
	
	try {
		var formitems = form.elements;
		for (var i = 0; i < formitems.length; i=i+1) {
			if(formitems[i].getAttribute("validexp") == null) continue;//没有定义验证信息 IE+other
			//if (formitems[i].validexp == undefined) continue; //没有定义验证信息 IE
			var validexp = new ValidExp(formitems[i].getAttribute("validexp"));
			if ((validexp.nullable == "true") && (formitems[i].value == "")) continue; //允许为空
			
			var validType = new ValidType();
			switch(validexp.validType) { //根据验证类型
			case validType.UnicomNumber:
				if (!isUnicomNumber(formitems[i].value)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.MobileNumber:
				if (!isMobileNumber(formitems[i].value)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.Integer:
				if (!isNumber(formitems[i].value, validexp.min, validexp.max)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.Code:
				if (!isCode(formitems[i].value, validexp.min, validexp.max)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.Text:
				if (!isText(formitems[i].value, validexp.min, validexp.max)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.Bytes:
				if (!isBytes(formitems[i].value, validexp.min, validexp.max)) {
					//alert(validexp.message);
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.RegExp:
				if (!isRegExp(formitems[i].value, validexp.pattern)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.DateTime:
				if (!isDateTime(formitems[i].value, validexp.pattern)) {
					//alert(validexp.message);
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.Enum:
				if (!isEnum(formitems[i].value, validexp.items)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.CertId:
				if (!isCertId(formitems[i].value, validexp.format)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.Function:
				if (!test(formitems[i].value, validexp.format)) {
					ymPrompt.alert({message:validexp.message,width:'300',height:'150',title:'提示',handler:''});
					setFocus(formitems[i]);
					return false;
				}
				break;
			case validType.Function:
				try {
					eval("var result = " + validexp.functionName + "(form,formitems[i]);");
				} catch(e) {
					alert("自定义函数"+ validexp.functionName + "(form, element)未定义");
					return false;
				}
				if (result == undefined) {
					alert("自定义函数" + validexp.functionName + "(form, element)无返回值！");
					return false;
				} else if (result == false) {
					alert(validexp.message);
					setFocus(formitems[i]);
					return false;
				}
				break;
			}
		}
		return true;
	} catch(e) {
		return false;
	}
}

function setFocus(obj) {
	try {
		switch(obj.type) {
		case "text": case "password":
			if (!obj.readOnly) obj.focus();
			break;
		case "select-one": case "select-multiple":
			if (obj.enabled) obj.focus();
			break;
		}
	} catch(e) {
		alert("set focus error!" + e.description);
	}
}

/*switch checkbox status selected or clear*/
function doSwitch(form, name, bValue) {
	try {
		checkForm(form);
		var checkObj = form.elements[name];
		if (checkObj == undefined || checkObj == null)
			return;
		if (checkObj.length>0) {
			for(var i=0; i<checkObj.length; i=i+1)
				if(!checkObj[i].disabled){
					checkObj[i].checked = bValue;
				}				
		} else {
			if(!checkObj.disabled){
				checkObj.checked = bValue;
			}
			
		}
	} catch(e) {
		alert(e.description == undefined ? e : e.description);
	}
}

/*valid checkbox item checked count*/
function checkedCount(checkObj) {
	var count = 0;
	if (checkObj != null) {
		if (checkObj.length>0) {
			for(var i=0; i<checkObj.length; i=i+1)
				if (checkObj[i].checked) count=count+1;
		} else {
			count = checkObj.checked ? 1:0;
		}
	}
	if (count <= 0) {
		throw "没有选择记录！";
	} else if (count > 1) {
		throw "只能选择一条记录！";
	}
}

/*valid checkbox has checked at least one*/
function isChecked(checkObj) {
	if (checkObj != null) {
		if (checkObj.length>0) {
			for(var i=0; i<checkObj.length; i=i+1)
				if (checkObj[i].checked) return true;
		} else {
			if (checkObj.checked) return true;
		}
	}
	//throw "没有选择记录！";
	throw ymPrompt.alert({message:'没有选择记录！',width:'300',height:'150',title:'提示',handler:''});
}
/*check form is exists*/
function checkForm(form) {
	if (form == undefined || form == null)
		throw "form undefined.";
	if (form.tagName != "FORM")
		throw "must specific a form.";
	return true;
}

/*query record*/
function queryRecord(form, action) {
	try {
		checkForm(form);
		form.action = action;
		form.method = "post";
		form.submit();
	} catch(e) {
		alert(e.description == undefined ? e : e.description);
	}
}

/*add record*/
function addRecord(form, action) {
	try {
		checkForm(form);
		form.action = action;
		form.method = "post";
		form.submit();
	} catch(e) {
		alert(e.description == undefined ? e : e.description);
	}
}

/*save record*/
function saveRecord(form, action) {
	try {
		checkForm(form);
		if (!validateForm(form)) return;
		form.action = action;
		form.method = "post";
		form.submit();
	} catch(e) {
		alert(e.description == undefined ? e : e.description);
	}
}
/*form submit*/
function submits(form, action) {
	try {
		if(action != undefined) form.action = action;
		form.method = "post";
		form.submit();
	} catch(e) {
		alert(e.description == undefined ? e : e.description);
	}
}

/*edit record*/
function editRecord(form, action, name) {
	try {
		checkForm(form)
		if (name != undefined)
			checkedCount(form.elements[name]);
		form.action = action;
		form.method = "post";
		form.submit();
	} catch(e) {
		alert(e.description == undefined ? e : e.description);
	}
}

/*update record*/
function updateRecord(form, action) {
	try {
		checkForm(form);
		if (!validateForm(form)) return;
		form.action = action;
		form.method = "post";
		form.submit();
	} catch(e) {
		alert(e.description == undefined ? e : e.description);
	}
}
var delBflag = false;
function delConfirm(msg){delBflag = msg == 'ok' ? true : false;}
/*delete record*/
function deleteRecord(form, action, name) {
	try {
		checkForm(form)
		if (name != undefined)
			isChecked(form.elements[name]);
		if (!confirm('确定要删除记录吗?'))
			return false;
		form.action = action;
		form.method = "post";
		form.submit();
	} catch(e) {
		//alert(e.description == undefined ? e : e.description);
	}
}

function removeRecord(form,action,value,name){
	try {
		checkForm(form)
		if (name != undefined){
			if(value == "" || value == "0"){
				ymPrompt.confirmInfo({message:'没有选择所属类别！',width:'300',height:'150',title:'提示',handler:delConfirm});
			}else if(isChecked(form.elements[name])){
				if(confirm('确定要移动记录吗?')){
					form.action = action;
					form.method = "post";
					form.submit();
				}
			}
		}
		
	} catch(e) {
		//alert(e.description == undefined ? e : e.description);
	}
}

function copyRecord(form,action,value,name){
	try {
		checkForm(form)
		if (name != undefined){
			if(value == "" || value == "0"){
				ymPrompt.confirmInfo({message:'没有选择所属类别！',width:'300',height:'150',title:'提示',handler:delConfirm});
			}else if(isChecked(form.elements[name])){
				form.action = action;
				form.method = "post";
				form.submit();
			}
		}
		
	} catch(e) {
		//alert(e.description == undefined ? e : e.description);
	}
}

function prompt(url, msg){
	this.url = url;
	this.getMessage = function(msg){
		if("ok" == msg)window.location.href = this.url;
		return;
	};
	ymPrompt.confirmInfo({message:msg,width:'300',height:'150',title:'提示',handler:getMessage});
}


function updateMoreRecord(form, action, name) {
		try {
			if (name != undefined)
				isChecked(form.elements[name]);
			form.action = action;
			form.method = "post";
			form.submit();
		} catch(e) {
			//alert(e.description == undefined ? e : e.description);
		}
	}