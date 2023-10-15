package ru.pablo.PersistanceImpl.Mappers;

import ru.pablo.Domain.Entities.Bookshelf;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BookshelfMapper {
    public static Bookshelf mapBookshelf(Map<String, Object> unmappedBookshelf){
        Bookshelf bookshelf = new Bookshelf(
                (Long)unmappedBookshelf.get("id"),
                (String)unmappedBookshelf.get("title")
        );
        return bookshelf;
    }

    public static List<Bookshelf> mapListUserBookshelf(List<Map<String, Object>> unmappedBookshelves){
        List<Bookshelf> result = new LinkedList<>();
        for(Map<String, Object> unmappedBookshelf: unmappedBookshelves){
            result.add(mapBookshelf(unmappedBookshelf));
        }
        return result;
    }

}
