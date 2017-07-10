package org.ferrari.utils.pagination.factory;

import java.util.List;


public interface IQuery {
	public int getPageCount();

	public int getMax();

	public int getMin();
	
	public int getSqlMax();
	
	public int getSqlMin();

	public int getTotal();

	public void setTotal(int total);

	public int getPageSize();

	public void setPageSize(int pageSize);

	public int getCurrentPage();

	public void setCurrentPage(int page);

	public int getPreviousPage();

	public int getNextPage();

	public String getSearchKeys();

	public void setSearchKeys(String searchKeys);
	
	public List<?> getQueryList();
	
	public void setQueryList(List<?> queryList);
	
	public boolean isTurnPage();
	
	public void setTurnPage(boolean bflag);

	public String toString();
	
	public void setDisplayPage(int page);
	
	public int getDisplayPage();
}
