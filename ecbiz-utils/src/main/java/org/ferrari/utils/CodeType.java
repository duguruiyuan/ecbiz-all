package org.ferrari.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
   * 
   * @Create: Dec 13, 2008 2:54:13 PM     
   * @Description: TODO
   * @see: com.jmyz.cms.web.utils
   * @modify by: kevin
   * @time: Dec 13, 2008 2:54:13 PM
 */
public abstract class CodeType implements Serializable{

	private String codeTypeName = null;
	private ArrayList<Code> list = new ArrayList<Code>();
	private HashMap<String, Code> map = new HashMap<String, Code>();

	/**
	 * 
	 */
	public CodeType() {
		this.codeTypeName = getClass().getName().replaceAll("^([^\\.]+\\.)*", "");
	}

	/**
	 * 
	 *@description: TODO
	 *@return
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	public String getName() {
		return codeTypeName;
	}

	/**
	 * 
	 *@description: TODO
	 *@return
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	public Code getDefaultValue() {
		return new Code(this);
	}

	/**
	 * 
	 *@description: TODO
	 *@param code
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	protected void add(Code code) {
		if (!map.containsKey(code.getValue())) {
			list.add(code);
			map.put(code.getValue(), code);
		}
	}

	/**
	 * 
	 *@description: TODO
	 *@param value
	 *@param name
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	protected void add(String value, String name) {
		new Code(this, value, name);
	}

	/**
	 * 
	 *@description: TODO
	 *@param code
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	protected void remove(Code code) {
		if (map.containsKey(code.getValue())) {
			list.remove(code);
			map.remove(code);
		}
	}

	/**
	 * 
	 *@description: TODO
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	protected void removeAll() {
		list.clear();
		map.clear();
	}

	/**
	 * 
	 *@description: TODO
	 *@param value
	 *@return
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	public Code get(String value) {
		return (Code) map.get(value);
	}

	/**
	 * 
	 *@description: TODO
	 *@return
	 *@author: kevin
	 *@date: Dec 15, 2008
	 */
	public List<Code> getCodeList() {
		return list;
	}

}
