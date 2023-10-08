package ru.pablo.PersistanceImpl.Repositories.Tables.Database;

public class BookshelfServiceTable extends PGTable {
    private String url = "jdbc:postgresql://localhost:5432/bookshelf_service";
    private String username = "postgres";
    private String password = "177013";

    public BookshelfServiceTable(){
        initConnection(url, username, password);
    }
}
