package org.ferrari.struts.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class BaseForm extends ActionForm{
	private static final long serialVersionUID = -7325376575210265227L;
	private String actionType = null;
	private List<?> dataList = null;
	private String argsKeys = null;
	private String pagekeys = "1";
	/**
	 * @return the pagekeys
	 */
	public String getPagekeys() {
		return pagekeys;
	}
	/**
	 * @param pagekeys the pagekeys to set
	 */
	public void setPagekeys(String pagekeys) {
		this.pagekeys = pagekeys;
	}
	/**
	 * @return the dataList
	 */
	public List<?> getDataList() {
		return dataList;
	}
	/**
	 * @param dataList the dataList to set
	 */
	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}
	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	/**
	 * @return the argsKeys
	 */
	public String getArgsKeys() {
		return argsKeys;
	}
	/**
	 * @param argsKeys the argsKeys to set
	 */
	public void setArgsKeys(String argsKeys) {
		this.argsKeys = argsKeys;
	}

}
