package ru.pablo.Spring.Security.JWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pablo.Spring.Security.ApplicationUser;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

@Service
public class JWTService {
    @Value("${jwt.access.lifetime}")
    private Integer accessTokenLifetime;
    @Value("${jwt.secret}")
    private String secretKey;
    public JWTToken generateAccessToken(ApplicationUser user){

        long creationTime = Instant.now().getEpochSecond();
        long expriredTime = creationTime + accessTokenLifetime * 10000000000l;

        Map<String, String> headers = Map.of(
                "typ","JWT",
                "alg","HS256"
        );
        Map<String, String> payload = Map.of(
                "userId", user.getId().toString(),
                "authorities", user.getAuthorities().toString(),
                "iap",String.valueOf(creationTime),
                "exp",String.valueOf(expriredTime)
        );

        JWTToken token = new JWTToken.JWTBuilder()
                .setHeaders(headers)
                .setPayload(payload)
                .setSignature(JWTUtils.signToken(headers, payload, secretKey))
                .build();

        return token;
    }

    public String generateRefreshToken(ApplicationUser user){

        return null;
    }

    public JWTToken refreshAccessToken(ApplicationUser user ,String refreshToken){
        if(isRefreshTokenValid(refreshToken)){
            return generateAccessToken(user);
        }
        return null;
    }

    public boolean isAccessTokenValid(JWTToken token){
        if(Objects.equals(JWTUtils.signToken(token.getEncodedHeaders(), token.getEncodedPayload(), secretKey), token.getSignature())){
            return Long.parseLong((String)token.getPayload().get("exp")) >= Instant.now().getEpochSecond();
        }
        return false;
    }

    public boolean isRefreshTokenValid(String token){
        return true;
    }



}
