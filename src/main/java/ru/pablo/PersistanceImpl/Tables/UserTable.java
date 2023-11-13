package ru.pablo.PersistanceImpl.Tables;

import ru.pablo.PersistanceImpl.Tables.Database.BookshelfServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTable extends BookshelfServiceTable {
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

    public Map<String, Object> getUserById(long userId){
        Map<String, Object> result = new HashMap<>();
        try {
            PreparedStatement query = getUserByIdStatement(userId);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Map<String, Object> getUserByNickname(String nickname){
        Map<String, Object> result = new HashMap<>();
        try {
            PreparedStatement query = getUserByNicknameStatement(nickname);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean isUserExists(String nickname){
        boolean result = false;
        try{
            PreparedStatement query = getIsUserExists(nickname);
            result = isRowExist(executeQuery(query));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void addUser(String nickname, String password){
        try{
            PreparedStatement statement = getAddUserStatement(nickname, password);
            executeUpdate(statement);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private PreparedStatement getAddUserStatement(String nickname, String password) throws SQLException {
        String query =
                "INSERT INTO users(nickname, password) " +
                "VALUES(?, ?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, nickname);
        statement.setString(2, password);
        return statement;
    }

    private PreparedStatement getUsersStatement() throws SQLException {
        String query = "SELECT * FROM users";
        PreparedStatement statement = getStatement(query);
        return statement;
    }

    private PreparedStatement getUserByIdStatement(long userId) throws SQLException{
        String query =
                "SELECT * FROM users " +
                "WHERE id = ?";

        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        return statement;
    }

    private PreparedStatement getUserByNicknameStatement(String nickname) throws SQLException{
        String query =
                "SELECT * FROM users " +
                "WHERE nickname = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, nickname);
        return statement;
    }

    private PreparedStatement getIsUserExists(String nickname) throws SQLException{
        String query = "SELECT EXISTS (SELECT true from users where nickname = ?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, nickname);
        return statement;
    }

}
