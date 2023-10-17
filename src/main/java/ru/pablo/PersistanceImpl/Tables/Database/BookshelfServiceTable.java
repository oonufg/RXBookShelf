package ru.pablo.PersistanceImpl.Tables.Database;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BookshelfServiceTable extends PGTable {
    @Value("${pgsql.url}")
    private String url;
    @Value("${pgsql.username}")
    private String username;
    @Value("${pgsql.password}")
    private String password;

    public BookshelfServiceTable(){
        postsInit();
    }
    @PostConstruct
    private void  postsInit(){
        initConnection(url, username, password);
    }
}
