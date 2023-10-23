package ru.pablo.PersistanceImpl.Repositories;

import ru.pablo.Domain.Entities.Bookshelf;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfAlreadyExistsException;
import ru.pablo.Domain.Exceptions.Bookshelf.BookshelfNotExistException;
import ru.pablo.Domain.Exceptions.User.UserNotHaveAccessException;
import ru.pablo.Domain.Persistance.IBookshelfRepository;
import ru.pablo.PersistanceImpl.Mappers.BookshelfMapper;
import ru.pablo.PersistanceImpl.Tables.BookshelfTable;
import ru.pablo.PersistanceImpl.Tables.BookshelvesSubscribesTable;

import java.util.List;

public class BookshelfRepository implements IBookshelfRepository {
    private static BookshelfTable bookshelfTable;
    private static BookshelvesSubscribesTable usersBookshelvesTable;

    static {
        bookshelfTable = new BookshelfTable();
        usersBookshelvesTable = new BookshelvesSubscribesTable();
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
        return BookshelfMapper.mapListBookshelf(bookshelfTable.getUserBookshelves(userId));
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
    public void deleteBookshelf(long userId, long bookshelfId) throws UserNotHaveAccessException, BookshelfNotExistException{
        if(bookshelfTable.isUserOwnerOfBookshelf(userId, bookshelfId)){
            if(bookshelfTable.isBookshelfExists(bookshelfId)) {
                bookshelfTable.deleteBookshelf(bookshelfId);
            }else{
                throw new BookshelfNotExistException();
            }
        }else{
            throw new UserNotHaveAccessException();
        }
    }

    @Override
    public void changeBookshelf(long userID, Bookshelf bookshelfToChange) throws UserNotHaveAccessException, BookshelfNotExistException{
        if (bookshelfTable.isUserOwnerOfBookshelf(userID, bookshelfToChange.getId())) {
            if(bookshelfTable.isBookshelfExists(bookshelfToChange.getId())) {
                bookshelfTable.changeBookshelf(bookshelfToChange.getId(), bookshelfToChange.getTitle());
            }else{
                throw new BookshelfNotExistException();
            }
        }else{
            throw new UserNotHaveAccessException();
        }
    }
}