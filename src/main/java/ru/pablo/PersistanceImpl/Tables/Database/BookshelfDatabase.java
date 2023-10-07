package ru.pablo.PersistanceImpl.Tables.Database;

public class BookshelfDatabase extends PGDatabase{
    private String url = "jdbc:postgresql://localhost:5432/bookshelf_service";
    private String username = "postgres";
    private String password = "177013";

    public BookshelfDatabase(){
        initConnection(url, username, password);
    }
}
