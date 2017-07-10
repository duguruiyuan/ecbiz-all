function pagestyle(cssPrefix)
{
	var n = readcookie();
	var url ="http://images.chinaren.com/product/club/"+cssPrefix+n+".css";
	var oCSS = document.getElementById("cssfile");
	if (oCSS!=null && oCSS.tagName.toLowerCase()=="link") {
		document.getElementsByTagName("head").item(0).removeChild(oCSS);
	}
	oCSS = document.createElement('link');
	oCSS.setAttribute("rel","stylesheet");
	oCSS.setAttribute("type","text/css");
	oCSS.setAttribute("id",'cssfile');
	oCSS.setAttribute("href", url);
	document.getElementsByTagName("head").item(0).appendChild(oCSS);
}

function changestyle(n){
	writecookie(n);
	if(top && top.frames.length > 0){
		for(var i =0;i<top.frames.length;i++){
			var od = top.frames[i];
			try{
				var oCSS  = od.document.getElementById('cssfile');
				if(oCSS){
					var oldCss = oCSS.href;
					var idx = oldCss.indexOf(".css");
					if(idx != -1){
						var newCss = oldCss.substring(0,idx-1);
						newCss=newCss+n+".css";
						oCSS.href = newCss;
					}
				}
			}catch(e){
				continue;	
			}
		}
	}
	try{
		var oCSS = document.getElementById("cssfile");
		if(!oCSS) return;
		var oldCss = oCSS.href;
		var idx = oldCss.indexOf(".css");
		if(idx != -1){
			var newCss = oldCss.substring(0,idx-1);
			newCss=newCss+n+".css";
			oCSS.href = newCss;
		}
	}catch(e){}
}

function writecookie(n)
{
	if (n=="") {n=1}
	var expires = new Date();
	expires.setTime(expires.getTime() + 24*60*60*365*1000);
	var flag = "CR_CLUB_STYLE="+n;
	document.cookie=flag+";path=/;expires="+expires.toGMTString();
}

function readcookie()
{
	var j = 0
	var mycookie = document.cookie;
	var name = "R_CLUB_STYLE";
	var start1 = mycookie.indexOf(name+"=");
	if (start1 == -1){
		j=1;
	}else{
		start=mycookie.indexOf("=",start1)+1; 
		var end = mycookie.indexOf(";",start);
		if (end==-1){
			end=mycookie.length;
		}
		var value=unescape(mycookie.substring(start,end));
		if (value==null){
			j=1;
		}else{
			j=value;
		}
	}
	return j;
}
