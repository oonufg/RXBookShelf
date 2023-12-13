package ru.pablo.Spring.Security.JWT;

import ru.pablo.PersistanceImpl.Tables.UserTable;

public class JWTTokenRepository {
    private static UserTable userTable;

    public void updateRefreshToken(long userid, String refreshToken){
        
    }

    public String refreshAccessToken(long userId, String refreshToken){
        return null;
    }

    public void dropAllUserTokens(){

    }

}
