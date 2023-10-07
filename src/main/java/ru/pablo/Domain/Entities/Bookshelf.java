package ru.pablo.Domain.Entities;

import ru.pablo.Domain.Persistance.IShelfRepository;

import java.util.List;

public class Bookshelf {
    private long id;
    private String title;
    private IShelfRepository shelfRepository;

    public Bookshelf(long id, String title){
        this.id = id;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Shelf getShelf(long shelfId){
        return null;
    }

    public List<Shelf> getShelves(){
        return null;
    }

}
