	function goPage(pageNum){
		if(/^\d+$/.test(pageNum)==false) {
			return;
		}
		if(pageNum < 1) {
			pageNum = 1;
		}
		if(pageNum > totalPageSize) {
			if(totalPageSize>0){
				pageNum = totalPageSize;
			}else{
				pageNum=1;
			}
		}
		$("#pageCurrentPage").val(pageNum);
		$("#searchForm").submit();
	}
	
	function showPageNumber(c,t) {
		var currentPage = c-1<1?1:c;
		var totalPage = t;
		var start;
		var end;
		if(totalPage <= 5){
			start = 1;
			end = totalPage;
		}else{
			if((totalPage - currentPage) <= 2){
				start = totalPage - 4;
				end = totalPage;
			}else if(currentPage < 3){
				start = 1;
				end = 5;
			}else{
				start = currentPage - 2;
				end = currentPage + 2;
			}
		}
		
		var pageHtml="";
		for(var i=end; i>=start; i--) {
			pageHtml += "<a href='javascript:goPage("+i+");'"
			if(i == currentPage){
				pageHtml += 'class="cur"';
			}
			pageHtml += ">"+ i +"</a>";	
		}
		$("#pageNumberSpan").append(pageHtml);
    }
	
  	//跳转到页面
	function goPageByInput() {
		var pageNo = document.getElementById("pageNoIpt").value;
		if(/^\d+$/.test(pageNo)==false) {
			alert("只能输入整数，请重新输入！");
			document.getElementById("pageNoIpt").value='';
			return;
		}
		goPage(pageNo);
	}