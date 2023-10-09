package ru.pablo.Domain.Entities;

import ru.pablo.Domain.Persistance.IShelfRepository;
import ru.pablo.PersistanceImpl.Repositories.ShelfRepository;

import java.util.List;

public class UserBookshelf extends Bookshelf{
    private boolean isOwner;
    private IShelfRepository shelfRepository;

    public UserBookshelf(long id, String title, boolean isOwner){
        super(title);
        this.id = id;
        this.isOwner = isOwner;
        this.shelfRepository = new ShelfRepository();
    }

    public long getId() {
        return id;
    }

    public Shelf getShelf(long shelfId){
        return null;
    }

    public void addShelf(Shelf shelf){
        shelfRepository.appendShelf(id, shelf);
    }

    public List<Shelf> getShelves(){
        return shelfRepository.getShelves(id);
    }

    public boolean isOwner(){
        return isOwner;
    }

}
