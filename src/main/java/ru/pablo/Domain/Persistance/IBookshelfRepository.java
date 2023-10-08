package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Entities.UserBookshelf;

import java.util.List;

public interface IBookshelfRepository {
    UserBookshelf getBookshelf(long userId, long bookshelfID);
    List<UserBookshelf> getBookshelves(long userId);
    void appendBookshelf(long userId, Bookshelf bookshelfToAdd);
}
