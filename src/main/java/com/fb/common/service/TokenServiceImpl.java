package com.fb.common.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import atg.taglib.json.util.JSONException;
import atg.taglib.json.util.JSONObject;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


/**
 * 
 * @author ketan gote
 * @version 1.0
 * 
 */

@Service
@RequestScope
public class TokenServiceImpl implements TokenService {

	@Value("${token.key}")
	private String tokenKey;

	@Value("${token.validity}")
	private Long tokenValidity;
	
	private String generateTokenFromJson(JSONObject tokenJson, Long tokenExpiry) {
		return Jwts.builder().setSubject(tokenJson.toString())
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpiry))
				.signWith(SignatureAlgorithm.HS512, tokenKey).compact();
	}

	
	public JSONObject getTokenData(String tokenId) throws JSONException, ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		String tokenData = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(tokenId).getBody().getSubject();
		return new JSONObject(tokenData);
	}

}
