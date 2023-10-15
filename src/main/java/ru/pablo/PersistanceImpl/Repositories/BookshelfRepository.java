package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Persistance.IBookshelfRepository;
import ru.pablo.PersistanceImpl.Mappers.BookshelfMapper;
import ru.pablo.PersistanceImpl.Repositories.Tables.BookshelfTable;
import ru.pablo.PersistanceImpl.Repositories.Tables.UsersBookshelvesTable;

import java.util.List;

public class BookshelfRepository implements IBookshelfRepository {
    private static BookshelfTable bookshelfTable;
    private static UsersBookshelvesTable usersBookshelvesTable;

    static {
        bookshelfTable = new BookshelfTable();
        usersBookshelvesTable = new UsersBookshelvesTable();
    }

    @Override
    public Bookshelf getBookshelf(long bookshelfID) {
        return BookshelfMapper.mapBookshelf(bookshelfTable.getUserBookshelf(bookshelfID));
    }

    @Override
    public List<Bookshelf> getBookshelves(long userId) {
        return BookshelfMapper.mapListUserBookshelf(bookshelfTable.getUserBookshelves(userId));
    }

    @Override
    public void appendBookshelf(long userID, Bookshelf bookshelfToAdd) {
        bookshelfTable.createBookshelf(userID, bookshelfToAdd.getTitle());
    }

    @Override
    public void deleteBookshelf(long bookshelfId) {
        bookshelfTable.deleteBookshelf(bookshelfId);
    }

    @Override
    public void changeBookshelf(long userID, Bookshelf bookshelfToChange) {
        bookshelfTable.changeBookshelf(bookshelfToChange.getId(), bookshelfToChange.getTitle());
    }

}