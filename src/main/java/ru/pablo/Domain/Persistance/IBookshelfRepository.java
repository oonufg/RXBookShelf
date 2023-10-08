package ru.pablo.Domain.Persistance;

import ru.pablo.Domain.Entities.Bookshelf;

import java.util.List;

public interface IBookshelfRepository {
    Bookshelf getBookshelf(long bookshelfID);
    List<Bookshelf> getBookshelves(long userId);
    void appendBookshelf(long userId, Bookshelf bookshelfToAdd);
}
