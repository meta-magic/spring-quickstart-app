
package com.fb.common.service;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;


/**
 * 
 * @author ketan gote
 * @version 1.0
 * 
 */
public interface TokenService {


	/** TOKEN KEY CONSTANTS */
	public final static String USER_ID_KEY = "userId";

	public final static String APP_SESSION_ID_KEY = "appSessionId";
	
	public final static String PROJECT_ID = "projectId";
	
	public final static String MTE_ID = "mteid";

	public JSONObject getTokenData(String tokenId) throws JSONException;
}
