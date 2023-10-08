package ru.pablo.PersistanceImpl.Repositories.Tables;

import ru.pablo.PersistanceImpl.Repositories.Tables.Database.BookshelfServiceTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UsersBookshelvesTable extends BookshelfServiceTable {
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

    public void subscribeToBookshelf(long userId, long bookshelfID, boolean isOwner){
        try{
            PreparedStatement statement = getAddBookshelfToTableStatement(userId, bookshelfID, isOwner);
            executeUpdate(statement);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private PreparedStatement getAddBookshelfToTableStatement(long userId, long bookshelfId, boolean isOwner) throws SQLException{
        String query = "INSERT INTO users_bookshelves(user_id, bookshelf_id, isowner) VALUES(?, ?, ?)";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        statement.setLong(2, bookshelfId);
        statement.setBoolean(3, isOwner);
        return statement;

    }

    private PreparedStatement getUserBookShevlesStatement(long userId) throws SQLException {
        String query =
                "SELECT bookshelves.id, bookshelves.title FROM users_bookshelves " +
                "LEFT JOIN bookshelves ON users_bookshelves.bookshelf_id = bookshelves.id " +
                "WHERE users_bookshelves.user_id = ?";

        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        return statement;
    }


}
