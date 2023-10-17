package ru.pablo.Domain.MediaService.Persistence.Tables;


import ru.pablo.PersistanceImpl.Tables.Database.BookshelfServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FilesTable extends BookshelfServiceTable {

    public Map<String,Object> getFileInfoByUID(String uid){
        Map<String, Object> result = new HashMap<>();
        try{
            PreparedStatement query = getFileByUID(uid);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));
        }catch(SQLException e){
            System.out.println("->" + e.getMessage());
        }
        return result;
    }
    public Map<String,Object> getFileInfoByID(long id){
        Map<String, Object> result = new HashMap<>();
        try{
            PreparedStatement query = getFileByID(id);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));
        }catch(SQLException e){
            System.out.println("->" + e.getMessage());
        }
        return result;
    }

    public long saveFileInfo(String uit, String title, String extension){
        long result = -1;
        try{
            PreparedStatement query = getSaveFileInfoStatement(uit, title, extension);
            ResultSet queryResult = executeQuery(query);
            Map<String, Object> unmappedResult = resultSetToMap(queryResult);
            result = (Long)unmappedResult.get("id");
        }catch(SQLException e){
            System.out.println("->" +e.getMessage());
        }
        return result;
    }

    private PreparedStatement getSaveFileInfoStatement(String uid, String title, String extension) throws SQLException {
        String query =
                "INSERT INTO mediafiles(uid, title, extension) VALUES(?, ?, ?) " +
                "RETURNING id";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, uid);
        statement.setString(2, title);
        statement.setString(3, extension);
        return statement;
    }

    private PreparedStatement getFileByID(long id) throws SQLException{
        String query = "SELECT * FROM mediafiles WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,id);
        return statement;
    }

    private PreparedStatement getFileByUID(String uid) throws SQLException{
        String query = "SELECT * FROM mediafiles WHERE uid = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, uid);
        return statement;
    }

}
