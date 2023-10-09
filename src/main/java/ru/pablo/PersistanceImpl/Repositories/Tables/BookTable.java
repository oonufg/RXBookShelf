package ru.pablo.PersistanceImpl.Repositories.Tables;

import ru.pablo.PersistanceImpl.Repositories.Tables.Database.BookshelfServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookTable extends BookshelfServiceTable {

    public List<Map<String, Object>> getBooks(long shelfId){
        List<Map<String, Object>> result = new LinkedList<>();
        try {
            PreparedStatement query = getBooksStatement(shelfId);
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void addBook(){

    }

    private PreparedStatement getBooksStatement(long shelfId) throws SQLException{
        String query =
                "SELECT books.id, books.title, books.description, mediafiles.uid FROM  books\n" +
                "JOIN mediafiles ON books.payload_id = mediafiles.id\n" +
                "WHERE books.shelf_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, shelfId);
        return statement;
    }

}
