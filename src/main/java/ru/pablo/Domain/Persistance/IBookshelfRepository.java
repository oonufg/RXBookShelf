package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyExistsException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;

import java.util.List;

public interface IBookshelfRepository {
    Bookshelf getBookshelf(long bookshelfID) throws BookshelfNotExistException;
    List<Bookshelf> getBookshelves(long userId);
    void appendBookshelf(long userId, Bookshelf bookshelfToAdd) throws BookshelfAlreadyExistsException;
    void deleteBookshelf(long userId, long bookshelfId) throws UserNotHaveAccessException, BookshelfNotExistException;
    void changeBookshelf(long userID, Bookshelf bookshelfToChange) throws UserNotHaveAccessException, BookshelfNotExistException;
}
