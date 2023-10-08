package ru.pablo.PersistanceImpl.Mappers;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Entities.UserBookshelf;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookshelfMapper {
    public static UserBookshelf mapBookshelf(Map<String, Object> unmappedBookshelf){
        UserBookshelf bookshelf = new UserBookshelf(
                (Long)unmappedBookshelf.get("id"),
                (String)unmappedBookshelf.get("title"),
                (Boolean)unmappedBookshelf.get("isowner")
        );
        return bookshelf;
    }

    public static List<Bookshelf> mapListBookshelf(List<Map<String, Object>> unmappedBookshelves){
        List<Bookshelf> result = new LinkedList<>();
        for(Map<String, Object> unmappedBookshelf: unmappedBookshelves){
            result.add(mapBookshelf(unmappedBookshelf));
        }
        return result;
    }

}
