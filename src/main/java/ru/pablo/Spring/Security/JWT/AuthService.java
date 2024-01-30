package ru.pablo.Spring.Security.JWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.pablo.PersistanceImpl.Tables.RefreshTokensTable;
import ru.pablo.Spring.Security.ApplicationUser;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Service
public class AuthService {
    @Value("${jwt.access.lifetime}")
    private Integer accessTokenLifetime;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.refresh.lifetime}")
    private Integer refreshTokenLifetime;

    private static final RefreshTokensTable refTokens;

    static{
        refTokens = new RefreshTokensTable();
    }

    public JWTToken generateAccessToken(ApplicationUser user){

        long creationTime = Instant.now().getEpochSecond();
        long expriredTime = creationTime + accessTokenLifetime;

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

    public RefreshToken generateRefreshToken(ApplicationUser user){
        RefreshToken token = new RefreshToken(
                generateRandomString(64),
                new Timestamp(Instant.now().getEpochSecond() + refreshTokenLifetime)
                );
        refTokens.updateRefreshToken(
                user.getId(),
                token.getValue(),
                token.getExpiration()
        );
        return token;
    }

    public JWTToken refreshAccessToken(ApplicationUser user , RefreshToken refreshToken){
        if(isRefreshTokenValid(user.getId(), refreshToken)){
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


    public boolean isRefreshTokenValid(long userId, RefreshToken token){
        RefreshToken currentUserToken = refTokens.getCurrentUserRefreshToken(userId);
        return currentUserToken.getExpiration().getTime() > Instant.now().getEpochSecond() && currentUserToken.equals(token);
    }
    private String generateRandomString(int charValue) {
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(charValue)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
}
