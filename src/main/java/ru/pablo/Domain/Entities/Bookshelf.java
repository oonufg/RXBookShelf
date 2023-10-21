package ru.pablo.Domain.Entities;

import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.PersistanceImpl.Repositories.ShelfRepository;

import java.util.List;

public class Bookshelf {
    private Long id;
    private String title;
    private ShelfRepository repository;

    public Bookshelf(Long id, String title){
        this.id = id;
        this.title = title;
        this.repository = new ShelfRepository();
    }

    public Bookshelf(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public long getId(){
        return id;
    }

    public List<Shelf> getShelves(){
        return repository.getShelves(id);
    }

    public void addShelf(Shelf shelf){
        repository.appendShelf(id, shelf);
    }

    public void deleteShelf(long shelfID)  {
        repository.deleteShelf(shelfID);
    }

}
