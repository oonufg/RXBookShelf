package ru.pablo.PersistanceImpl.Mappers;

import ru.pablo.Domain.Entities.Book;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookMapper {
    public static Book mapBook(Map<String, Object> unmappedBook){
        return new Book(
                (Long)unmappedBook.get("id"),
                (String)unmappedBook.get("title"),
                (String) unmappedBook.get("description"),
                (Long) unmappedBook.get("payload_id")
        );
    }

    public static List<Book> mapBookList(List<Map<String, Object>> unmappedBooks){
        List<Book> result = new LinkedList<>();
        for(Map<String, Object> unmappedBook: unmappedBooks){
            result.add(mapBook(unmappedBook));
        }
        return result;
    }
}