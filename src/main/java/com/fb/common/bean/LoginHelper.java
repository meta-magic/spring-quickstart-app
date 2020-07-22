package com.fb.common.bean;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 
 * @author ketan gote
 * @version 1.0
 * 
 */
@Component
@Scope("request")
public class LoginHelper {

	private String userId;

	private String appSessionId;

	private String projectId;
	
	private String mteid;
	
	private String projectVersionId;
	
	private String tokenId;
	
	public LoginHelper(){
		
	}
	
	public void setIds(String userId, String appSessionId){
		this.userId = userId;
		this.appSessionId = appSessionId;
	}
	
	public void setIds(String userId, String appSessionId, String projectId, String mteid, String tokenId){
		this.userId = userId;
		this.appSessionId = appSessionId;
		this.projectId = projectId;
		this.mteid = mteid;
		this.projectVersionId = "v1.0";
		this.tokenId = tokenId;
	}
	
	public String getUserId() {
		return userId;
	}

	public String getAppSessionId() {
		return appSessionId;
	}
	
	public String getProjectId(){
		return this.projectId;
	}
	
	public String getMteid(){
		return this.mteid;
	}
	
	public String getProjectVersionId(){
		return this.projectVersionId;
	}
	
	public String getTokenId(){
		return this.tokenId;
	}
	
}
