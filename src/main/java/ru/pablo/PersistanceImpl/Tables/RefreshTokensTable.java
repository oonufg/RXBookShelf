package ru.pablo.PersistanceImpl.Tables;

import ru.pablo.PersistanceImpl.Tables.Database.BookshelfServiceTable;
import ru.pablo.Spring.Security.JWT.RefreshToken;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;

public class RefreshTokensTable extends BookshelfServiceTable {
    public void updateRefreshToken(long userId, String token, Timestamp expiredTime){
        try{
            PreparedStatement query = getUpdateRefreshTokenStatement(userId, token, expiredTime);
            executeQuery(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public RefreshToken getCurrentUserRefreshToken(long userId){
        RefreshToken result = null;
        try{
            PreparedStatement query = getCurrentUserRefreshTokenStatement(userId);
            Map<String,Object> queryResult = resultSetToMap(executeQuery(query));
            result = new RefreshToken(
                    (String)queryResult.get("token"),
                    (Timestamp) queryResult.get("expiration")
            );
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean isRefreshTokenExists(long userId){
        return true;
    }

    private PreparedStatement getCurrentUserRefreshTokenStatement(long userId) throws SQLException{
        String query =
                "SELECT token, expiration FROM refresh_tokens" +
                "WHERE user_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        return statement;
    }

    private PreparedStatement getUpdateRefreshTokenStatement(long userId, String token, Timestamp timestamp) throws SQLException {
        String query =
                "UPDATE refresh_tokens " +
                "SET token = ?, expiration = ? " +
                "WHERE user_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, token);
        statement.setTimestamp(2, timestamp);
        statement.setLong(3, userId);

        return statement;
    }

}
