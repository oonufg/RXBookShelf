package ru.pablo.Spring.Security.JWT;

import com.google.gson.Gson;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Map;

public class JWTUtils {
    public static String signToken(Map<String, String> headers, Map<String, String> payload, String key){
        Gson json = new Gson();
        String signature = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);

            signature = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(sha256_HMAC.doFinal((
                            Base64.getUrlEncoder().encodeToString(json.toJson(headers).getBytes()) + "." + Base64.getUrlEncoder().encodeToString(json.toJson(payload).getBytes()))
                            .getBytes()
                    ));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return signature;
    }
    public static String signToken(String headers, String payload, String key){
        String signature = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secretKeySpec);

            signature = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(sha256_HMAC.doFinal((headers + "." + payload).getBytes()));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return signature;
    }

}
