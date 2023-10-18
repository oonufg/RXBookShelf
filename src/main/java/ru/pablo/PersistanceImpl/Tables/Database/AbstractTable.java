package ru.pablo.PersistanceImpl.Tables.Database;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class AbstractTable {
    protected static Connection connection;


    protected PreparedStatement getStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    protected int executeUpdate(PreparedStatement statement) throws SQLException{
        int result = statement.executeUpdate();
        System.out.println(result);
        return result;
    }

    protected ResultSet executeQuery(PreparedStatement statement) throws SQLException{
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();
        return resultSet;
    }

    protected List<Map<String, Object>> resutlSetToList(ResultSet resultSet){
        List<Map<String,Object>> result = new LinkedList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while(resultSet.next()){
                Map<String, Object> currentRecord = resultSetToMapMapper(resultSet);
                result.add(currentRecord);
            }
        }catch(SQLException exception){
            System.out.println("Error" + exception.getMessage());
        }
        return result;
    }

    protected Map<String, Object> resultSetToMap(ResultSet resultSet){
        Map<String, Object> result = new HashMap<>();
        try{
            if(resultSet.next()) {
                result.putAll(resultSetToMapMapper(resultSet));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private Map<String, Object> resultSetToMapMapper(ResultSet resultSet){
        Map<String, Object> result = new HashMap<>();
        try{
            ResultSetMetaData metaData = resultSet.getMetaData();
            for(int i = 0; i < metaData.getColumnCount();i++) {
                String columnName = metaData.getColumnName(i+1);
                Object columnValue = resultSet.getObject(i+1);
                result.put(columnName,columnValue);
            }
        }catch(SQLException exception){
            System.out.println(exception.getMessage());
        }
        return result;
    }

    protected boolean isResultSetEmpty(ResultSet set) throws SQLException{
        boolean result = true;
        if(set.next()){
            result = false;
        }
        return result;
    }

    protected boolean isRowExist(ResultSet set) throws SQLException{
        boolean result = false;
        if(set.next()){
            result = set.getBoolean(1);
        }
        return result;
    }

    protected void setupConnection(String url, String username, String password){
        initJDBCDriver();
        initConnection(url,username,password);
    }

    protected abstract void initJDBCDriver();

    protected abstract void initConnection(String url,String username, String password);
}