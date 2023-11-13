package ru.pablo.Domain.Entities;

import ru.pablo.Domain.Exceptions.Shelf.ShelfAlreadyExistException;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.PersistanceImpl.Repositories.ShelfRepository;

import java.util.List;

public class Bookshelf {
    private Long id;
    private String title;
    private String ownerNickname;
    private ShelfRepository repository;

    public Bookshelf(Long id, String title){
        this.id = id;
        this.title = title;
        this.repository = new ShelfRepository();
    }

    public Bookshelf(Long id, String title, String ownerNickname){
        this.id = id;
        this.title = title;
        this.ownerNickname = ownerNickname;
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

    public String getOwnerNickname() {
        return ownerNickname;
    }

    public List<Shelf> getShelves(){
        return repository.getShelves(id);
    }

    public void addShelf(Shelf shelf) throws ShelfAlreadyExistException {
        repository.appendShelf(id, shelf);
    }

    public void deleteShelf(long shelfID) throws ShelfNotExistsException {
        repository.deleteShelf(shelfID);
    }

}
