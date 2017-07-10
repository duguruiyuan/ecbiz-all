package org.ferrari.utils;



public class PageUtil {
	public static String pageStr (PageInfo pageInfo){
		
		Integer pageNo = pageInfo.getPageNo();
		Integer pageSize = pageInfo.getPageSize();
		Integer recordCount = pageInfo.getTotalCount();
		String method = "openPage";

		method = "javascript:" + method + "(";

		pageSize = pageSize == 0 ? pageSize = 20 : pageSize;

		int pageCount = (recordCount + pageSize - 1) / pageSize;

		StringBuilder sb = new StringBuilder();

		sb.append("<style type=\"text/css\">");
		sb.append(".pageDyn {padding:15px 0;position:relative;width:100%}");
		sb.append(".pagination {height:23px;line-height:23px;position:absolute; left:50%; font-size:12px;display:block}");
		sb.append(".pagination a, .pagination a:link, .pagination a:visited {padding:2px 5px;margin:2px;border:1px solid #aaaadd;text-decoration:none;color:#006699;}");
		sb.append(".pagination a:hover, .pagination a:active {border: 1px solid #ff0000;color: #000;text-decoration: none;}");
		sb.append(".pagination span.current {padding: 2px 5px;margin: 2px;border: 1px solid #006699;font-weight: bold;background-color: #006699;color: #FFF;}");
		sb.append(".pagination span.disabled {padding: 2px 5px;margin: 2px;border: 1px solid #eee; color: #ddd;}");
		sb.append("</style>\r\n");
		sb.append("<span id=\"pagination\" class=\"pagination\">\r\n");
		if (recordCount == 0) {
			sb.append("<strong>没有可显示的项目</strong>\r\n");
		} else {
			if (pageNo > pageCount)
				pageNo = pageCount;
			if (pageNo < 1)
				pageNo = 1;

			//sb.append("&nbsp;共<strong>").append(recordCount).append(
			//"</strong>项").append(",<strong>").append(pageCount).append(
			//"</strong>页:&nbsp;\r\n");
			if (pageNo == 1)
				sb.append("<span class=\"disabled\">&laquo;&nbsp;上一页").append(
						"</span>\r\n");
			else {
				sb.append("<a href=\"").append(method).append(
						pageNo - 1).append(")\">&laquo;&nbsp;上一页</a>\r\n");
			}

			int start = 1;
			if (pageNo > 4) {
				start = pageNo - 1;
				sb.append("<a href=\"").append(method).append("1)\">1</a>\r\n");
				sb.append("<a href=\"").append(method).append("2)\">2</a>\r\n");
				sb.append("&hellip;\r\n");
			}

			int end = pageNo + 1;
			if (end > pageCount) {
				end = pageCount;
			}
			for (int i = start; i <= end; ++i) {
				if (pageNo == i)
					sb.append("<span class=\"current\">").append(i).append(
							"</span>\r\n");
				else {
					sb.append("<a href=\"").append(method).append(i)
							.append(")\">").append(i).append("</a>\r\n");
				}
			}

			if (end < pageCount - 2) {
				sb.append("&hellip;\r\n");
			}
			if (end < pageCount - 1) {
				sb.append("<a href=\"").append(method).append(
						pageCount - 1).append(")\">").append(pageCount - 1)
						.append("</a>\r\n");
			}
			if (end < pageCount) {
				sb.append("<a href=\"").append(method).append(
						pageCount).append(")\">").append(pageCount).append(
						"</a>\r\n");
			}

			if (pageNo == pageCount)
				sb.append("<span class=\"disabled\">下一页&nbsp;&raquo;").append(
						"</span>\r\n");
			else {
				sb.append("<a href=\"").append(method).append(
						pageNo + 1).append(")\">下一页&nbsp;&raquo;</a>\r\n");
			}
		}

		sb.append("</span>\r\n");
		sb.append("<script type=\"text/javascript\">");
		sb.append("_pagePara.pageSize="+pageInfo.getPageSize()+";");
		sb.append("_pagePara.recordCount="+pageInfo.getTotalCount()+";");
		sb.append("</script>");
		return sb.toString();
		
	}
	
	/**
	 * 用于改版的新分页样式。参考页面showreputationNew
	 * @date 2016-02-26
	 * @param pageInfo
	 * @return
	 */
	public static String pageStr2 (PageInfo pageInfo){
		
		Integer currpage = pageInfo.getPageNo();
		Integer pageSize = pageInfo.getPageSize();
		Integer recordCount = pageInfo.getTotalCount();
		String method = "openPage";

		method = "javascript:" + method + "(";

		pageSize = pageSize == 0 ? pageSize = 20 : pageSize;

		int maxPage = (recordCount + pageSize - 1) / pageSize;

		StringBuilder buffer = new StringBuilder();
		if (maxPage <= 5) {
			if (currpage == 1) {
				buffer.append("<a  class=\"p-w\">首页</a>");
				buffer.append("<a  class=\"p-w\">上一页</a>");
				buffer.append("<a  class=\"page-cur\">1</a>");
			} else {
				if (currpage == 2) {
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\" class=\"p-w\">首页</a>");
					buffer.append("<a href=\""+method+(currpage-1)+")"+"\" target=\"_self\" class=\"p-w\">上一页</a>");
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\">1</a>");
				} else {
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\" class=\"p-w\">首页</a>");
					buffer.append("<a href=\""+method+(currpage-1)+")"+"\" target=\"_self\" class=\"p-w\">上一页</a>");
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\">1</a>");
				}
			}
			for (int i=2; i<=maxPage; i++) {
				if (currpage == i) {
					buffer.append("<a  class=\"page-cur\">"+i+"</a>");
				} else {
					buffer.append("<a  target=\"_self\" href=\""+method+i+")"+"\">"+i+"</a>");
				}
			}
			if (currpage == maxPage) {
				buffer.append("<a  class=\"p-w\">下一页</a>");
				buffer.append("<a  class=\"p-w\">末页</a>");
			} else {
				buffer.append("<a href=\""+method+(currpage+1)+")"+"\" target=\"_self\" class=\"p-w\">下一页</a>");
				buffer.append("<a href=\""+method+(maxPage)+")"+"\" target=\"_self\" class=\"p-w\">末页</a>");
			}
		} else {
			int size = 2;
			if ((maxPage - currpage) < 2) {
					size = maxPage - currpage;
			}
			int max = currpage + size;
			if (currpage == 1) {
				buffer.append("<a  class=\"p-w\">首页</a>");
				buffer.append("<a  class=\"p-w\">上一页</a>");
				buffer.append("<a  class=\"page-cur\">1</a>");
			} else {
				if (currpage == 2) {
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\" class=\"p-w\">首页</a>");
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\" class=\"p-w\">上一页</a>");
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\">1</a>");
				} else {
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\" class=\"p-w\">首页</a>");
					buffer.append("<a href=\""+method+(currpage-1)+")"+"\" target=\"_self\" class=\"p-w\">上一页</a>");
					buffer.append("<a href=\""+method+"1)"+"\" target=\"_self\">1</a>");
				}
			}
			if (currpage < 5) {
				for (int i=2; i<=5; i++) {
					if (currpage == i) {
						buffer.append("<a  class=\"page-cur\">"+i+"</a>");
					} else {
						buffer.append("<a  target=\"_self\" href=\""+method+i+")\">"+i+"</a>");
					}
				}
			} else {
				buffer.append("<em style=\"float:left;\">...</em>");
				for (int i = currpage-2; i <= max && i<=maxPage; i++) {
					if (currpage == i) {
						buffer.append("<a  class=\"page-cur\">"+i+"</a>");
					} else {
						buffer.append("<a  target=\"_self\" href=\""+method+i+")\">"+i+"</a>");
					}
				}
			}
			if (currpage == maxPage) {
				buffer.append("<a  class=\"p-w\">下一页</a>");
				buffer.append("<a  class=\"p-w\">末页</a>");
			} else {
				buffer.append("<a href=\""+method+(currpage+1)+")\" target=\"_self\" class=\"p-w\">下一页</a>");
				buffer.append("<a href=\""+method+(maxPage)+")"+"\" target=\"_self\" class=\"p-w\">末页</a>");
			}
		}
		
		return buffer.toString();
		
	}
}
