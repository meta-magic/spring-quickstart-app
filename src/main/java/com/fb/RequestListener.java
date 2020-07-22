package com.fb;

import java.util.UUID;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.MDC;

@WebListener
public class RequestListener implements ServletRequestListener {
	
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		MDC.put("RequestId", UUID.randomUUID().toString());
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		MDC.clear();
	}
}