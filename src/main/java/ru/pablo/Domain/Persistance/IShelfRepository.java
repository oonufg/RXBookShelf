package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Shelf;

import java.util.List;

public interface IShelfRepository {
    List<Shelf> getShelves(long bookshelfId);
    Shelf getShelf(long shelfId);
    void appendShelf(long bookshelfId, Shelf shelfToAdd);

}
