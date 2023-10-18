package ru.pablo.PersistanceImpl.Tables;

import ru.pablo.PersistanceImpl.Tables.Database.BookshelfServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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

    public Map<String, Object> getBook(Long bookId){
        Map<String, Object> result = new HashMap<>();
        try{
            PreparedStatement query = getBookStatement(bookId);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  result;
    }

    public void addBook(String title, String description, long shelf_id, long payload_id){
        try{
            PreparedStatement query = getAddBookStatement(title, description, shelf_id, payload_id);
            executeUpdate(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public PreparedStatement getBookStatement(long bookId) throws SQLException{
        String query =
                "SELECT id, title, description, payload_id FROM books " +
                "WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, bookId);
        return statement;
    }

    private PreparedStatement getAddBookStatement(String title, String description, long shelf_id, long payload_id) throws SQLException{
        String query =
                "INSERT INTO books(title, description, shelf_id, payload_id) " +
                "VALUES(?,?,?,?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, title);
        statement.setString(2, description);
        statement.setLong(3, shelf_id);
        statement.setLong(4, payload_id);
        return statement;
    }

    private PreparedStatement getBooksStatement(long shelfId) throws SQLException{
        String query =
                "SELECT books.id, books.title, books.description, books.shelf_id, books.payload_id FROM  books " +
                "WHERE books.shelf_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, shelfId);
        return statement;
    }

}
