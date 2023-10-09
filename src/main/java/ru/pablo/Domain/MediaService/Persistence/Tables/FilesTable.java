package ru.pablo.Domain.MediaService.Persistence.Tables;


import ru.pablo.PersistanceImpl.Repositories.Tables.Database.BookshelfServiceTable;

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
            queryResult.next();
            result.putAll(resultSetToMap(queryResult));
        }catch(SQLException e){
            System.out.println("->" + e.getMessage());
        }
        return result;
    }

    public void saveFileInfo(String uit, String title, String extension){
        try{
            PreparedStatement query = getSaveFileInfoStatement(uit, title, extension);
            executeQuery(query);
        }catch(SQLException e){
            System.out.println("->" +e.getMessage());
        }
    }

    private PreparedStatement getSaveFileInfoStatement(String uid, String title, String extension) throws SQLException {
        String query = "INSERT INTO mediafiles VALUES(?, ?, ?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, uid);
        statement.setString(2, title);
        statement.setString(3, extension);
        return statement;
    }

    private PreparedStatement getFileByUID(String uid) throws SQLException{
        String query = "SELECT * FROM mediafiles WHERE uid = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, uid);
        return statement;
    }

}
