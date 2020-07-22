package com.fb.common.bean;


import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fb.util.AppMessage;

/**
 * 
 * @author ketangote
 * @version 1.0
 */

 
@JsonInclude(value = Include.NON_NULL)
public class ResponseStatusBean implements Serializable, ToJson{

	private boolean success;
	private String message;
	private String messageCode;
	private HttpStatus status;
	private Object data;
	private Integer type;
	
	public ResponseStatusBean(boolean success, String messageCode) {
		this.success = success;
		this.messageCode = messageCode;
		this.message = AppMessage.getInstance().getMessage(messageCode);
	}

	public ResponseStatusBean(boolean success, String messageCode, Object data) {
		this(success,messageCode);
		this.status = HttpStatus.OK;
		this.data = data;
	}

	public ResponseStatusBean(boolean success,String messageCode, HttpStatus status, Object data) {
		this(success,messageCode);
		this.status = status;
		this.data = data;
	}

	public ResponseStatusBean(boolean success, String message, String messageCode) {
		this.success = success;
		this.message = message;
		this.messageCode = messageCode;
	}
	

	public ResponseStatusBean(boolean success, String message, String messageCode, Object data) {
		this(success,message,messageCode);
		this.status = HttpStatus.OK;
		this.data = data;
	}
	
	public ResponseStatusBean(boolean success, String message, String messageCode, HttpStatus status, Object data) {
		this(success,message,messageCode);
		this.status = status;
		this.data = data;
	}

 


	@Override
	public String toString() {
		return "ServiceStatusBean [success=" + success + ", message=" + message + ", status=" + status + ", data=" + data + "]";
	}
	
	@Override
	public String toJson() {
		String json = "";
		try {
			json = new ObjectMapper().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	public atg.taglib.json.util.JSONObject toJsonObject() {
		atg.taglib.json.util.JSONObject json =  new atg.taglib.json.util.JSONObject();
		try {
			json = new atg.taglib.json.util.JSONObject(new ObjectMapper().writeValueAsString(this));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}

	public Integer getType() {
		return type;
	}
	
	
}
	
