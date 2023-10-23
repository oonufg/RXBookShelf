package ru.pablo.PersistanceImpl.Tables;

import ru.pablo.PersistanceImpl.Tables.Database.BookshelfServiceTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookshelvesSubscribesTable extends BookshelfServiceTable {
    public void subscribeToBookshelf(long userId, long bookshelfID){
        try{
            PreparedStatement query = getSubscribeToBookshelfStatement(userId, bookshelfID);
            executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void unsubscribeFromBookshelf(long userId, long bookshelfId){
        try{
            PreparedStatement query = getUnsubscribeFromBookshelfStatement(userId, bookshelfId);
            executeUpdate(query);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean isUserSubscriberOfBookshelf(long userId, long bookshelfId){
        boolean result = false;
        try{
            PreparedStatement query = getIsUserSubscriberOfBookshelf(userId, bookshelfId);
            ResultSet queryResult = executeQuery(query);
            result = isRowExist(queryResult);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public List<Map<String, Object>> getSubscribeBookshelves(long userId){
        List<Map<String, Object>> result = new LinkedList<>();
        try{
            PreparedStatement query = getUserSubscribeanBookshelves(userId);
            ResultSet queryResult = executeQuery(query);
            result.addAll(resutlSetToList(queryResult));
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    private PreparedStatement getSubscribeToBookshelfStatement(long userId, long bookshelfId) throws SQLException{
        String query = "INSERT INTO bookshelves_subscribes(user_id, bookshelf_id) VALUES(?, ?)";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        statement.setLong(2, bookshelfId);
        return statement;
    }

    private PreparedStatement getUnsubscribeFromBookshelfStatement(long userId, long bookshelfId) throws SQLException{
        String query =
                "DELETE FROM bookshelves_subscribes " +
                "WHERE user_id = ? AND bookshelf_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        statement.setLong(2, bookshelfId);
        return statement;
    }

    private PreparedStatement getUserSubscribeanBookshelves(long userId) throws SQLException{
        String query =
                "SELECT bookshelves.id, bookshelves.title FROM bookshelves_subscribes " +
                "JOIN bookshelves ON bookshelves_subscribes.bookshelf_id = bookshelves.id " +
                "WHERE bookshelves_subscribes.user_id = ?";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        return statement;
    }

    private PreparedStatement getIsUserSubscriberOfBookshelf(long userId, long bookshelfId) throws SQLException{
        String query = "SELECT EXISTS (SELECT true from bookshelves_subscribes where user_id = ? and bookshelf_id = ? )";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        statement.setLong(2, bookshelfId);
        return statement;
    }

}