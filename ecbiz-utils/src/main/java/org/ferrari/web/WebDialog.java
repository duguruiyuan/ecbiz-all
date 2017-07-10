package org.ferrari.web;

import java.util.ArrayList;

public class WebDialog {

	private String title;
	private String message;
	private String path;
	private String refresh;
	private ArrayList<Object> buttons = new ArrayList<Object>();

	public WebDialog(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRefresh() {
		return refresh;
	}

	public void setRefresh(int seconds, String url) {
		if (seconds <=0) throw new IllegalArgumentException("seconds must large than zero.");
		if (url == null) throw new IllegalArgumentException("url is null.");
		this.refresh = seconds + ";url=" + url;
	}

	public void addButton(String name, String path) {
		buttons.add(new Button(name,path));
	}

	public int getButtonSize() {
		return buttons.size();
	}

	public String getButtonName(int index) {
		return ((Button) buttons.get(index)).name;
	}

	public String getButtonPath(int index) {
		return ((Button) buttons.get(index)).path;
	}

	class Button {
		private String name;
		private String path;
		Button(String name, String path) {
			this.name = name;
			this.path = path;
		}
	}

}
