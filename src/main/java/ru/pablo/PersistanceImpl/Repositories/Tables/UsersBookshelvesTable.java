package ru.pablo.PersistanceImpl.Repositories.Tables;

import ru.pablo.PersistanceImpl.Repositories.Tables.Database.BookshelfServiceTable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UsersBookshelvesTable extends BookshelfServiceTable {
    public void subscribeToBookshelf(long userId, long bookshelfID, boolean isOwner){
        try{
            PreparedStatement statement = getAddBookshelfToTableStatement(userId, bookshelfID, isOwner);
            executeUpdate(statement);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private PreparedStatement getAddBookshelfToTableStatement(long userId, long bookshelfId, boolean isOwner) throws SQLException{
        String query = "INSERT INTO users_subsbookshelves(user_id, bookshelf_id, isowner) VALUES(?, ?, ?)";
        PreparedStatement statement = getStatement(query);
        statement.setLong(1, userId);
        statement.setLong(2, bookshelfId);
        statement.setBoolean(3, isOwner);
        return statement;

    }




}
