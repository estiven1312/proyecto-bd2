package com.zp.zorritoplus.util;

import org.json.JSONObject;

import java.util.Base64;

public class JwtUtil {
    private static String BEARER = "Bearer ";
    public static JSONObject decodeBearerToken(String token){
        token = token.replace(BEARER, "");
        String[] parts = token.split("\\.");
        return JwtUtil.decodeToken(parts[1]);
    }
    public static JSONObject decodeToken(String partOfToken){
        JSONObject jsonObject = new JSONObject(decode(partOfToken));
        return jsonObject;
    }
    private static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

}
