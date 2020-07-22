package com.fb;
 

import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.fb.util.AppMessage;

import ch.qos.logback.classic.Logger;

@Configuration
public class AppEventListener {

	private static final Logger log = (Logger) LoggerFactory.getLogger(AppEventListener.class);
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		log.info("Application is ready!");
	    AppMessage.getInstance().loadMessages();
	    AppMessage.getInstance().loadDataType();
	}
}