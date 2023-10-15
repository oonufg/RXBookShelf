package ru.pablo.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.PersistanceImpl.Repositories.BookshelfRepository;
import ru.pablo.Services.DTO.BookshelfDTO;
import ru.pablo.Services.DTO.ShelfDTO;

@PropertySource("classpath:application.yml")
@ComponentScan("ru.pablo")
@SpringBootApplication
public class RXBookShelf{
    public static void main(String[] args){
        SpringApplication.run(RXBookShelf.class, args);
    }
}