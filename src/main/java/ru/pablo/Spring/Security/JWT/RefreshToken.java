package ru.pablo.Spring.Security.JWT;

import java.sql.Timestamp;
import java.util.Objects;

public class RefreshToken {
    private String value;
    private Timestamp expiration;

    public RefreshToken(String token, Timestamp expiration){
        this.value = token;
        this.expiration = expiration;
    }

    public String getValue() {
        return value;
    }

    public Timestamp getExpiration() {
        return expiration;
    }

    @Override
    public boolean equals(Object obj) {
        RefreshToken tokenToEqual = (RefreshToken) obj;
        return Objects.equals(tokenToEqual.value, tokenToEqual.value);
    }
}
