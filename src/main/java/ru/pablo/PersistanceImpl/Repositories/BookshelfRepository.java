package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyExistsException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.Domain.Persistance.IBookshelfRepository;
import ru.pablo.PersistanceImpl.Mappers.BookshelfMapper;
import ru.pablo.PersistanceImpl.Tables.BookshelfTable;
import ru.pablo.PersistanceImpl.Tables.UsersBookshelvesTable;

import java.util.List;

public class BookshelfRepository implements IBookshelfRepository {
    private static BookshelfTable bookshelfTable;
    private static UsersBookshelvesTable usersBookshelvesTable;

    static {
        bookshelfTable = new BookshelfTable();
        usersBookshelvesTable = new UsersBookshelvesTable();
    }

    @Override
    public Bookshelf getBookshelf(long bookshelfID) throws BookshelfNotExistException{
        if(bookshelfTable.isBookshelfExists(bookshelfID)) {
            return BookshelfMapper.mapBookshelf(bookshelfTable.getUserBookshelf(bookshelfID));
        }else{
            throw new BookshelfNotExistException();
        }
    }

    @Override
    public List<Bookshelf> getBookshelves(long userId) {
        return BookshelfMapper.mapListUserBookshelf(bookshelfTable.getUserBookshelves(userId));
    }

    @Override
    public void appendBookshelf(long userID, Bookshelf bookshelfToAdd) throws BookshelfAlreadyExistsException{
        if(!bookshelfTable.isUserHaveSameBookshelf(userID, bookshelfToAdd.getTitle())) {
            bookshelfTable.createBookshelf(userID, bookshelfToAdd.getTitle());
        }else{
            throw new BookshelfAlreadyExistsException();
        }
    }

    @Override
    public void deleteBookshelf(long userId, long bookshelfId) throws UserNotHaveAccessException{
        if(bookshelfTable.isUserOwnerOfBookshelf(userId, bookshelfId)){
            bookshelfTable.deleteBookshelf(bookshelfId);
        }else{
            throw new UserNotHaveAccessException();
        }
    }

    @Override
    public void changeBookshelf(long userID, Bookshelf bookshelfToChange) {
        bookshelfTable.changeBookshelf(bookshelfToChange.getId(), bookshelfToChange.getTitle());
    }

}