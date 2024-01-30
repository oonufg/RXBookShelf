package ru.pablo.Spring.Security.JWT;

import ru.pablo.PersistanceImpl.Tables.RefreshTokensTable;
import ru.pablo.PersistanceImpl.Tables.UserTable;

public class JWTRefreshTokenRepository {
    private static RefreshTokensTable tokensTable;

    public void updateRefreshToken(long userId, RefreshToken refToken){
        tokensTable.updateRefreshToken(userId, refToken.getValue(), refToken.getExpiration());
    }

    public String refreshAccessToken(long userId, RefreshToken refToken){
        return null;
    }
}
