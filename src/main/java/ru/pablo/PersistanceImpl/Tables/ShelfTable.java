package ru.pablo.PersistanceImpl.Tables;

import ru.pablo.PersistanceImpl.Tables.Database.BookshelfServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShelfTable extends BookshelfServiceTable {

    public void addShelf(long bookshelfId, String title){
        try{
            PreparedStatement query = getAddShelfStatement(bookshelfId, title);
            executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Map<String, Object>> getShelves(long bookshelfId){
        List<Map<String, Object>> result = new LinkedList<>();
        try{
            PreparedStatement query = getShelvesStatement(bookshelfId);
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public Map<String, Object> getShelf(long bookshelfId){
        Map<String, Object> result = new HashMap<>();
        try{
            PreparedStatement query = getShelfStatement(bookshelfId);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void deleteShelf(long shelfId){
        try{
            PreparedStatement query = getDeleteShelfPrepareStatement(shelfId);
            executeUpdate(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void changeShelf(long shelfId, String title){
        try{
            PreparedStatement query = getChangeShelfStatement(shelfId, title);
            executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private PreparedStatement getDeleteShelfPrepareStatement(long shelfId) throws SQLException{
        String query =
                "DELETE FROM shelves " +
                "WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, shelfId);
        return  statement;
    }

    private PreparedStatement getShelfStatement(long shelfId) throws SQLException{
        String query =
                "SELECT * FROM shelves " +
                "WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, shelfId);
        return statement;
    }

    private PreparedStatement getShelvesStatement(long bookshelfId) throws SQLException{
        String query =
                "SELECT * FROM shelves " +
                "WHERE bookshelf_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1,bookshelfId);
        return statement;
    }

    private PreparedStatement getAddShelfStatement(long bookshelfID, String title) throws SQLException {
        String query = "INSERT INTO shelves(title, bookshelf_id) VALUES(?, ?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, title);
        statement.setLong(2,bookshelfID);
        return statement;
    }

    private PreparedStatement getChangeShelfStatement(long shelfId, String title) throws SQLException{
        String query =
                "UPDATE shelves " +
                "SET title = ? " +
                "WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, title);
        statement.setLong(2, shelfId);
        return  statement;
    }

}
