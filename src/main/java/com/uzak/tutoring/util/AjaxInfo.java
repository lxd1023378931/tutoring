package com.uzak.tutoring.util;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

public class AjaxInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6860222762788926L;
	private int status;
	private String message;
	private Object data;

	public AjaxInfo() {
	}

	public AjaxInfo(int status, String message) {
		this.status = status;
		this.message = message;
		this.data = null;
	}

	public AjaxInfo(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public void put(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public void put(int status, String message, Object data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return data;
	}

	public void setObject(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		JSONObject jso = new JSONObject();
		jso.put("status", this.status);
		jso.put("message", this.message);
		jso.put("data", this.data);
		return jso.toString();
	}

}
