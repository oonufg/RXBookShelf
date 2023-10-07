package ru.pablo.PersistanceImpl.Tables;

import ru.pablo.PersistanceImpl.Tables.Database.BookshelfDatabase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTable extends BookshelfDatabase {
    public List<Map<String, Object>> getUsers(){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            PreparedStatement query = getUsersStatement();
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Map<String, Object> getUser(long userId){
        Map<String, Object> result = new HashMap<>();
        try {
            PreparedStatement query = getUserStatement(userId);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void addUser(String nickname){
        try{
            PreparedStatement statement = getAddUserStatement(nickname);
            executeUpdate(statement);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private PreparedStatement getAddUserStatement(String nickname) throws SQLException {
        String query =
                "INSERT INTO users(nickname) " +
                "VALUES(?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, nickname);
        return statement;
    }

    private PreparedStatement getUsersStatement() throws SQLException {
        String query = "SELECT * FROM users";
        PreparedStatement statement = getStatement(query);
        return statement;
    }

    private PreparedStatement getUserStatement(long userId) throws SQLException{
        String query =
                "SELECT * FROM users " +
                "WHERE id = ?"
                ;
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        return statement;
    }

}
