package com.fb.util;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import ch.qos.logback.classic.Logger;


/**
 * 
 * @author ketan gote
 * @version 1.0
 * 
 */

public class AppMessage implements MessageConstant {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AppMessage.class);

	private static final AppMessage appMessage = new AppMessage();

	private Properties messages = new Properties();

	private Map<String, String> dataTypeMap = new HashMap<String, String>();

	private AppMessage() {

	}

	public void loadMessages() {
		try {
			Resource resource = new ClassPathResource("app.messages.properties");
			messages.load(resource.getInputStream());
			log.info("Message loaded from app.messages.properties ");
		} catch (IOException e) {
			log.error("Unable to load Message from app.messages.properties, exception {} ", e.getMessage());
		}

	}

	public void loadDataType() {
		try {
			this.dataTypeMap.put("1", "boolean");
			this.dataTypeMap.put("2", "date");
			this.dataTypeMap.put("3", "number");
			this.dataTypeMap.put("4", "string");
		} catch (Exception e) {
			log.error("Unable to load Data types , exception {} ", e.getMessage());
		}
	}

	public static AppMessage getInstance() {
		return appMessage;
	}

	public String getDataType(String key) {
		return dataTypeMap.get(key);
	}

	public String addMessage(String message, String code) {
		return (String) messages.put(message, code);
	}

	public String getMessage(String code) {
		String message = (String) this.messages.get(code);
		return (message != null) ? message : code;
	}
}