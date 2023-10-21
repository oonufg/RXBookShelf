package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;

import java.util.List;

public interface IShelfRepository {
    List<Shelf> getShelves(long bookshelfId);
    Shelf getShelf(long shelfId);
    void appendShelf(long bookshelfId, Shelf shelfToAdd);

    void deleteShelf(long shelfId);

    void changeShelf(long userID, Shelf shelfToChange) throws UserNotHaveAccessException, ShelfNotExistsException;

}


