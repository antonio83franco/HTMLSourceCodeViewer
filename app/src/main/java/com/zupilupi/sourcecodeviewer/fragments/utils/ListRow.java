package com.zupilupi.sourcecodeviewer.fragments.utils;

public class ListRow {

	private String resType;
	private String resName;
	private String resUrl;
	
	public ListRow (String type, String name, String url) {
		this.resType = type;
		this.resName = name;
		this.resUrl = url;
	}
	
	public String getResType() {
		return resType;
	}
	public void setResType(String resType) {
		this.resType = resType;
	}
	public String getResName() {
		return resName;
	}
	public void setResName(String resName) {
		this.resName = resName;
	}
	public String getResUrl() {
		return resUrl;
	}
	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}
	
	public String toString() {
		return this.resUrl;
	}
	
}
