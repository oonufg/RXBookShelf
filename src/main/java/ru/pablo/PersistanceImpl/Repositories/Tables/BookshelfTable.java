package ru.pablo.PersistanceImpl.Repositories.Tables;

import ru.pablo.PersistanceImpl.Repositories.Tables.Database.BookshelfServiceTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookshelfTable  extends BookshelfServiceTable {
    public long createBookshelf(String title){
        try{
            PreparedStatement query = getCreateBookshelfStatement(title);
            ResultSet queryResult =  executeQuery(query);
            return (Long)resultSetToMap(queryResult).get("id");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return -1;
    }

    private PreparedStatement getCreateBookshelfStatement(String title) throws SQLException {
        String query =
                "INSERT INTO bookshelves(title) VALUES(?) " +
                "RETURNING id";
        PreparedStatement statement = getStatement(query);
        statement.setString(1,title);
        return statement;
    }

}
