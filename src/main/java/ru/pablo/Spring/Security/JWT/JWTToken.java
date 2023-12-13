package ru.pablo.Spring.Security.JWT;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class JWTToken {
    private final Map<String, String> headers;
    private final Map<String, String> payload;
    private String encodedHeaders;
    private String encodedPayload;
    private final String signature;

    private Gson jsonSD = new Gson();

    public JWTToken(Map<String, String> headers, Map<String, String> payload, String signature){
        this.headers = headers;
        this.payload = payload;
        this.signature = signature;
    }

    public String getEncodedHeaders(){
        return this.encodedHeaders;
    }

    public String getEncodedPayload(){
        return this.encodedPayload;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getPayload() {
        return payload;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s",encodedHeaders,encodedPayload,signature);
    }

    public static class JWTBuilder{
        private Gson jsonSD;
        private Map<String, String> headers;
        private Map<String, String> payload;
        private String signature;

        public JWTBuilder(){
            jsonSD = new Gson();
        }

        public JWTToken decodeFromString(String encodedJwt){
            String[] encodedParts = encodedJwt.split("\\.");
            String decodedHeaders = new String(Base64.getUrlDecoder().decode(encodedParts[0]));
            String decodedPayload = new String(Base64.getUrlDecoder().decode(encodedParts[1]));
            Type mapType = new TypeToken<Map<String,String>>(){}.getType();
            Map<String,String> headers = jsonSD.fromJson(decodedHeaders, mapType);
            Map<String, String> payload = jsonSD.fromJson(decodedPayload, mapType);

            JWTToken token = new JWTToken(headers,payload,encodedParts[2]);
            token.encodedHeaders = encodedParts[0];
            token.encodedPayload = encodedParts[1];

            return  token;
        }
        public  JWTBuilder setHeaders(Map<String, String> headers){
            this.headers = headers;
            return this;
        }
        public JWTBuilder setPayload(Map<String, String> payload){
            this.payload = payload;
            return this;
        }

        public JWTBuilder setSignature(String signature){
            this.signature = signature;
            return this;
        }

        public JWTToken build(){
            JWTToken token = new JWTToken(this.headers, this.payload, this.signature);
            token.encodedHeaders = Base64.getUrlEncoder().encodeToString(jsonSD.toJson(this.headers).getBytes());
            token.encodedPayload = Base64.getUrlEncoder().encodeToString(jsonSD.toJson(this.payload).getBytes());
            return token;
        }
    }
}
