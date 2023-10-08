package ru.pablo.PersistanceImpl.Mappers;

import ru.pablo.Domain.Entities.Shelf;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShelfMapper {
    public static Shelf mapShelf(Map<String, Object> unmappedShelf){
        Shelf shelf = new Shelf(
                (Long)unmappedShelf.get("id"),
                (String)unmappedShelf.get("title")
        );
        return shelf;
    }

    public static List<Shelf> mapShelfList(List<Map<String, Object>> unmappedShelfList){
        List<Shelf> result = new LinkedList<>();
        for(Map<String, Object> unmappedShelf: unmappedShelfList){
            result.add(mapShelf(unmappedShelf));
        }
        return result;
    }

}
