package ru.pablo.PersistanceImpl.Tables;

import ru.pablo.PersistanceImpl.Tables.Database.BookshelfServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookshelfTable  extends BookshelfServiceTable {

    public List<Map<String,Object>> getUserBookshelves(long userId){
        List<Map<String,Object>> result = new LinkedList<>();
        try{
            PreparedStatement query = getUserBookShevlesStatement(userId);
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public Map<String,Object> getUserBookshelf(long bookshelfId){
        Map<String,Object> result = new HashMap<>();
        try{
            PreparedStatement query = getUserBookshelfStatement(bookshelfId);
            ResultSet queryResult = executeQuery(query);
            result.putAll(resultSetToMap(queryResult));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public long createBookshelf(long userId, String title){
        try{
            PreparedStatement query = getCreateBookshelfStatement(userId, title);
            ResultSet queryResult =  executeQuery(query);
            return (Long)resultSetToMap(queryResult).get("id");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public void deleteBookshelf(long bookshelfId){
        try{
            PreparedStatement query = getDeleteBookshelfStatement(bookshelfId);
            executeUpdate(query);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void changeBookshelf(long bookshelfId, String title){
        try{
            PreparedStatement query = getChangeBookshelfStatement(bookshelfId, title);
            executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isBookshelfExists(long bookshelfId){
        boolean result = false;
        try{
            PreparedStatement query = getIsBookshelfExistsStatement(bookshelfId);
            ResultSet queryResult = executeQuery(query);
            result = isRowExist(queryResult);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean isUserOwnerOfBookshelf(long userId, long bookshelfId){
        boolean result = false;
        try{
            PreparedStatement query = getIsUserBookshelfOwnerStatement(userId, bookshelfId);
            ResultSet queryResult = executeQuery(query);
            result = isRowExist(queryResult);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public boolean isUserHaveSameBookshelf(long userId, String title){
        boolean result = false;
        try{
            PreparedStatement query = getIsUserHaveSameBookshelfStatement(userId, title);
            ResultSet queryResult = executeQuery(query);
            result = isRowExist(queryResult);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private PreparedStatement getChangeBookshelfStatement(long bookshelfId, String title) throws SQLException{
        String query =
                "UPDATE bookshelves " +
                "SET title = ? " +
                "WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, title);
        statement.setLong(2, bookshelfId);
        return  statement;
    }

    private PreparedStatement getDeleteBookshelfStatement(long bookshelfId) throws SQLException{
        String query = "DELETE FROM bookshelves WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, bookshelfId);
        return statement;
    }

    private PreparedStatement getCreateBookshelfStatement(long userId, String title) throws SQLException {
        String query =
                "INSERT INTO bookshelves(title, owner_id) VALUES(?, ?) " +
                "RETURNING id";
        PreparedStatement statement = getStatement(query);
        statement.setString(1,title);
        statement.setLong(2, userId);
        return statement;
    }

    private PreparedStatement getUserBookshelfStatement(long bookShelfId) throws SQLException{
        String query =
                "SELECT id, title FROM bookshelves " +
                "WHERE id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, bookShelfId);
        return statement;
    }

    private PreparedStatement getUserBookShevlesStatement(long userId) throws SQLException {
        String query =
                "SELECT bookshelves.id, bookshelves.title FROM bookshelves " +
                "WHERE bookshelves.owner_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        return statement;
    }

    private PreparedStatement getIsBookshelfExistsStatement(long id) throws SQLException{
        String query = "SELECT EXISTS (SELECT true from bookshelves where id = ?)";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, id);
        return statement;
    }

    private PreparedStatement getIsUserBookshelfOwnerStatement(long userId, long bookshelfId) throws SQLException{
        String query = "SELECT EXISTS (SELECT true FROM bookshelves WHERE id = ? AND owner_id = ?)";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, bookshelfId);
        statement.setLong(2, userId);
        return statement;
    }

    private PreparedStatement getIsUserHaveSameBookshelfStatement(long userid, String title) throws SQLException{
        String query = "SELECT EXISTS (SELECT true FROM bookshelves WHERE title = ? AND owner_id = ?)";
        PreparedStatement statement = getStatement(query);
        statement.setString(1, title);
        statement.setLong(2, userid);
        return statement;
    }

}
