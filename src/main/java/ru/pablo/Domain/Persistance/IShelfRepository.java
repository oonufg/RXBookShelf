package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Shelf;
import ru.pablo.Domain.Exceptions.Shelf.ShelfAlreadyExistException;
import ru.pablo.Domain.Exceptions.Shelf.ShelfNotExistsException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;

import java.util.List;

public interface IShelfRepository {
    List<Shelf> getShelves(long bookshelfId);
    Shelf getShelf(long shelfId) throws ShelfNotExistsException;
    void appendShelf(long bookshelfId, Shelf shelfToAdd) throws ShelfAlreadyExistException;

    void deleteShelf(long shelfId) throws ShelfNotExistsException;

    void changeShelf(long userID, Shelf shelfToChange) throws UserNotHaveAccessException, ShelfNotExistsException;

}


