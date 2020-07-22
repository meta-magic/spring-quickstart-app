package com.fb.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fb.common.bean.LoginHelper;
import com.fb.common.bean.ResponseStatusBean;
import com.fb.common.service.TokenService;
import com.fb.util.AppMessage;

import atg.taglib.json.util.JSONObject;
import ch.qos.logback.classic.Logger;

/**
 * 
 * @author ketangote
 * @version 1.0
 */

@Component
@Aspect
@Order(2)
@Scope(value = "request")
public class TokenAspect {

	
	private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(TokenAspect.class);

	@Autowired
	private TokenService tokenService;

	@Autowired
	private LoginHelper loginHelper;

	@Around("fbAllOperations()")
	public Object validateToken(ProceedingJoinPoint joinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		try {
			String tokenId = request.getHeader("AuthorizedToken");
			JSONObject jsonObject = tokenService.getTokenData(tokenId);
			LOGGER.debug("TokenId="+tokenId+" TokenData="+jsonObject);
			loginHelper.setIds(
					jsonObject.getString(TokenService.USER_ID_KEY),
					jsonObject.getString(TokenService.APP_SESSION_ID_KEY),
					jsonObject.getString(TokenService.PROJECT_ID),
					jsonObject.getString(TokenService.MTE_ID),
					tokenId);
		} catch (Exception e) {
			ResponseStatusBean response = new ResponseStatusBean(false,
					AppMessage.getInstance().getMessage(AppMessage.INVALID_TOKEN),
					AppMessage.INVALID_TOKEN, null);
			
			return new ResponseEntity<ResponseStatusBean>(response, HttpStatus.UNAUTHORIZED);
			
		}  
		return joinPoint.proceed();
	}

	@Pointcut("execution(* com.fb.controller.*..*(..))")
	public void fbAllOperations() {
	}
	 

}
